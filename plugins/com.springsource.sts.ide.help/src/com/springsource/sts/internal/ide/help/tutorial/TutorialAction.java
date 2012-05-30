/******************************************************************************************
 * Copyright (c) 2008 - 2009 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.internal.ide.help.tutorial;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.cheatsheets.ICheatSheetAction;
import org.eclipse.ui.cheatsheets.ICheatSheetManager;
import org.eclipse.ui.internal.cheatsheets.views.CheatSheetView;
import org.springsource.ide.eclipse.commons.content.core.util.IContentConstants;
import org.springsource.ide.eclipse.commons.core.StatusHandler;
import org.springsource.ide.eclipse.commons.ui.UiStatusHandler;

import com.springsource.sts.internal.ide.help.HelpPlugin;
import com.springsource.sts.internal.ide.help.tutorial.TutorialManager.ServerType;

/**
 * @author Steffen Pingel
 * @author Leo Dos Santos
 * @author Christian Dupuis
 * @author Terry Denney
 */
public class TutorialAction extends Action implements ICheatSheetAction {

	public static String INSTANCE_ID = System.currentTimeMillis() + ":" + new Object().hashCode();

	// protected static final String VIEW_ID_PACKAGE_EXPLORER =
	// JavaUI.ID_PACKAGES;
	//
	// protected static final String VIEW_ID_PROJECT_EXPLORER =
	// ProjectExplorer.VIEW_ID;
	//
	private static boolean authoring = false;

	public static TutorialManager lastTutorial = null;

	public static String lastCommand = null;

	private static List<TutorialListener> tutorialListeners = new CopyOnWriteArrayList<TutorialListener>();

	public static void addTutorialListener(TutorialListener listener) {
		tutorialListeners.add(listener);
	}

	public static boolean isAuthoring() {
		return authoring;
	}

	public static void removeTutorialListener(TutorialListener listener) {
		tutorialListeners.remove(listener);
	}

	public static void setAuthoring(boolean authoring) {
		TutorialAction.authoring = authoring;
	}

	public TutorialAction() {
	}

	public void fireStepActivated(ICheatSheetManager manager) {
		TutorialListener[] listeners = tutorialListeners.toArray(new TutorialListener[0]);
		for (TutorialListener listener : listeners) {
			listener.stepActivated(manager);
		}
	}

	/**
	 * @return true if <code>e</code> was handled
	 */
	private boolean handleCoreException(CoreException e) {
		if (e.getStatus().getCode() == IContentConstants.ERROR_MISSING_PROJECT) {
			final IWorkbenchWindow activeWorkbenchWindow = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
			if (activeWorkbenchWindow != null) {
				IWorkbenchPage activePage = activeWorkbenchWindow.getActivePage();
				if (activePage != null && activePage.getActivePart() instanceof CheatSheetView) {
					CheatSheetView view = (CheatSheetView) activePage.getActivePart();
					boolean restart = MessageDialog.openConfirm(activeWorkbenchWindow.getShell(), "Tutorial",
							"This tutorial requires a project that was not found in the workspace. Restart tutorial?");
					if (restart) {
						view.getCheatSheetViewer().restart();
					}
					return true;
				}
			}
		}
		return false;
	}

	public void run(String[] params, ICheatSheetManager manager) {
		if (params.length == 0) {
			StatusHandler.log(new Status(IStatus.ERROR, HelpPlugin.PLUGIN_ID, "Missing paramater for TutorialAction"));
			return;
		}

		TutorialManager tutorial = new TutorialManager(manager);
		if (tutorial.isTutorial()) {
			TutorialManager.setActiveTutorial(tutorial);
		}
		else if (tutorial.isCheatSheetPreview()) {
			// the tutorial was launched from the cheat sheet editor
			// preview, ignore all actions
			TutorialManager.setActiveTutorial(null);
			return;
		}

		if (authoring) {
			lastCommand = params[0];
			lastTutorial = tutorial;
		}

		manager.setData(IContentConstants.TUTORIAL_STARTED, INSTANCE_ID);

		String command = params[0];
		try {
			if ("setupWorkspace".equals(command)) {
				tutorial.setupWorkspace();
			}
			else if (command.startsWith("step")) {
				tutorial.runStep(command);
			}
			else if ("cleanupWorkspace".equals(command)) {
				tutorial.cleanupWorkspace();
			}
			else if ("runInTomcat".equals(command)) {
				String projectName = params[1];
				String url = params[2];
				tutorial.runInServer(ServerType.TOMCAT, projectName, url);
			}
			else {
				throw new CoreException(new Status(IStatus.ERROR, HelpPlugin.PLUGIN_ID,
						"TutorialAction invoked with invalid parameter: " + command));
			}
		}
		catch (OperationCanceledException e) {
			// canceled
		}
		catch (CoreException e) {
			if (!handleCoreException(e)) {
				UiStatusHandler.logAndDisplay(e.getStatus());
			}
		}

		if (authoring && command.startsWith("step")) {
			fireStepActivated(manager);
		}
	}
}
