/******************************************************************************************
 * Copyright (c) 2008 - 2009 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.core.contentassist.providers;

import org.eclipse.wst.xml.core.internal.provisional.document.IDOMElement;
import org.springframework.ide.eclipse.beans.ui.editor.contentassist.IContentAssistCalculator;
import org.springframework.ide.eclipse.beans.ui.editor.contentassist.PropertyBeanReferenceContentAssistCalculator;

import com.springsource.sts.config.core.contentassist.XmlBackedContentProposalProvider;

/**
 * An {@link XmlBackedContentProposalProvider} that uses
 * {@link PropertyBeanReferenceContentAssistCalculator} as its content assist
 * calculator.
 * @author Leo Dos Santos
 * @author Christian Dupuis
 * @since 2.0.0
 */
@SuppressWarnings("restriction")
public class PropertyBeanReferenceContentProposalProvider extends XmlBackedContentProposalProvider {

	/**
	 * Constructs a content proposal provider for an XML attribute.
	 * 
	 * @param input the XML element to serve as the model for this proposal
	 * provider
	 * @param attrName the name of the attribute to compute proposals for
	 */
	public PropertyBeanReferenceContentProposalProvider(IDOMElement input, String attrName) {
		super(input, attrName);
	}

	@Override
	protected IContentAssistCalculator createContentAssistCalculator() {
		return new PropertyBeanReferenceContentAssistCalculator();
	}

}
