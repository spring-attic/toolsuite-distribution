/******************************************************************************************
 * Copyright (c) 2009 - 2012 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.tests;

import java.io.IOException;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.swtbot.swt.finder.exceptions.WidgetNotFoundException;
import org.eclipse.swtbot.swt.finder.finders.UIThreadRunnable;
import org.eclipse.swtbot.swt.finder.results.Result;
import org.eclipse.swtbot.swt.finder.utils.SWTBotPreferences;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;
import org.springsource.ide.eclipse.commons.tests.util.StsTestUtil;
import org.springsource.ide.eclipse.commons.tests.util.swtbot.StsUiTestCase;

import com.springsource.sts.config.tests.util.StsConfigBot;
import com.springsource.sts.config.ui.editors.AbstractConfigEditor;
import com.springsource.sts.config.ui.editors.SpringConfigEditor;

/**
 * @author Leo Dos Santos
 * @author Steffen Pingel
 */
public abstract class AbstractConfigUiTestCase extends StsUiTestCase {

	public AbstractConfigEditor cEditor;

	protected IProject createPredefinedProject(String projectName) throws CoreException, IOException {
		return StsTestUtil.createPredefinedProject(projectName, getBundleName());
	}

	protected StsConfigBot getBot() {
		return (StsConfigBot) bot;
	}

	protected String getBundleName() {
		return "com.springsource.sts.config.tests";
	}

	protected SpringConfigEditor openFileInEditor(final String path) throws CoreException, IOException {
		// XXX run project setup in UI thread to avoid
		// ConcurrentModificationException
		IProject project = createPredefinedProject("ConfigTests");
		final IFile file = project.getFile(path);
		assertTrue(file.exists());

		return UIThreadRunnable.syncExec(new Result<SpringConfigEditor>() {
			public SpringConfigEditor run() {
				IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
				assertNotNull("Expected active workbench window", window);
				IWorkbenchPage page = window.getActivePage();
				assertNotNull("Expected active workbench page", page);
				IEditorPart editor;
				try {
					editor = IDE.openEditor(page, file);
				}
				catch (PartInitException e) {
					throw new RuntimeException(e);
				}
				assertEquals(SpringConfigEditor.class, editor.getClass());
				return ((SpringConfigEditor) editor);
			}
		});
	}

	@Override
	protected void setUp() throws Exception {
		SWTBotPreferences.KEYBOARD_LAYOUT = "EN_US";
		bot = new StsConfigBot();
		try {
			bot.viewByTitle("Welcome").close();
		}
		catch (WidgetNotFoundException e) {
			// ignore
		}

		// run in setUp() to enable super class to capture screenshot in
		// case of a failure
		cleanUp();
	}

}
