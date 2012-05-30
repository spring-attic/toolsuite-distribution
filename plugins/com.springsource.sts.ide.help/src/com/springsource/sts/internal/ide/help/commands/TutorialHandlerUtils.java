/******************************************************************************************
 * Copyright (c) 2008 - 2009 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.internal.ide.help.commands;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;

import com.springsource.sts.internal.ide.help.tutorial.TutorialManager;

/**
 * @author Steffen Pingel
 * @author Leo Dos Santos
 * @author Christian Dupuis
 */
public class TutorialHandlerUtils {

	public static TutorialManager getActiveTutorial(ExecutionEvent event) throws ExecutionException {
		TutorialManager tutorial = TutorialManager.getActiveTutorial();
		if (tutorial == null) {
			throw new ExecutionException("No tutorial is active");
		}
		return tutorial;
	}

}
