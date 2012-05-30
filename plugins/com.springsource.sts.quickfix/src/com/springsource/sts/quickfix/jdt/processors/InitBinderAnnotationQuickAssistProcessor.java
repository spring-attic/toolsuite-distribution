/******************************************************************************************
 * Copyright (c) 2011 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.quickfix.jdt.processors;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.ITypeBinding;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.PrimitiveType;
import org.eclipse.jdt.core.dom.SimpleName;
import org.eclipse.jdt.core.dom.SingleVariableDeclaration;
import org.eclipse.jdt.core.dom.Type;
import org.eclipse.jdt.core.dom.TypeDeclaration;
import org.eclipse.jdt.ui.text.java.IInvocationContext;
import org.eclipse.jdt.ui.text.java.IJavaCompletionProposal;
import org.springframework.ide.eclipse.beans.core.BeansCorePlugin;
import org.springframework.ide.eclipse.core.java.JdtUtils;
import org.springframework.web.bind.WebDataBinder;

import com.springsource.sts.quickfix.jdt.proposals.AddInitBinderCompletionProposal;
import com.springsource.sts.quickfix.jdt.util.ProposalCalculatorUtil;

/**
 * @author Terry Denney
 * @since 2.6
 */
public class InitBinderAnnotationQuickAssistProcessor extends AbstractAnnotationQuickAssistProcessor {

	@Override
	public String getAnnotationName() {
		return "InitBinder";
	}

	@Override
	public boolean isQuickfixAvailable(FieldDeclaration fieldDecl, IInvocationContext context) {
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean isQuickfixAvailable(MethodDeclaration methodDecl, IInvocationContext context) {
		TypeDeclaration typeDecl = getSurroundingTypeDecl(methodDecl);
		if (typeDecl != null && ProposalCalculatorUtil.hasAnnotation("Controller", typeDecl)) {
			if (!ProposalCalculatorUtil.hasAnnotation("InitBinder", methodDecl)) {
				Type returnType = methodDecl.getReturnType2();
				if (returnType.isPrimitiveType()
						&& ((PrimitiveType) returnType).getPrimitiveTypeCode().equals(PrimitiveType.VOID)) {
					ClassLoader classLoader = JdtUtils.getProjectClassLoaderSupport(
							context.getCompilationUnit().getJavaProject().getProject(),
							BeansCorePlugin.getClassLoader()).getProjectClassLoader();
					Class<?> binderClass;
					try {
						binderClass = classLoader.loadClass(WebDataBinder.class.getCanonicalName());
					}
					catch (ClassNotFoundException e) {
						return false;
					}

					List<SingleVariableDeclaration> params = methodDecl.parameters();
					for (SingleVariableDeclaration param : params) {
						ITypeBinding paramTypeBinding = param.getType().resolveBinding();
						Class<?> paramClass;
						try {
							paramClass = classLoader.loadClass(paramTypeBinding.getQualifiedName());
						}
						catch (ClassNotFoundException e) {
							continue;
						}

						if (binderClass.isAssignableFrom(paramClass)) {
							return true;
						}
					}
				}
			}
		}

		return false;
	}

	@Override
	public boolean isQuickfixAvailable(TypeDeclaration typeDecl, IInvocationContext context) {
		return false;
	}

	@Override
	public List<IJavaCompletionProposal> getAssistsForMethod(MethodDeclaration methodDecl, SimpleName name,
			ICompilationUnit cu) {
		List<IJavaCompletionProposal> proposals = new ArrayList<IJavaCompletionProposal>();

		proposals.add(new AddInitBinderCompletionProposal(methodDecl, cu));

		return proposals;
	}
}
