/******************************************************************************************
 * Copyright (c) 2008 - 2009 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.internal.ide.help.wizards;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.mylyn.commons.core.ICoreRunnable;
import org.eclipse.mylyn.commons.ui.CommonUiUtil;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchWizard;
import org.springsource.ide.eclipse.commons.content.core.ContentItem;
import org.springsource.ide.eclipse.commons.core.StatusHandler;

import com.springsource.sts.ide.help.TutorialUtils;
import com.springsource.sts.internal.ide.help.HelpImages;
import com.springsource.sts.internal.ide.help.HelpPlugin;

/**
 * @author Steffen Pingel
 * @author Wesley Coelho
 * @author Leo Dos Santos
 * @author Christian Dupuis
 * @author Terry Denney
 */
public class ImportSampleProjectsWizard extends Wizard implements IWorkbenchWizard {

	private SelectSampleProjectsPage selectProjectsPage;

	public ImportSampleProjectsWizard() {
		super();
		setDefaultPageImageDescriptor(HelpImages.IMPORT_WIZBAN);
	}

	@Override
	public void addPages() {
		selectProjectsPage = new SelectSampleProjectsPage();
		addPage(selectProjectsPage);

		setNeedsProgressMonitor(true);
		setWindowTitle("Import Sample Projects");
	}

	@Override
	public boolean canFinish() {
		return selectProjectsPage.isPageComplete();
	}

	public void init(IWorkbench workbench, IStructuredSelection selection) {
	}

	@Override
	public boolean performFinish() {
		final ContentItem[] sampleProjects = selectProjectsPage.getSelectedProjects();
		try {
			CommonUiUtil.run(getContainer(), new ICoreRunnable() {
				public void run(IProgressMonitor monitor) throws CoreException {
					try {
						monitor.beginTask("Import Sample Projects", sampleProjects.length);

						for (ContentItem sampleProject : sampleProjects) {
							monitor.subTask(sampleProject.getName());
							TutorialUtils.importSample(sampleProject, getShell(), new SubProgressMonitor(monitor, 1));
						}
					}
					finally {
						monitor.done();
					}
				}
			});
		}
		catch (OperationCanceledException e) {
			return false;
		}
		catch (CoreException e) {
			StatusHandler.log(new Status(IStatus.ERROR, HelpPlugin.PLUGIN_ID,
					"Unexpected error during sample project import", e));
			CommonUiUtil.setMessage(selectProjectsPage, e.getStatus());
			return false;
		}
		return true;
	}

}
