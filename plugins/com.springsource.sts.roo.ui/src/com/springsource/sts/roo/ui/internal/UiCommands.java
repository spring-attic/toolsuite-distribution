/******************************************************************************************
 * Copyright (c) 2009 - 2010 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.roo.ui.internal;

import org.eclipse.core.resources.IProject;
import org.eclipse.swt.widgets.Display;

/**
 * @author Christian Dupuis
 */
public class UiCommands {

	private final RooShellTab rooShellTab;

	public UiCommands(RooShellTab rooShellTab) {
		this.rooShellTab = rooShellTab;
	}

	public void exit() {
		Display.getDefault().asyncExec(new Runnable() {

			public void run() {
				rooShellTab.shellView.removeTab(rooShellTab.project);
			}
		});
	}

	public void exitAndRestart(final int returnCode) {
		Display.getDefault().asyncExec(new Runnable() {

			public void run() {
				IProject project = rooShellTab.project;
				rooShellTab.shellView.removeTab(project);
				rooShellTab.shellView.openShell(project);
			}
		});
	}

}