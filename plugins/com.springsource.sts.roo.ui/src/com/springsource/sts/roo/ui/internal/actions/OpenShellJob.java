/******************************************************************************************
 * Copyright (c) 2012 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.roo.ui.internal.actions;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;

import com.springsource.sts.roo.ui.internal.RooShellView;

public class OpenShellJob extends Job {

	private final IProject project;

	public OpenShellJob(IProject project) {
		super(String.format("Opening Roo shell for project '%s'", project.getName()));
		this.project = project;
		setPriority(Job.INTERACTIVE);
		setSystem(true);
	}

	@Override
	protected IStatus run(IProgressMonitor monitor) {
		Display.getDefault().asyncExec(new Runnable() {

			public void run() {
				try {
					RooShellView view = (RooShellView) PlatformUI.getWorkbench().getActiveWorkbenchWindow()
							.getActivePage().showView("com.springsource.sts.roo.ui.rooShellView", null,
									IWorkbenchPage.VIEW_ACTIVATE);
					view.openShell(project);
				}
				catch (Exception e) {
				}
			}
		});
		return Status.OK_STATUS;
	}

}
