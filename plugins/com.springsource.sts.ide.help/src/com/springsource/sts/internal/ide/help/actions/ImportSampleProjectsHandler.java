/******************************************************************************************
 * Copyright (c) 2007 - 2009 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.internal.ide.help.actions;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.Status;

/**
 * @author Wesley Coelho
 * @author Steffen Pingel
 * @author Leo Dos Santos
 * @author Christian Dupuis
 */
public class ImportSampleProjectsHandler extends AbstractHandler {

	private static final String PROJECT_FILE = "projectFile";

	private static final String PROJECT_NAME = "projectName";

	public Object execute(ExecutionEvent event) throws ExecutionException {
		String projectFile = event.getParameter(PROJECT_FILE);
		String projectName = event.getParameter(PROJECT_NAME);
		if (projectFile != null && projectName != null) {
			try {
				// Shell shell =
				// PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell
				// ();
				// SampleProjectImporter importer = new
				// SampleProjectImporter(projectFile, projectName, shell);
				// importer.importProject();
			}
			catch (OperationCanceledException e) {
				return Status.CANCEL_STATUS;
			}
			// catch (CoreException e) {
			// HelpPlugin.log(e.getStatus());
			// }
		}

		return Status.OK_STATUS;
	}

}