/******************************************************************************************
 * Copyright (c) 2010 - 2012 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.quickfix.jdt.computers;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jdt.internal.ui.text.java.JavaCompletionProposalComputer;
import org.eclipse.jdt.ui.text.java.ContentAssistInvocationContext;
import org.eclipse.jface.text.contentassist.ICompletionProposal;

/**
 * @author Terry Denney
 * @author Martin Lippert
 * @since 2.6
 */
public class AnnotationArgumentProposalComputer extends JavaCompletionProposalComputer {

	@Override
	public List<ICompletionProposal> computeCompletionProposals(ContentAssistInvocationContext context,
			IProgressMonitor monitor) {
		List<ICompletionProposal> proposals = new ArrayList<ICompletionProposal>();
		for (JavaCompletionProposalComputer computer : AnnotationComputerRegistry.computers) {

			List<ICompletionProposal> completionProposals = computer.computeCompletionProposals(context, monitor);
			if (completionProposals != null) {
				proposals.addAll(completionProposals);
			}
		}
		return proposals;
	}

}
