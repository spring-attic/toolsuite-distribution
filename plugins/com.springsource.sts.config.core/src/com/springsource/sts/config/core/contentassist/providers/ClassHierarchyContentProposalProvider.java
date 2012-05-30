/******************************************************************************************
 * Copyright (c) 2008 - 2009 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.core.contentassist.providers;

import org.eclipse.wst.xml.core.internal.provisional.document.IDOMElement;
import org.springframework.ide.eclipse.beans.ui.editor.contentassist.ClassHierachyContentAssistCalculator;
import org.springframework.ide.eclipse.beans.ui.editor.contentassist.IContentAssistCalculator;

import com.springsource.sts.config.core.contentassist.XmlBackedContentProposalProvider;

/**
 * An {@link XmlBackedContentProposalProvider} that uses
 * {@link ClassHierachyContentAssistCalculator} as its content assist
 * calculator.
 * @author Leo Dos Santos
 * @author Christian Dupuis
 * @since 2.0.0
 */
@SuppressWarnings("restriction")
public class ClassHierarchyContentProposalProvider extends XmlBackedContentProposalProvider {

	private final String className;

	/**
	 * Constructs a content proposal provider for an XML attribute. Generates a
	 * list of classes that inherit from the given class name.
	 * 
	 * @param input the XML element to serve as the model for this proposal
	 * provider
	 * @param attrName the name of the attribute to compute proposals for
	 * @param className the name of the root class
	 */
	public ClassHierarchyContentProposalProvider(IDOMElement input, String attrName, String className) {
		super(input, attrName);
		this.className = className;
	}

	@Override
	protected IContentAssistCalculator createContentAssistCalculator() {
		return new ClassHierachyContentAssistCalculator(className);
	}

}
