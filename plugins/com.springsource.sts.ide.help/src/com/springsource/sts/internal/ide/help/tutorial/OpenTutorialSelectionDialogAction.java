/******************************************************************************************
 * Copyright (c) 2008 - 2009 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.internal.ide.help.tutorial;

import org.eclipse.core.runtime.Assert;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.window.Window;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;
import org.springsource.ide.eclipse.commons.content.core.ContentItem;

import com.springsource.sts.ide.help.TutorialUtils;

/**
 * @author Steffen Pingel
 * @author Leo Dos Santos
 * @author Christian Dupuis
 * @author Terry Denney
 */
public class OpenTutorialSelectionDialogAction implements IWorkbenchWindowActionDelegate {

	private IWorkbenchWindow window;

	public void dispose() {
	}

	public void init(IWorkbenchWindow window) {
		this.window = window;
	}

	public void run() {
		run(null);
	}

	public void run(IAction action) {
		TutorialSelectionDialog dialog = new TutorialSelectionDialog(window.getShell());
		if (dialog.open() == Window.OK) {
			ContentItem tutorial = dialog.getSelectedTutorial();
			Assert.isNotNull(tutorial);
			TutorialUtils.openTutorial(window, tutorial, false);
		}
	}

	public void selectionChanged(IAction action, ISelection selection) {
	}

}
