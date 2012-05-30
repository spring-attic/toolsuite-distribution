/******************************************************************************************
 * Copyright (c) 2010 - 2011 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.quickfix.jdt.proposals;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.BodyDeclaration;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.SimpleName;
import org.eclipse.jdt.core.dom.SingleMemberAnnotation;
import org.eclipse.jdt.core.dom.StringLiteral;
import org.eclipse.jdt.core.dom.rewrite.ASTRewrite;
import org.eclipse.jdt.core.dom.rewrite.ImportRewrite;
import org.eclipse.jdt.internal.ui.text.correction.ASTResolving;
import org.eclipse.jdt.ui.text.java.JavaContentAssistInvocationContext;
import org.springframework.beans.factory.annotation.Qualifier;

import com.springsource.sts.quickfix.QuickfixImages;
import com.springsource.sts.quickfix.jdt.util.ProposalCalculatorUtil;

/**
 * @author Terry Denney
 * @since 2.6
 */
public class QualifierCompletionProposal extends AnnotationCompletionProposal {

	private final BodyDeclaration decl;

	private final ASTNode annotationNode;

	// private final String matchingBean;

	public QualifierCompletionProposal(ASTNode annotationNode, BodyDeclaration decl,
			JavaContentAssistInvocationContext javaContext) {
		super("Qualifier(\"\") - " + Qualifier.class.getCanonicalName(), javaContext.getCompilationUnit(),
				QuickfixImages.getImage(QuickfixImages.ANNOTATION));
		this.annotationNode = annotationNode;
		this.decl = decl;
	}

	@Override
	protected ASTRewrite getRewrite() throws CoreException {
		AST ast = decl.getAST();
		ASTRewrite rewrite = ASTRewrite.create(ast);
		String qualifierTypeName = Qualifier.class.getCanonicalName();
		if (!ProposalCalculatorUtil.containsImport(getCompilationUnit(), qualifierTypeName)) {
			CompilationUnit astRoot = ASTResolving.findParentCompilationUnit(decl);
			ImportRewrite importRewrite = createImportRewrite(astRoot);
			importRewrite.addImport(qualifierTypeName);
		}

		SingleMemberAnnotation annotation = ast.newSingleMemberAnnotation();
		SimpleName typeName = ast.newSimpleName("Qualifier");
		StringLiteral beanValue = ast.newStringLiteral();
		// beanValue.setLiteralValue("beanName");
		annotation.setTypeName(typeName);
		// SimpleName beanValue = ast.newSimpleName("beanName");
		annotation.setValue(beanValue);

		setTrackPosition(new StringLiteralTrackedPosition(rewrite.track(beanValue)));

		rewrite.replace(annotationNode, annotation, null);

		return rewrite;
	}

}
