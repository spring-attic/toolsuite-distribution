/******************************************************************************************
 * Copyright (c) 2008 - 2012 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.wizard.template;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.wizards.newresource.BasicNewResourceWizard;
import org.springframework.ide.eclipse.core.SpringCoreUtils;
import org.springsource.ide.eclipse.commons.core.StatusHandler;
import org.springsource.ide.eclipse.commons.ui.UiStatusHandler;

import com.springsource.sts.wizard.WizardImages;
import com.springsource.sts.wizard.WizardPlugin;
import com.springsource.sts.wizard.template.infrastructure.processor.ProcessingInfo;
import com.springsource.sts.wizard.template.infrastructure.processor.Processor;
import com.springsource.sts.wizard.template.infrastructure.ui.WizardUI;
import com.springsource.sts.wizard.template.newproject.NewProjectProcessingInfo;

/**
 * @author Terry Denney
 * @author Leo Dos Santos
 * @author Christian Dupuis
 * @author Martin Lippert
 */
public class TemplateWizard extends Wizard implements INewWizard, WizardUI {

	private final TemplateSelectionWizardPage selectionPage;

	private NewTemplateWizardPage firstPage;

	private IWorkbench fWorkbench;

	private IProject project;

	public TemplateWizard() {
		selectionPage = new TemplateSelectionWizardPage(this);
		addPage(selectionPage);

		setWindowTitle("New Template Project");
		setDefaultPageImageDescriptor(WizardImages.TEMPLATE_WIZARD_ICON);
		setForcePreviousAndNextButtons(true);
		setNeedsProgressMonitor(true);
	}

	@Override
	public boolean canFinish() {
		if (firstPage == null) {
			return false;
		}
		return firstPage.isPageComplete();
	}

	public void collectInput(Map<String, Object> collectedInput, Map<String, String> inputKinds) {
		IWizardPage page = selectionPage.getNextPage();
		while (page != null) {
			if (page instanceof NewTemplateWizardPage) {
				((NewTemplateWizardPage) page).collectInput(collectedInput, inputKinds);
			}
			page = page.getNextPage();
		}
	}

	public void init(IWorkbench workbench, IStructuredSelection selection) {
		fWorkbench = workbench;
	}

	@Override
	public boolean performFinish() {
		final Map<String, Object> collectedInput = new HashMap<String, Object>();
		final Map<String, String> inputKinds = new HashMap<String, String>();
		collectInput(collectedInput, inputKinds);

		final IProject projectHandle = firstPage.getProjectHandle();

		IPath newPath = null;
		if (!firstPage.useDefaults()) {
			newPath = firstPage.getLocationPath();
		}

		final IPath finalPath = newPath;

		try {
			getContainer().run(true, true, new IRunnableWithProgress() {

				public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
					try {
						ProcessingInfo info = new NewProjectProcessingInfo(selectionPage.getProjectLocation(),
								projectHandle.getName());

						Processor processor = new Processor(info);
						project = processor.process(projectHandle, finalPath, selectionPage.getTopLevelPackage(),
								selectionPage.getProjectNameToken(), collectedInput, inputKinds, getContainer()
										.getShell(), monitor);
						if (project != null) {
							selectAndReveal(project);
							try {
								project.refreshLocal(IResource.DEPTH_INFINITE, new NullProgressMonitor());
								SpringCoreUtils.buildFullProject(project);
							}
							catch (CoreException e) {
								UiStatusHandler.logAndDisplay(e.getStatus());
							}
						}
					}
					catch (Exception e) {
						StatusHandler.log(new Status(Status.ERROR, WizardPlugin.PLUGIN_ID,
								"Exception during template project creation", e));
					}
				}
			});
		}
		catch (InvocationTargetException e) {
			StatusHandler.log(new Status(Status.ERROR, WizardPlugin.PLUGIN_ID, "Template project creation failed", e));
		}
		catch (InterruptedException e) {
			StatusHandler.log(new Status(Status.ERROR, WizardPlugin.PLUGIN_ID, "Template project creation interrupted",
					e));
		}
		return project != null;
	}

	public void setFirstPage(NewTemplateWizardPage firstPage) {
		this.firstPage = firstPage;
	}

	protected void selectAndReveal(final IResource newResource) {
		getContainer().getShell().getDisplay().syncExec(new Runnable() {

			public void run() {
				BasicNewResourceWizard.selectAndReveal(newResource, fWorkbench.getActiveWorkbenchWindow());
			}
		});
	}
}
