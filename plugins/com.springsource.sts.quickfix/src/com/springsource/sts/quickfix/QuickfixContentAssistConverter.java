/******************************************************************************************
 * Copyright (c) 2008 - 2010 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.quickfix;

import org.eclipse.core.resources.IFile;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMNode;
import org.springframework.ide.eclipse.beans.ui.editor.contentassist.IContentAssistContext;

/**
 * Default content assist converter for generating IContentAssistContext
 * @author Terry Denney
 * @author Leo Dos Santos
 * @author Christian Dupuis
 * @since 2.0
 */
public class QuickfixContentAssistConverter extends AbstractContentAssistConverter {

	public QuickfixContentAssistConverter(IDOMNode node, String attributeName, IFile file) {
		super(node, attributeName, file);
	}

	@Override
	protected IContentAssistContext createContext(IDOMNode node, String attributeName, IFile file, String prefix) {
		return new QuickFixContentAssistContext(attributeName, file, node, prefix);
	}

}
