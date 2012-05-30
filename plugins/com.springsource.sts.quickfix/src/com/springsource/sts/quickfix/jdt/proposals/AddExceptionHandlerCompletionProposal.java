/******************************************************************************************
 * Copyright (c) 2011 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.quickfix.jdt.proposals;

import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.Annotation;
import org.eclipse.jdt.core.dom.ArrayInitializer;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.Expression;
import org.eclipse.jdt.core.dom.MarkerAnnotation;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.SimpleName;
import org.eclipse.jdt.core.dom.SingleMemberAnnotation;
import org.eclipse.jdt.core.dom.Type;
import org.eclipse.jdt.core.dom.TypeLiteral;
import org.eclipse.jdt.core.dom.rewrite.ASTRewrite;
import org.eclipse.jdt.internal.ui.text.correction.ASTResolving;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.springsource.sts.quickfix.QuickfixImages;
import com.springsource.sts.quickfix.jdt.util.ProposalCalculatorUtil;

/**
 * @author Terry Denney
 * @since 2.6
 */
public class AddExceptionHandlerCompletionProposal extends AnnotationCompletionProposal {

	private final MethodDeclaration methodDecl;

	private final List<String> exceptionNames;

	public AddExceptionHandlerCompletionProposal(MethodDeclaration methodDecl, List<String> exceptionNames,
			ICompilationUnit cu) {
		super(getDisplayName(exceptionNames), cu, QuickfixImages.getImage(QuickfixImages.ANNOTATION));
		this.methodDecl = methodDecl;
		this.exceptionNames = exceptionNames;
	}

	private static String getDisplayName(List<String> exceptionNames) {
		StringBuilder result = new StringBuilder();

		result.append("Add @ExceptionHandler");

		if (exceptionNames.size() > 0) {
			result.append("(");

			for (int i = 0; i < exceptionNames.size(); i++) {
				if (i > 0) {
					result.append(", ");
				}
				result.append(exceptionNames.get(i));
			}

			result.append(")");
		}

		return result.toString();
	}

	@SuppressWarnings("unchecked")
	@Override
	protected ASTRewrite getRewrite() throws CoreException {
		CompilationUnit astRoot = ASTResolving.findParentCompilationUnit(methodDecl);
		ASTRewrite astRewrite = ASTRewrite.create(astRoot.getAST());

		String importName = ExceptionHandler.class.getCanonicalName();
		if (!ProposalCalculatorUtil.containsImport(getCompilationUnit(), importName)) {
			createImportRewrite(astRoot).addImport(importName);
		}

		AST ast = astRewrite.getAST();

		Annotation annotation;
		if (exceptionNames.isEmpty()) {
			MarkerAnnotation mAnnotation = ast.newMarkerAnnotation();
			annotation = mAnnotation;
		}
		else {
			SingleMemberAnnotation sAnnotation = ast.newSingleMemberAnnotation();
			annotation = sAnnotation;
			Expression value;
			if (exceptionNames.size() == 1) {
				TypeLiteral typeLiteral = getTypeLiteral(exceptionNames.get(0), ast);
				value = typeLiteral;
				addLinkedPosition(astRewrite.track(typeLiteral.getType()), true, "ExceptionHandler");
			}
			else {
				ArrayInitializer arrayInitializer = ast.newArrayInitializer();
				List<Expression> expressions = arrayInitializer.expressions();
				for (int i = 0; i < exceptionNames.size(); i++) {
					String exceptionName = exceptionNames.get(i);
					TypeLiteral typeLiteral = getTypeLiteral(exceptionName, ast);
					addLinkedPosition(astRewrite.track(typeLiteral.getType()), i == 0, "ExceptionHandler" + i);
					expressions.add(typeLiteral);
				}

				value = arrayInitializer;
			}
			sAnnotation.setValue(value);
		}

		SimpleName name = ast.newSimpleName("ExceptionHandler");
		annotation.setTypeName(name);

		astRewrite.getListRewrite(methodDecl, MethodDeclaration.MODIFIERS2_PROPERTY).insertFirst(annotation, null);

		return astRewrite;
	}

	private TypeLiteral getTypeLiteral(String exceptionName, AST ast) {
		TypeLiteral typeLiteral = ast.newTypeLiteral();
		SimpleName typeName = ast.newSimpleName(exceptionName);
		Type type = ast.newSimpleType(typeName);
		typeLiteral.setType(type);
		return typeLiteral;
	}

}
