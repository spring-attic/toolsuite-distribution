/******************************************************************************************
 * Copyright (c) 2010 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.quickfix.processors;

import java.util.List;

import org.eclipse.jface.text.contentassist.ICompletionProposal;
import org.eclipse.jface.text.quickassist.IQuickAssistInvocationContext;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMNode;

import com.springsource.sts.quickfix.proposals.AddPropertyQuickFixProposal;

/**
 * @author Terry Denney
 */
public class RequiredPropertyQuickAssistProcessor extends BeanQuickAssistProcessor {

	private final List<String> missingProperties;

	private final IDOMNode beanNode;

	public RequiredPropertyQuickAssistProcessor(int offset, int length, String text, boolean missingEndQuote,
			List<String> missingProperties, IDOMNode beanNode) {
		super(offset, length, text, missingEndQuote);
		this.missingProperties = missingProperties;
		this.beanNode = beanNode;
	}

	public ICompletionProposal[] computeQuickAssistProposals(IQuickAssistInvocationContext invocationContext) {
		StringBuilder label = new StringBuilder("Add <property> for ");
		for (int i = 0; i < missingProperties.size(); i++) {
			if (i > 0) {
				label.append(", ");
			}
			label.append(missingProperties.get(i));
		}

		return new ICompletionProposal[] { new AddPropertyQuickFixProposal(offset, length, missingEndQuote,
				missingProperties, beanNode, label.toString()) };
	}
}
