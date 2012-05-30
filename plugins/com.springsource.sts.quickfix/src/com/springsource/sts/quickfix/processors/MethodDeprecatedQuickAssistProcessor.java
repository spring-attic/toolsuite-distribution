/******************************************************************************************
 * Copyright (c) 2010 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.quickfix.processors;

import org.eclipse.jdt.core.IMethod;
import org.eclipse.jdt.core.IType;
import org.eclipse.jface.text.contentassist.ICompletionProposal;
import org.eclipse.jface.text.quickassist.IQuickAssistInvocationContext;
import org.springframework.ide.eclipse.core.java.JdtUtils;

import com.springsource.sts.quickfix.proposals.RemoveDeprecatedQuickFixProposal;

/**
 * @author Terry Denney
 */
public class MethodDeprecatedQuickAssistProcessor extends BeanQuickAssistProcessor {

	private final String className;

	private final String methodName;

	private final IMethod method;

	public MethodDeprecatedQuickAssistProcessor(int offset, int length, String text, boolean missingEndQuote,
			String className, String methodName, IMethod method) {
		super(offset, length, text, missingEndQuote);
		this.className = className;
		this.methodName = methodName;
		this.method = method;
	}

	public ICompletionProposal[] computeQuickAssistProposals(IQuickAssistInvocationContext invocationContext) {
		IType type = JdtUtils.getJavaType(method.getJavaProject().getProject(), className);
		if (!type.isReadOnly() && method != null) {

			return new ICompletionProposal[] { new RemoveDeprecatedQuickFixProposal(offset, length, missingEndQuote,
					className, methodName, method) };
		}

		return new ICompletionProposal[0];
	}

}
