/******************************************************************************************
 * Copyright (c) 2010 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.quickfix.processors;

import org.eclipse.core.resources.IProject;
import org.eclipse.jdt.core.IType;
import org.eclipse.jface.text.contentassist.ICompletionProposal;
import org.eclipse.jface.text.quickassist.IQuickAssistInvocationContext;
import org.springframework.ide.eclipse.core.java.JdtUtils;

import com.springsource.sts.quickfix.proposals.RemoveDeprecatedQuickFixProposal;

/**
 * @author Terry Denney
 */
public class ClassDeprecatedQuickAssistProcessor extends BeanQuickAssistProcessor {

	private final String className;

	private final IProject project;

	public ClassDeprecatedQuickAssistProcessor(int offset, int length, String text, boolean missingEndQuote,
			String className, IProject project) {
		super(offset, length, text, missingEndQuote);
		this.className = className;
		this.project = project;
	}

	public ICompletionProposal[] computeQuickAssistProposals(IQuickAssistInvocationContext invocationContext) {
		IType type = JdtUtils.getJavaType(project.getProject(), className);
		if (!type.isReadOnly()) {
			return new ICompletionProposal[] { new RemoveDeprecatedQuickFixProposal(offset, length, missingEndQuote,
					className, type) };
		}
		return new ICompletionProposal[0];
	}

}
