/******************************************************************************************
 * Copyright (c) 2008 - 2010 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.wizard.actions;

import org.eclipse.core.resources.IFile;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;
import org.springframework.ide.eclipse.beans.core.BeansCorePlugin;
import org.springframework.ide.eclipse.beans.core.BeansCoreUtils;
import org.springframework.ide.eclipse.beans.core.internal.model.BeansProject;
import org.springframework.ide.eclipse.beans.core.model.IBeansConfig;
import org.springframework.ide.eclipse.beans.core.model.IBeansProject;

import com.springsource.sts.wizard.Messages;
import com.springsource.sts.wizard.ui.BeanWizardDialog;

/**
 * Action for creating new bean definition
 * @author Terry Denney
 * @author Leo Dos Santos
 * @author Christian Dupuis
 * @since 2.0
 */
public class CreateNewBeanDefAction extends Action implements IObjectActionDelegate {

	private IFile selectedFile;

	@Override
	public void run() {
		Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
		if (shell != null && !shell.isDisposed()) {
			if (!BeansCoreUtils.isBeansConfig(selectedFile)) {
				IBeansProject springProject = BeansCorePlugin.getModel().getProject(selectedFile.getProject());
				if (springProject != null && springProject instanceof BeansProject) {
					boolean convert = MessageDialog.openQuestion(shell, Messages
							.getString("CreateNewBeanDefAction.QUESTION_DIALOG_TITLE"), Messages
							.getString("CreateNewBeanDefAction.QUESTION_DIALOG_MESSAGE"));
					if (convert) {
						((BeansProject) springProject).addConfig(selectedFile, IBeansConfig.Type.MANUAL);
					}
				}
				else {
					MessageDialog.openError(shell, Messages.getString("CreateNewBeanDefAction.ERROR_DIALOG_TITLE"),
							Messages.getString("CreateNewBeanDefAction.ERROR_DIALOG_MESSAGE"));
				}
			}

			if (BeansCoreUtils.isBeansConfig(selectedFile)) {
				BeanWizardDialog dialog = BeanWizardDialog.createBeanWizardDialog(shell, selectedFile, true);
				dialog.create();
				dialog.setBlockOnOpen(true);
				dialog.open();
			}
		}
	}

	public void run(IAction action) {
		run();
	}

	public void selectionChanged(IAction action, ISelection selection) {
		if (selection instanceof TreeSelection) {
			TreeSelection treeSelection = (TreeSelection) selection;
			Object[] objects = treeSelection.toArray();
			if (objects != null && objects.length == 1) {
				if (objects[0] instanceof IFile) {
					selectedFile = (IFile) objects[0];
					return;
				}
			}
		}
		selectedFile = null;
	}

	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
	}

}
