/******************************************************************************************
 * Copyright (c) 2010 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.quickfix.processors;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.jface.text.contentassist.ICompletionProposal;
import org.eclipse.jface.text.quickassist.IQuickAssistInvocationContext;

import com.springsource.sts.quickfix.proposals.RenameMethodQuickfixProposal;

/**
 * @author Terry Denney
 */
public class RenameMethodQuickAssistProcessor extends BeanQuickAssistProcessor {

	private final RenameMethodQuickfixProposal proposal;

	public RenameMethodQuickAssistProcessor(int offset, int length, String className, String text, IProject project,
			boolean missingEndQuote, IFile beanFile) {
		super(offset, length, text, missingEndQuote);

		this.proposal = new RenameMethodQuickfixProposal(offset, length, text, className, missingEndQuote, beanFile,
				project);
	}

	public ICompletionProposal[] computeQuickAssistProposals(IQuickAssistInvocationContext invocationContext) {
		return new ICompletionProposal[] { proposal };
	}

}
