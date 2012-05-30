/******************************************************************************************
 * Copyright (c) 2008 - 2009 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.internal.ide.help.commands;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.Status;
import org.eclipse.ui.handlers.HandlerUtil;

import com.springsource.sts.internal.ide.help.tutorial.TutorialManager;

/**
 * @author Steffen Pingel
 * @author Leo Dos Santos
 * @author Christian Dupuis
 */
public abstract class AbstractTutorialHandler extends AbstractHandler {

	public Object execute(ExecutionEvent event) throws ExecutionException {
		try {
			TutorialManager tutorial = TutorialManager.getActiveTutorial(HandlerUtil
					.getActiveWorkbenchWindowChecked(event));
			if (tutorial != null) {
				execute(event, tutorial);
			}
			else {
				return Status.CANCEL_STATUS;
			}
		}
		catch (CoreException e) {
			throw new ExecutionException("Could not perform command", e);
		}
		catch (OperationCanceledException e) {
			return Status.CANCEL_STATUS;
		}
		return Status.OK_STATUS;
	}

	protected abstract void execute(ExecutionEvent event, TutorialManager tutorial) throws CoreException;

}
