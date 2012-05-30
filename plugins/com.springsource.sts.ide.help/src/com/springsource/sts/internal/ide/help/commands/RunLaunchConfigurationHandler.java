/******************************************************************************************
 * Copyright (c) 2008 - 2009 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.internal.ide.help.commands;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchManager;
import org.eclipse.debug.ui.DebugUITools;
import org.springsource.ide.eclipse.commons.ui.ICoreRunnable;
import org.springsource.ide.eclipse.commons.ui.UiUtil;


/**
 * @author Steffen Pingel
 * @author Leo Dos Santos
 * @author Christian Dupuis
 */
public class RunLaunchConfigurationHandler extends AbstractHandler {

	private static final String ID_PARAMETER_FILE_NAME = "fileName";

	private static final String ID_PARAMETER_MODE = "mode";

	public Object execute(ExecutionEvent event) throws ExecutionException {
		final String fileName = event.getParameter(ID_PARAMETER_FILE_NAME);
		if (fileName == null) {
			throw new ExecutionException("Required fileName parameter not specified");
		}

		final String mode;
		if (event.getParameter(ID_PARAMETER_MODE) != null) {
			mode = event.getParameter(ID_PARAMETER_MODE);
		}
		else {
			mode = ILaunchManager.RUN_MODE;
		}

		IFile file = ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(fileName));

		final ILaunchConfiguration config = DebugPlugin.getDefault().getLaunchManager().getLaunchConfiguration(file);
		if (config != null && config.exists()) {
			try {
				if (!config.supportsMode(mode)) {
					throw new ExecutionException("Invalid mode \"" + mode + "\" specified for launch configuration \""
							+ fileName + "\"");
				}

				final ILaunch launch[] = new ILaunch[1];
				UiUtil.busyCursorWhile(new ICoreRunnable() {
					public void run(IProgressMonitor monitor) throws CoreException {
						launch[0] = DebugUITools.buildAndLaunch(config, mode, monitor);
					}

				});
			}
			catch (CoreException e) {
				throw new ExecutionException("Could not launch applicaton", e);
			}
		}

		return null;
	}

}
