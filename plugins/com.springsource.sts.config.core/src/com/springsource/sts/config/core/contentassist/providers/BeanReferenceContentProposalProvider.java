/******************************************************************************************
 * Copyright (c) 2008 - 2009 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.core.contentassist.providers;

import org.eclipse.wst.xml.core.internal.provisional.document.IDOMElement;
import org.springframework.ide.eclipse.beans.ui.editor.contentassist.BeanReferenceContentAssistCalculator;
import org.springframework.ide.eclipse.beans.ui.editor.contentassist.IContentAssistCalculator;

import com.springsource.sts.config.core.contentassist.XmlBackedContentProposalProvider;

/**
 * An {@link XmlBackedContentProposalProvider} that uses
 * {@link BeanReferenceContentAssistCalculator} as its content assist
 * calculator.
 * @author Leo Dos Santos
 * @author Christian Dupuis
 * @since 2.0.0
 */
@SuppressWarnings("restriction")
public class BeanReferenceContentProposalProvider extends XmlBackedContentProposalProvider {

	private final boolean showExternal;

	/**
	 * Constructs a content proposal provider for an XML attribute. Shows beans
	 * that are not located in the current file. Use
	 * {@link #BeanReferenceContentProposalProvider(IDOMElement, String, boolean)}
	 * to exclude beans that are not in the current file.
	 * 
	 * @param input the XML element to serve as the model for this proposal
	 * provider
	 * @param attrName the name of the attribute to compute proposals for
	 */
	public BeanReferenceContentProposalProvider(IDOMElement input, String attrName) {
		this(input, attrName, true);
	}

	/**
	 * Constructs a content proposal provider for an XML attribute.
	 * 
	 * @param input the XML element to serve as the model for this proposal
	 * provider
	 * @param attrName the name of the attribute to compute proposals for
	 * @param showExternal true if those beans should be displayed that are not
	 * located in the current file
	 */
	public BeanReferenceContentProposalProvider(IDOMElement input, String attrName, boolean showExternal) {
		super(input, attrName);
		this.showExternal = showExternal;
	}

	@Override
	protected IContentAssistCalculator createContentAssistCalculator() {
		return new BeanReferenceContentAssistCalculator(showExternal);
	}

}
