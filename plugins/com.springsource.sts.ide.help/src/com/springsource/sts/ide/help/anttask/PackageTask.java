/******************************************************************************************
 * Copyright (c) 2009 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.ide.help.anttask;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.DirectoryScanner;
import org.apache.tools.ant.Task;
import org.apache.tools.ant.taskdefs.Zip;
import org.apache.tools.ant.types.FileSet;
import org.apache.tools.ant.types.ZipFileSet;
import org.eclipse.core.runtime.CoreException;
import org.springsource.ide.eclipse.commons.content.core.util.Descriptor;
import org.springsource.ide.eclipse.commons.content.core.util.DescriptorReader;


/**
 * @author Steffen Pingel
 * @author Terry Denney
 * @author Christian Dupuis
 */
public class PackageTask extends Task {

	private final List<FileSet> filesets = new ArrayList<FileSet>();

	private final DescriptorReader reader = new DescriptorReader();

	private File dest;

	private String baseUrl = "";

	public void addFileset(FileSet set) {
		filesets.add(set);
	}

	@Override
	public void execute() throws BuildException {
		if (filesets.isEmpty()) {
			throw new BuildException("A file set is required");
		}
		if (dest == null) {
			throw new BuildException("toDir is required");
		}

		for (FileSet fileset : filesets) {
			DirectoryScanner ds = fileset.getDirectoryScanner(getProject());
			String[] files = ds.getIncludedFiles();
			if (files != null) {
				File baseDir = ds.getBasedir();
				for (String file : files) {
					File inputFile = new File(baseDir, file);
					processFile(inputFile);
				}
			}
		}

		try {
			reader.write(new File(dest, "descriptors.xml"));
		}
		catch (CoreException e) {
			throw new BuildException(e);
		}
	}

	public String getBaseUrl() {
		return baseUrl;
	}

	public File getDest() {
		return dest;
	}

	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}

	public void setDest(File toFile) {
		this.dest = toFile;
	}

	private void processFile(final File source) throws BuildException {
		try {
			List<Descriptor> descriptors = reader.read(source);
			for (Descriptor descriptor : descriptors) {
				log("processing " + descriptor.getId() + " from " + source.getAbsoluteFile());
				Zip zip = new Zip();
				zip.setProject(getProject());
				zip.setTaskName(getTaskName());
				zip.setUpdate(false);

				ZipFileSet fileSet = new ZipFileSet();
				fileSet.setDir(source.getParentFile());
				fileSet.setExcludes("template/**");
				zip.addFileset(fileSet);

				zip.init();

				File destFile = new File(dest, descriptor.getId() + "-" + descriptor.getVersion() + ".zip");
				zip.setDestFile(destFile);
				zip.perform();

				try {
					descriptor.setUrl(getBaseUrl() + URLEncoder.encode(destFile.getName(), "UTF-8"));
				}
				catch (UnsupportedEncodingException e) {
					descriptor.setUrl(getBaseUrl() + destFile.getName());
				}
				descriptor.setSize(destFile.length());
			}
		}
		catch (CoreException e) {
			throw new BuildException(e);
		}
	}
}
