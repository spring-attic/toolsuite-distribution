/******************************************************************************************
 * Copyright (c) 2011 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.quickfix.jdt.proposals;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.Annotation;
import org.eclipse.jdt.core.dom.rewrite.ASTRewrite;

import com.springsource.sts.quickfix.QuickfixImages;

/**
 * @author Terry Denney
 * @since 2.6
 */
public class RemoveQualifierCompletionProposal extends MarkerResolutionProposal {

	private final Annotation annotation;

	public RemoveQualifierCompletionProposal(Annotation annotation, ICompilationUnit cu) {
		super("Remove @Qualifier", cu, QuickfixImages.getImage(QuickfixImages.REMOVE_CORRECTION));
		this.annotation = annotation;
	}

	@Override
	protected ASTRewrite getRewrite() throws CoreException {
		AST ast = annotation.getAST();
		ASTRewrite astRewrite = ASTRewrite.create(ast);

		astRewrite.remove(annotation, null);

		return astRewrite;
	}

}
