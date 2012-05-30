/******************************************************************************************
 * Copyright (c) 2008 - 2010 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.wizard.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.jface.fieldassist.IContentProposal;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMDocument;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMElement;
import org.springframework.ide.eclipse.beans.ui.editor.contentassist.IContentAssistContext;
import org.w3c.dom.Document;

import com.springsource.sts.config.core.contentassist.providers.PropertyNameContentProposalProvider;

/**
 * Wrapper class for PropertyNameContentProposalProvider to work with bean
 * wizard.
 * @author Terry Denney
 * @author Leo Dos Santos
 * @author Christian Dupuis
 * @since 2.0
 */
@SuppressWarnings("restriction")
public class WizardPropertyNameContentProposalProvider extends PropertyNameContentProposalProvider {

	private final IFile file;

	private final Document document;

	private final Set<String> definedProperties;

	public WizardPropertyNameContentProposalProvider(IDOMElement newProperty, String attributeName, IFile beanFile,
			IDOMDocument originalDocument, Set<String> definedProperties) {
		super(newProperty, attributeName);
		this.file = beanFile;
		this.document = originalDocument;
		this.definedProperties = definedProperties;
	}

	@Override
	protected IContentAssistContext createContentAssistContext(String contents) {
		return new WizardContentAssistContext(getInput(), getAttributeName(), file, contents, document);
	}

	@Override
	public IContentProposal[] getProposals(String contents, int position) {
		IContentProposal[] proposals = super.getProposals(contents, position);

		List<IContentProposal> filteredProposals = new ArrayList<IContentProposal>();
		for (IContentProposal proposal : proposals) {
			String content = proposal.getContent();
			if (!definedProperties.contains(content)) {
				filteredProposals.add(proposal);
			}
		}

		return filteredProposals.toArray(new IContentProposal[filteredProposals.size()]);
	}
}
