package com.springsource.sts.quickfix.tests;

import java.io.IOException;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.internal.ui.javaeditor.CompilationUnitEditor;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;
import org.springsource.ide.eclipse.commons.tests.util.StsTestCase;

import com.springsource.sts.config.ui.editors.AbstractConfigEditor;

public class AbstractCompilationUnitTestCase extends StsTestCase {

	public AbstractConfigEditor cuEditor;

	@Override
	protected String getBundleName() {
		return "com.springsource.sts.quickfix.tests";
	}

	@SuppressWarnings("restriction")
	protected CompilationUnitEditor openFileInEditor(IFile file) throws CoreException, IOException {

		if (file.exists()) {
			IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
			if (window != null) {
				IWorkbenchPage page = window.getActivePage();
				if (page != null) {
					IEditorPart editor = IDE.openEditor(page, file);
					if (editor != null) {
						return ((CompilationUnitEditor) editor);
					}
				}
			}
		}
		return null;
	}
}
