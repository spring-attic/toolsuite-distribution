/******************************************************************************************
 * Copyright (c) 2009 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.internal.ide.help;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.springsource.ide.eclipse.commons.content.core.util.ContentUtil;
import org.springsource.ide.eclipse.commons.content.core.util.Descriptor;
import org.springsource.ide.eclipse.commons.content.core.util.IContentConstants;
import org.springsource.ide.eclipse.commons.core.StatusHandler;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


/**
 * @author Steffen Pingel
 * @author Terry Denney
 */
public class SampleProjectData {

	public static class WorkspaceProject {

		private String archiveFileName;

		private String name;

		public String getArchiveFileName() {
			return archiveFileName;
		}

		public String getName() {
			return name;
		}

		public void setArchiveFileName(String archiveFileName) {
			this.archiveFileName = archiveFileName;
		}

		public void setName(String name) {
			this.name = name;
		}

	}

	private static final String ATTRIBUTE_PROJECT_NAME = "name";

	private static final String ATTRIBUTE_PROJECT_PATH = "path";

	private static final String TAG_PROJECT = "project";

	private static final String TAG_DESCRIPTOR = "descriptor";

	private static final String TAG_SAMPLE_PROJECT = "sampleProject";

	private final String id;

	private final File path;

	private final List<WorkspaceProject> projects = new ArrayList<WorkspaceProject>();

	private Descriptor descriptor;

	/**
	 * Constructs a local tutorial.
	 */
	public SampleProjectData(File path) {
		Assert.isNotNull(path);
		this.path = path;
		this.id = path.getName();
	}

	public void addProject(WorkspaceProject project) {
		projects.add(project);
	}

	public Descriptor getDescriptor() {
		return descriptor;
	}

	public File getFile() throws CoreException {
		return new File(path, IContentConstants.SAMPLE_PROJECT_DATA_FILE_NAME);
	}

	public File getFile(String filename) throws CoreException {
		File file = new File(path, filename);
		if (!file.exists()) {
			StatusHandler.log(new Status(IStatus.WARNING, HelpPlugin.PLUGIN_ID,
					"Sample project failed to access file \"" + file + "\""));
			return null;
		}
		return file;
	}

	public File getPath() {
		return path;
	}

	public WorkspaceProject[] getProjects() {
		return projects.toArray(new WorkspaceProject[0]);
	}

	public void read() throws CoreException {
		File file = getFile();
		DocumentBuilder documentBuilder = ContentUtil.createDocumentBuilder();
		Document document = null;
		try {
			document = documentBuilder.parse(file);
			Element rootNode = document.getDocumentElement();
			if (rootNode == null) {
				throw new SAXException("No root node");
			}
			if (!TAG_SAMPLE_PROJECT.equals(rootNode.getNodeName())) {
				throw new SAXException("Not a a valid tutorial");
			}
			NodeList children = rootNode.getChildNodes();
			for (int i = 0; i < children.getLength(); i++) {
				Node childNode = children.item(i);
				if (childNode.getNodeType() == Node.ELEMENT_NODE) {
					if (TAG_DESCRIPTOR.equals(childNode.getNodeName())) {
						descriptor = Descriptor.read(childNode);
					}
					else if (TAG_PROJECT.equals(childNode.getNodeName())) {
						readProjectNode(childNode);
					}
					else {
						StatusHandler.log(new Status(IStatus.WARNING, HelpPlugin.PLUGIN_ID, "Unknown tag \""
								+ childNode.getNodeName() + "\" in tutorial \"" + id + "\""));
					}
				}
			}
		}
		catch (SAXException e) {
			throw new CoreException(new Status(Status.ERROR, HelpPlugin.PLUGIN_ID,
					"Could not read initialization data for tutorial \"" + id + "\"", e));
		}
		catch (IOException e) {
			throw new CoreException(new Status(Status.ERROR, HelpPlugin.PLUGIN_ID,
					"Could not read initialization data for tutorial \"" + id + "\"", e));
		}
	}

	private void readProjectNode(Node node) throws SAXException {
		String path = ContentUtil.getAttributeValue(node, ATTRIBUTE_PROJECT_PATH);
		if (path == null) {
			throw new SAXException("The project record is invalid");
		}
		String name = ContentUtil.getAttributeValue(node, ATTRIBUTE_PROJECT_NAME);

		WorkspaceProject project = new WorkspaceProject();
		project.setArchiveFileName(path);
		project.setName(name);
		projects.add(project);
	}

	public void validate() throws CoreException {
		if (projects.isEmpty()) {
			throw new CoreException(new Status(Status.ERROR, HelpPlugin.PLUGIN_ID, "The sample project \"" + id
					+ "\" does not specify a workspace project"));
		}
	}

	public void write() throws CoreException {
		File file = getFile();
		DocumentBuilder documentBuilder = ContentUtil.createDocumentBuilder();
		Transformer serializer = ContentUtil.createTransformer();
		Document document = documentBuilder.newDocument();
		writeDocument(document);
		DOMSource source = new DOMSource(document);
		try {
			StreamResult target = new StreamResult(file);
			serializer.setOutputProperty(OutputKeys.INDENT, "yes");
			serializer.transform(source, target);
		}
		catch (TransformerException e) {
			throw new CoreException(new Status(Status.ERROR, HelpPlugin.PLUGIN_ID,
					"Could not write initialization data for tutorial \"" + id + "\""));
		}
	}

	private void writeDocument(Document document) {
		Element rootNode = document.createElement(TAG_SAMPLE_PROJECT);
		document.appendChild(rootNode);

		Element descriptorNode = document.createElement(TAG_DESCRIPTOR);
		rootNode.appendChild(descriptorNode);
		Descriptor.write(descriptor, descriptorNode);

		for (WorkspaceProject project : projects) {
			Element projectNode = document.createElement(TAG_PROJECT);
			rootNode.appendChild(projectNode);
			writeProject(document, projectNode, project);
		}

	}

	private void writeProject(Document document, Element projectNode, WorkspaceProject project) {
		projectNode.setAttribute(ATTRIBUTE_PROJECT_PATH, project.getArchiveFileName());
		if (project.getName() != null) {
			projectNode.setAttribute(ATTRIBUTE_PROJECT_NAME, project.getName());
		}
	}

}
