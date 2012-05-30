/******************************************************************************************
 * Copyright (c) 2007 - 2009 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.internal.ide.help;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.zip.ZipFile;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.actions.WorkspaceModifyOperation;
import org.eclipse.ui.dialogs.IOverwriteQuery;
import org.eclipse.ui.internal.wizards.datatransfer.ZipLeveledStructureProvider;
import org.eclipse.ui.wizards.datatransfer.ImportOperation;
import org.springsource.ide.eclipse.commons.core.Policy;


/**
 * @author Wesley Coelho
 * @author Steffen Pingel
 * @author Leo Dos Santos
 * @author Christian Dupuis
 */
public class SampleProjectImporter {

	private final String projectName;

	private final Shell shell;

	protected volatile IProject project;

	private final File archiveFile;

	public SampleProjectImporter(String projectName, File archiveFile, Shell shell) {
		this.projectName = projectName;
		this.archiveFile = archiveFile;
		this.shell = shell;
	}

	private void deleteExistingProject(final IProgressMonitor monitor) throws CoreException {
		if (project.exists()) {
			final boolean[] result = new boolean[1];
			Display.getDefault().syncExec(new Runnable() {
				public void run() {
					result[0] = MessageDialog.openQuestion(getShell(), "Import Sample Project", "A project named "
							+ project.getName() + " already exists in the workspace. Overwrite the existing project?");
				}
			});

			if (!result[0]) {
				throw new OperationCanceledException();
			}

			// project.close(new SubProgressMonitor(monitor, 5));
			project.delete(true, true, monitor);
		}
	}

	private Shell getShell() {
		return shell;
	}

	private ZipFile getZipFile() throws CoreException {
		try {
			return new ZipFile(archiveFile);
		}
		catch (IOException e) {
			throw new CoreException(new Status(IStatus.ERROR, HelpPlugin.PLUGIN_ID,
					"Could not locate sample project archive", e));
		}
	}

	private void importFiles(IProject project, ZipFile archiveFile, IProgressMonitor monitor)
			throws InvocationTargetException, InterruptedException {
		ZipLeveledStructureProvider provider = new ZipLeveledStructureProvider(archiveFile);
		ImportOperation operation = new ImportOperation(project.getFullPath(), provider.getRoot(), provider,
				new IOverwriteQuery() {
					// always overwrite
					public String queryOverwrite(String pathString) {
						return IOverwriteQuery.YES;
					}
				});
		operation.setContext(getShell());
		// need to overwrite .project file
		operation.setOverwriteResources(true);
		operation.setCreateContainerStructure(false);
		operation.run(monitor);
	}

	public void importProject(IProgressMonitor monitor) throws CoreException, OperationCanceledException {
		final ZipFile archiveFile = getZipFile();

		final WorkspaceModifyOperation op = new WorkspaceModifyOperation() {
			@Override
			protected void execute(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException,
					CoreException {
				monitor.subTask("Creating project " + projectName);

				Policy.checkCancelled(monitor);
				IWorkspace workspace = ResourcesPlugin.getWorkspace();
				IProjectDescription projectDescription = workspace.newProjectDescription(projectName);
				project.create(projectDescription, new SubProgressMonitor(monitor, 10));
				project.open(new SubProgressMonitor(monitor, 20));

				monitor.subTask("Importing project " + projectName);
				Policy.checkCancelled(monitor);
				importFiles(project, archiveFile, new SubProgressMonitor(monitor, 70));
			}

		};

		try {
			monitor.beginTask("Importing project", 100);

			IWorkspace workspace = ResourcesPlugin.getWorkspace();
			project = workspace.getRoot().getProject(projectName);

			deleteExistingProject(monitor);

			op.run(monitor);
		}
		catch (InterruptedException e) {
			throw new OperationCanceledException();
		}
		catch (InvocationTargetException e) {
			throw new CoreException(new Status(IStatus.ERROR, HelpPlugin.PLUGIN_ID, "Error during project import", e));
		}
		finally {
			monitor.done();
		}
	}
}
