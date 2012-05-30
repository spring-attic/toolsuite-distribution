/******************************************************************************************
 * Copyright (c) 2010 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.roo.ui.internal.actions;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.core.resources.IProject;
import org.eclipse.jdt.internal.ui.JavaPlugin;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PartInitException;
import org.springsource.ide.eclipse.commons.frameworks.core.internal.commands.ICommandListener;
import org.springsource.ide.eclipse.commons.frameworks.core.internal.commands.IFrameworkCommand;
import org.springsource.ide.eclipse.commons.frameworks.ui.internal.actions.AbstractCommandActionDelegate;
import org.springsource.ide.eclipse.commons.frameworks.ui.internal.wizard.GenericCommandWizard;

import com.springsource.sts.roo.ui.internal.RooShellTab;
import com.springsource.sts.roo.ui.internal.RooShellView;
import com.springsource.sts.roo.ui.internal.wizard.RooCommandWizard;

/**
 * Action to open the Roo Command Wizard
 * @author Christian Dupuis
 * @since 2.5.0
 */
@SuppressWarnings("restriction")
public class RooCommandWizardActionDelegate extends AbstractCommandActionDelegate {

	private RooShellTab tab;

	protected void addCommands(ICommandListener listener) {
		if (listener != null && tab != null) {
			tab.addCommands(listener);
		}
	}

	protected GenericCommandWizard getCommandWizard(Collection<IProject> projects, IFrameworkCommand command) {
		IProject project = getSelectedProjects().get(0);

		try {
			IWorkbenchPart workbench = JavaPlugin.getActiveWorkbenchWindow().getActivePage().getActivePart();
			RooShellView view = (RooShellView) workbench.getSite().getPage()
					.showView("com.springsource.sts.roo.ui.rooShellView", null, IWorkbenchPage.VIEW_ACTIVATE);
			tab = view.openShell(project);
		}
		catch (PartInitException e) {
			// TODO CD what to do here
		}

		projects = new ArrayList<IProject>();
		projects.add(project);
		RooCommandWizard wizard = new RooCommandWizard(projects, command, tab);
		addCommands(wizard);

		return wizard;
	}

}
