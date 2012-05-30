/******************************************************************************************
 * Copyright (c) 2008 - 2010 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.wizard.core;

import org.eclipse.core.resources.IFile;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMDocument;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMNode;
import org.springframework.ide.eclipse.beans.ui.editor.contentassist.IContentAssistContext;
import org.w3c.dom.Attr;

import com.springsource.sts.quickfix.AbstractContentAssistConverter;

/**
 * Content assist converter that is used to validate bean attributes and
 * property attributes.
 * @author Terry Denney
 * @author Leo Dos Santos
 * @author Christian Dupuis
 * @author Steffen Pingel
 * @since 2.0
 */
@SuppressWarnings("restriction")
public class WizardContentAssistConverter extends AbstractContentAssistConverter {

	private final IDOMDocument originalDocument;

	public WizardContentAssistConverter(IDOMNode node, Attr attribute, IFile file, IDOMDocument originalDocument) {
		super(node, attribute.getName(), file);
		this.originalDocument = originalDocument;
	}

	@Override
	protected IContentAssistContext createContext(IDOMNode node, String attributeName, IFile file, String prefix) {
		return new WizardContentAssistContext(node, attributeName, file, prefix, originalDocument);
	}

}
