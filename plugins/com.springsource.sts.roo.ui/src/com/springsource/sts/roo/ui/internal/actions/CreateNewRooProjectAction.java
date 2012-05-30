/******************************************************************************************
 * Copyright (c) 2009 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.roo.ui.internal.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.PlatformUI;

import com.springsource.sts.roo.ui.RooUiActivator;
import com.springsource.sts.roo.ui.internal.wizard.NewRooProjectWizard;

/**
 * {@link Action} implementation for launching the Roo project creation wizard.
 * @author Christian Dupuis
 * @since 2.3.0
 */
public class CreateNewRooProjectAction extends Action {
	
	private final Shell shell;
	
	public CreateNewRooProjectAction(Shell shell) {
		super("Create New Roo Project", RooUiActivator.getImageDescriptor("icons/full/obj16/new_project_obj.png"));
		this.shell = shell;
	}
	
	@Override
	public void run() {
		INewWizard wizard = new NewRooProjectWizard();
		wizard.init(PlatformUI.getWorkbench(), new StructuredSelection());
		WizardDialog dialog = new WizardDialog(shell, wizard);
		dialog.open();
	}
	
}
