/******************************************************************************************
 * Copyright (c) 2008 - 2010 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.quickfix.processors;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jface.text.contentassist.ICompletionProposal;
import org.eclipse.jface.text.quickassist.IQuickAssistInvocationContext;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMNode;
import org.springframework.ide.eclipse.beans.ui.editor.contentassist.IContentAssistCalculator;

import com.springsource.sts.quickfix.ContentAssistProposalWrapper;
import com.springsource.sts.quickfix.QuickfixContentAssistConverter;
import com.springsource.sts.quickfix.QuickfixUtils;
import com.springsource.sts.quickfix.proposals.RenameToSimilarNameQuickFixProposal;

/**
 * Quick assist processor for factory, init or destroy method attribute in beans
 * XML editor.
 * @author Terry Denney
 * @author Leo Dos Santos
 * @author Christian Dupuis
 * @since 2.0
 */
public class MethodAttributeQuickAssistProcessor extends BeanQuickAssistProcessor {

	private final IDOMNode parentNode;

	private final IJavaProject javaProject;

	private final String className;

	private final boolean isStatic;

	private final IContentAssistCalculator calculator;

	private final IFile file;

	private final String attributeName;

	public MethodAttributeQuickAssistProcessor(int offset, int length, String className, String text,
			boolean missingEndQuote, IDOMNode parentNode, String attributeName, IProject project, boolean isStatic,
			IContentAssistCalculator calculator, IFile file) {
		super(offset, length, text, missingEndQuote);
		this.parentNode = parentNode;
		this.attributeName = attributeName;
		this.javaProject = JavaCore.create(project);
		this.className = className;
		this.isStatic = isStatic;
		this.calculator = calculator;
		this.file = file;
	}

	public ICompletionProposal[] computeQuickAssistProposals(IQuickAssistInvocationContext invocationContext) {
		List<ICompletionProposal> proposals = new ArrayList<ICompletionProposal>();

		Set<ContentAssistProposalWrapper> matchedProposals = new QuickfixContentAssistConverter(parentNode,
				attributeName, file).getMethodProposals(text, false, calculator);
		for (ContentAssistProposalWrapper proposal : matchedProposals) {
			String methodName = proposal.getName();
			String description = proposal.getDisplayText();
			proposals.add(new RenameToSimilarNameQuickFixProposal(methodName, offset, length, missingEndQuote,
					description));
		}

		if (className != null) {
			ICompletionProposal proposal = QuickfixUtils.getNewMethodQuickFixProposal(text, "Object", new String[0],
					javaProject, className, offset, length, text, missingEndQuote, isStatic, "method");
			if (proposal != null) {
				proposals.add(proposal);
			}
		}

		return proposals.toArray(new ICompletionProposal[proposals.size()]);
	}

}
