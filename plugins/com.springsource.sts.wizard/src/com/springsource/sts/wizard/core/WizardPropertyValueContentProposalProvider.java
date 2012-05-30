/******************************************************************************************
 * Copyright (c) 2008 - 2010 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.wizard.core;

import org.eclipse.core.resources.IFile;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMDocument;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMElement;
import org.springframework.ide.eclipse.beans.ui.editor.contentassist.IContentAssistContext;
import org.w3c.dom.Document;

import com.springsource.sts.config.core.contentassist.providers.PropertyValueContentProposalProvider;

/**
 * Wrapper class for PropertyValueContentProposalProvider to work with bean
 * wizard.
 * @author Terry Denney
 * @author Leo Dos Santos
 * @author Christian Dupuis
 * @since 2.0
 */
@SuppressWarnings("restriction")
public class WizardPropertyValueContentProposalProvider extends PropertyValueContentProposalProvider {

	private final IFile file;

	private final Document document;

	public WizardPropertyValueContentProposalProvider(IDOMElement newProperty, String attributeName, IFile beanFile,
			IDOMDocument originalDocument) {
		super(newProperty, attributeName);
		this.file = beanFile;
		this.document = originalDocument;
	}

	@Override
	protected IContentAssistContext createContentAssistContext(String contents) {
		return new WizardContentAssistContext(getInput(), getAttributeName(), file, contents, document);
	}
}
