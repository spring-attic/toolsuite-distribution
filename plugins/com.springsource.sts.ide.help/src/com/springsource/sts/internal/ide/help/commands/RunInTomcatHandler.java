/******************************************************************************************
 * Copyright (c) 2008 - 2009 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.internal.ide.help.commands;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.runtime.CoreException;

import com.springsource.sts.internal.ide.help.tutorial.TutorialManager;
import com.springsource.sts.internal.ide.help.tutorial.TutorialManager.ServerType;

/**
 * @author Steffen Pingel
 * @author Leo Dos Santos
 * @author Christian Dupuis
 */
public class RunInTomcatHandler extends AbstractTutorialHandler {

	private static final String ID_PARAMETER_FILE_NAME = "projectName";

	private static final String ID_PARAMETER_URL = "url";

	public RunInTomcatHandler() {
	}

	@Override
	protected void execute(ExecutionEvent event, TutorialManager tutorial) throws CoreException {
		String projectName = event.getParameter(ID_PARAMETER_FILE_NAME);
		String url = event.getParameter(ID_PARAMETER_URL);

		tutorial.runInServer(ServerType.TOMCAT, projectName, url);
	}

}
