/******************************************************************************************
 * Copyright (c) 2009 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.quickfix.proposals.tests;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;
import org.eclipse.wst.sse.core.internal.provisional.text.ITextRegion;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMNode;

import com.springsource.sts.quickfix.tests.AbstractQuickfixTestCase;

/**
 * Abstract test case with set up and tear down for editing a bean config file
 * @author Terry Denney
 * @author Leo Dos Santos
 * @author Christian Dupuis
 */
@SuppressWarnings("restriction")
public class AbstractBeanFileQuickfixTestCase extends AbstractQuickfixTestCase {

	protected int getLength(ITextRegion valueRegion, boolean isMissingEndQuote) {
		int length = valueRegion.getLength();

		if (isMissingEndQuote) {
			return length - 1;
		}
		else {
			return length - 2;
		}
	}

	protected int getOffset(ITextRegion valueRegion, IDOMNode beanNode) {
		return valueRegion.getStart() + beanNode.getStartOffset() + 1;
	}

	protected IEditorPart openBeanEditor(String fileName) throws Exception {
		copyProjectCreateDocument(fileName);

		String baseLocation = model.getBaseLocation();
		IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
		IPath filePath = new Path(baseLocation);
		IFile beanFile = root.getFile(filePath);

		return IDE.openEditor(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage(), beanFile);
	}

}
