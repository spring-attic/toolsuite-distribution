/******************************************************************************************
 * Copyright (c) 2008 - 2009 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.internal.ide.help.commands;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.runtime.CoreException;

import com.springsource.sts.internal.ide.help.tutorial.TutorialManager;

/**
 * @author Steffen Pingel
 * @author Leo Dos Santos
 * @author Christian Dupuis
 */
public class TearDownTutorialHandler extends AbstractTutorialHandler {

	public TearDownTutorialHandler() {
	}

	@Override
	protected void execute(ExecutionEvent event, TutorialManager tutorial) throws CoreException {
		tutorial.cleanupWorkspace();
	}

}
