/******************************************************************************************
 * Copyright (c) 2010 - 2011 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.quickfix.jdt.processors;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.SimpleName;
import org.eclipse.jdt.core.dom.TypeDeclaration;
import org.eclipse.jdt.ui.text.java.IInvocationContext;
import org.eclipse.jdt.ui.text.java.IJavaCompletionProposal;

import com.springsource.sts.quickfix.jdt.proposals.AddControllerCompletionProposal;
import com.springsource.sts.quickfix.jdt.util.ProposalCalculatorUtil;

/**
 * @author Terry Denney
 * @since 2.6
 */
public class ControllerAnnotationQuickAssistProcessor extends AbstractAnnotationQuickAssistProcessor {

	@Override
	public String getAnnotationName() {
		return "Controller";
	}

	@Override
	public List<IJavaCompletionProposal> getAssistsForMethod(MethodDeclaration methodDecl, SimpleName name,
			ICompilationUnit cu) {
		List<IJavaCompletionProposal> proposals = new ArrayList<IJavaCompletionProposal>();
		proposals.add(new AddControllerCompletionProposal(getTypeDeclaration(methodDecl), cu, false));
		return proposals;
	}

	@Override
	public List<IJavaCompletionProposal> getAssistsForType(TypeDeclaration typeDecl, SimpleName name,
			ICompilationUnit cu) {
		List<IJavaCompletionProposal> proposals = new ArrayList<IJavaCompletionProposal>();
		proposals.add(new AddControllerCompletionProposal(typeDecl, cu, true));
		return proposals;
	}

	@Override
	public boolean isQuickfixAvailable(MethodDeclaration methodDecl, IInvocationContext context) {
		if (isControllerNeeded(methodDecl)) {
			TypeDeclaration typeDecl = getTypeDeclaration(methodDecl);
			if (typeDecl != null) {
				return !ProposalCalculatorUtil.hasAnnotation("Controller", typeDecl);
			}
		}

		return false;
	}

	@Override
	public boolean isQuickfixAvailable(TypeDeclaration typeDecl, IInvocationContext context) {
		if (ProposalCalculatorUtil.hasAnnotation("Controller", typeDecl)) {
			return false;
		}

		if (ProposalCalculatorUtil.hasAnnotation("RequestMapping", typeDecl)) {
			return true;
		}

		MethodDeclaration[] methodDecls = typeDecl.getMethods();
		for (MethodDeclaration methodDecl : methodDecls) {
			if (isControllerNeeded(methodDecl)) {
				return true;
			}
		}

		return false;
	}

	private TypeDeclaration getTypeDeclaration(ASTNode node) {
		if (node == null || node instanceof TypeDeclaration) {
			return (TypeDeclaration) node;
		}
		return getTypeDeclaration(node.getParent());
	}

	private boolean isControllerNeeded(MethodDeclaration methodDecl) {
		return ProposalCalculatorUtil.hasAnnotation("RequestMapping", methodDecl)
				|| ProposalCalculatorUtil.hasAnnotation("InitBinder", methodDecl)
				|| ProposalCalculatorUtil.hasAnnotation("ExceptionHandler", methodDecl);
	}
}
