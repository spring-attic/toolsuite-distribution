/******************************************************************************************
 * Copyright (c) 2008 - 2009 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.internal.ide.help;

import java.net.MalformedURLException;
import java.net.URL;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.mylyn.context.ui.IContextUiStartup;
import org.eclipse.mylyn.tasks.core.ITask;
import org.eclipse.mylyn.tasks.core.TaskActivationAdapter;
import org.eclipse.mylyn.tasks.ui.TasksUi;
import org.eclipse.ui.PlatformUI;
import org.springsource.ide.eclipse.commons.core.StatusHandler;

import com.springsource.sts.ide.help.TutorialUtils;
import com.springsource.sts.internal.ide.help.tutorial.TutorialManager;

/**
 * Listens to first task activation.
 * @author Steffen Pingel
 * @author Leo Dos Santos
 * @author Christian Dupuis
 */
public class TutorialStartup implements IContextUiStartup {

	/**
	 * Listener that starts tutorial if a task is activated that has an
	 * associated tutorial.
	 */
	private class TutorialActivationListener extends TaskActivationAdapter {

		@Override
		public void taskActivated(ITask task) {
			String tutorialUrl = task.getAttribute(TutorialManager.KEY_TUTORIAL_URL);
			if (tutorialUrl == null) {
				return;
			}

			TutorialManager activeTutorial = TutorialManager.getActiveTutorial();
			if (activeTutorial != null && activeTutorial.getTutorialUrl().equals(tutorialUrl)) {
				return;
			}

			try {
				TutorialManager.setActiveTutorial(null);
				TutorialUtils.openTutorial(PlatformUI.getWorkbench().getActiveWorkbenchWindow(), new URL(tutorialUrl),
						false);
			}
			catch (MalformedURLException e) {
				StatusHandler.log(new Status(IStatus.ERROR, HelpPlugin.PLUGIN_ID, "Error opening tutorial", e));
			}
		}

	}

	private final TutorialActivationListener activationListener;

	public TutorialStartup() {
		activationListener = new TutorialActivationListener();
	}

	public void lazyStartup() {
		TasksUi.getTaskActivityManager().addActivationListener(activationListener);
	}

}
