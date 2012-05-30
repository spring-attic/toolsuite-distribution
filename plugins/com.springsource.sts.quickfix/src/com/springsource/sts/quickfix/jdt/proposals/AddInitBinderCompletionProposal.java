/******************************************************************************************
 * Copyright (c) 2011 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.quickfix.jdt.proposals;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.MarkerAnnotation;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.SimpleName;
import org.eclipse.jdt.core.dom.rewrite.ASTRewrite;
import org.eclipse.jdt.internal.ui.text.correction.ASTResolving;
import org.springframework.web.bind.annotation.InitBinder;

import com.springsource.sts.quickfix.QuickfixImages;
import com.springsource.sts.quickfix.jdt.util.ProposalCalculatorUtil;

/**
 * @author Terry Denney
 * @since 2.6
 */
public class AddInitBinderCompletionProposal extends AnnotationCompletionProposal {

	private final MethodDeclaration methodDecl;

	public AddInitBinderCompletionProposal(MethodDeclaration methodDecl, ICompilationUnit cu) {
		super("Add @InitBinder", cu, QuickfixImages.getImage(QuickfixImages.ANNOTATION));
		this.methodDecl = methodDecl;
	}

	@Override
	protected ASTRewrite getRewrite() throws CoreException {
		CompilationUnit astRoot = ASTResolving.findParentCompilationUnit(methodDecl);
		ASTRewrite astRewrite = ASTRewrite.create(astRoot.getAST());

		String importName = InitBinder.class.getCanonicalName();
		if (!ProposalCalculatorUtil.containsImport(getCompilationUnit(), importName)) {
			createImportRewrite(astRoot).addImport(importName);
		}

		AST ast = astRewrite.getAST();

		MarkerAnnotation annotation = ast.newMarkerAnnotation();
		SimpleName name = ast.newSimpleName("InitBinder");
		annotation.setTypeName(name);

		astRewrite.getListRewrite(methodDecl, MethodDeclaration.MODIFIERS2_PROPERTY).insertFirst(annotation, null);

		return astRewrite;
	}

}
