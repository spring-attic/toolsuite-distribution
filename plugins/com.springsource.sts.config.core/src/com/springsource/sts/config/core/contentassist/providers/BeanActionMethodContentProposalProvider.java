/******************************************************************************************
 * Copyright (c) 2010 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.core.contentassist.providers;

import org.eclipse.wst.xml.core.internal.provisional.document.IDOMElement;
import org.springframework.ide.eclipse.beans.ui.editor.contentassist.IContentAssistCalculator;
import org.springframework.ide.eclipse.webflow.ui.editor.contentassist.webflow.BeanActionMethodContentAssistCalculator;
import org.springframework.ide.eclipse.webflow.ui.editor.contentassist.webflow.BeanMethodContentAssistCalculator;

import com.springsource.sts.config.core.contentassist.XmlBackedContentProposalProvider;

/**
 * An {@link XmlBackedContentProposalProvider} that uses
 * {@link BeanMethodContentAssistCalculator} as its content assist calculator.
 * @author Leo Dos Santos
 * @since 2.3.4
 */
@SuppressWarnings("restriction")
public class BeanActionMethodContentProposalProvider extends XmlBackedContentProposalProvider {

	/**
	 * Constructs a content proposal provider for an XML attribute.
	 * 
	 * @param input the XML element to server as the model of this proposal
	 * provider
	 * @param attrName the name of the attribute to compute proposals for
	 */
	public BeanActionMethodContentProposalProvider(IDOMElement input, String attrName) {
		super(input, attrName);
	}

	@Override
	protected IContentAssistCalculator createContentAssistCalculator() {
		return new BeanActionMethodContentAssistCalculator();
	}

}
