/******************************************************************************************
 * Copyright (c) 2009 - 2010 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.tests;

import java.io.IOException;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;
import org.springsource.ide.eclipse.commons.tests.util.StsTestCase;

import com.springsource.sts.config.ui.editors.AbstractConfigEditor;

/**
 * Derived from AbstractBeansCoreTestCase
 * @author Leo Dos Santos
 * @author Terry Denney
 * @author Christian Dupuis
 * @author Steffen Pingel
 */
public class AbstractConfigTestCase extends StsTestCase {

	public AbstractConfigEditor cEditor;

	@Override
	protected String getBundleName() {
		return "com.springsource.sts.config.tests";
	}

	protected AbstractConfigEditor openFileInEditor(String path) throws CoreException, IOException {
		IProject project = createPredefinedProject("ConfigTests");
		IFile file = project.getFile(path);
		if (file.exists()) {
			IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
			if (window != null) {
				IWorkbenchPage page = window.getActivePage();
				if (page != null) {
					IEditorPart editor = IDE.openEditor(page, file);
					if (editor != null) {
						return ((AbstractConfigEditor) editor);
					}
				}
			}
		}
		return null;
	}

}
