/******************************************************************************************
 * Copyright (c) 2010 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.quickfix.processors;

import org.eclipse.jface.text.contentassist.ICompletionProposal;
import org.eclipse.jface.text.quickassist.IQuickAssistInvocationContext;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMNode;

import com.springsource.sts.quickfix.proposals.AddFactoryMethodQuickFixProposal;

/**
 * @author Terry Denney
 */
public class MissingFactoryMethodAttributeQuickAssistProcessor extends BeanQuickAssistProcessor {

	private final IDOMNode beanNode;

	public MissingFactoryMethodAttributeQuickAssistProcessor(int offset, int length, String text,
			boolean missingEndQuote, IDOMNode beanNode) {
		super(offset, length, text, missingEndQuote);
		this.beanNode = beanNode;
	}

	public ICompletionProposal[] computeQuickAssistProposals(IQuickAssistInvocationContext invocationContext) {
		return new ICompletionProposal[] { new AddFactoryMethodQuickFixProposal(offset, length, missingEndQuote,
				beanNode) };
	}

}
