/******************************************************************************************
 * Copyright (c) 2008 - 2009 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.internal.ide.help.commands;

import java.io.File;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.NotEnabledException;
import org.eclipse.core.commands.common.NotDefinedException;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.Status;
import org.eclipse.swt.dnd.Clipboard;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.ui.handlers.IHandlerService;
import org.springsource.ide.eclipse.commons.core.FileUtil;
import org.springsource.ide.eclipse.commons.core.StatusHandler;
import org.springsource.ide.eclipse.commons.ui.ICoreRunnable;

import com.springsource.sts.internal.ide.help.HelpPlugin;
import com.springsource.sts.internal.ide.help.tutorial.TutorialManager;

/**
 * @author Steffen Pingel
 * @author Leo Dos Santos
 * @author Christian Dupuis
 */
public class PasteFileHandler extends AbstractHandler {

	private static final String ID_COMMAND_PASTE = "org.eclipse.ui.edit.paste";

	private static final String ID_PARAMETER_FILE_NAME = "fileName";

	public Object execute(ExecutionEvent event) throws ExecutionException {
		IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindowChecked(event);

		final String fileName = event.getParameter(ID_PARAMETER_FILE_NAME);
		if (fileName != null) {
			final String[] text = new String[1];
			try {
				org.springsource.ide.eclipse.commons.ui.UiUtil.busyCursorWhile(new ICoreRunnable() {
					public void run(IProgressMonitor monitor) throws CoreException {
						TutorialManager tutorialManager = TutorialManager.getActiveTutorial();
						File file = tutorialManager.getTutorialData().getFile(fileName);
						text[0] = FileUtil.readFile(file, new NullProgressMonitor());
					}
				});
			}
			catch (OperationCanceledException e) {
				return null;
			}
			catch (CoreException e) {
				throw new ExecutionException("Could not read file", e);
			}

			Clipboard clipboard = new Clipboard(window.getShell().getDisplay());
			clipboard.setContents(text, new Transfer[] { TextTransfer.getInstance() });
		}

		IEditorPart editor = HandlerUtil.getActiveEditorChecked(event);
		// if (editor == null) {
		// StatusHandler.log(new Status(IStatus.WARNING, HelpPlugin.PLUGIN_ID,
		// "Could not paste file: no active workbench window"));
		// return null;
		// }
		editor.setFocus();

		IHandlerService handlerService = (IHandlerService) window.getService(IHandlerService.class);
		try {
			handlerService.executeCommand(ID_COMMAND_PASTE, null);
		}
		catch (NotDefinedException e) {
			StatusHandler.log(new Status(IStatus.ERROR, HelpPlugin.PLUGIN_ID, "Paste command not defined", e));
		}
		catch (NotEnabledException e1) {
			throw new ExecutionException("The current editor does not support pasting");
		}
		catch (org.eclipse.core.commands.NotHandledException e) {
			throw new ExecutionException("The paste command failed", e);
		}

		return null;
	}
}
