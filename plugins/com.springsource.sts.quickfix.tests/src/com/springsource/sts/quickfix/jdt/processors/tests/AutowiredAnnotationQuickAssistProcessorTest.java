/******************************************************************************************
 * Copyright (c) 2009 - 2011 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.quickfix.jdt.processors.tests;

import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.jdt.core.IField;
import org.eclipse.jdt.core.IMethod;
import org.eclipse.jdt.core.ISourceRange;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.ui.text.java.IInvocationContext;
import org.eclipse.jdt.ui.text.java.IJavaCompletionProposal;
import org.eclipse.jface.text.source.ISourceViewer;

import com.springsource.sts.quickfix.jdt.processors.AutowiredAnnotationQuickAssistProcessor;
import com.springsource.sts.quickfix.jdt.proposals.AddAutowireCompletionProposal;

/**
 * @author Terry Denney
 */
public class AutowiredAnnotationQuickAssistProcessorTest extends AnnotationProcessorTest {

	private AutowiredAnnotationQuickAssistProcessor processor;

	private IType constructorTestType;

	private ISourceViewer constructorTestViewer;

	@Override
	protected void setUp() throws Exception {
		setUp("com.test.AutowireTest");

		constructorTestType = javaProject.findType("com.test.ConstructorAutowireTest");
		IFile constructorTestFile = (IFile) constructorTestType.getResource();
		constructorTestViewer = getViewer(constructorTestFile);

		processor = new AutowiredAnnotationQuickAssistProcessor();
	}

	private IMethod getConstructor(IType type, int numParam) throws JavaModelException {
		IMethod[] methods = type.getMethods();
		for (IMethod method : methods) {
			if (method.isConstructor() && method.getParameterTypes().length == numParam) {
				return method;
			}
		}
		return null;
	}

	public void testConstructorWith1Param() throws JavaModelException {
		IMethod constructor = getConstructor(type, 1);
		ISourceRange sourceRange = constructor.getSourceRange();
		MethodDeclaration methodDecl = (MethodDeclaration) getASTNode(sourceRange, type, viewer);
		IInvocationContext context = getContext(sourceRange, constructor, methodDecl);

		List<IJavaCompletionProposal> proposals = processor.getAssists(methodDecl, context);
		assertTrue("2 proposals are expected", proposals.size() == 2);
		assertTrue("AddAutowireCompletionProposal expected", proposals.get(0) instanceof AddAutowireCompletionProposal);
		assertTrue("AddAutowireCompletionProposal expected", proposals.get(1) instanceof AddAutowireCompletionProposal);
	}

	public void testConstructorWithAutowired() throws JavaModelException {
		IMethod constructor = getConstructor(constructorTestType, 1);
		ISourceRange sourceRange = constructor.getSourceRange();
		MethodDeclaration methodDecl = (MethodDeclaration) getASTNode(sourceRange, constructorTestType,
				constructorTestViewer);
		IInvocationContext context = getContext(sourceRange, constructor, methodDecl);

		List<IJavaCompletionProposal> proposals = processor.getAssists(methodDecl, context);
		assertTrue("No proposals expected", proposals.isEmpty());
	}

	public void testConstructorWithOtherConstructorWithAutowired() throws JavaModelException {
		IMethod constructor = getConstructor(constructorTestType, 2);
		ISourceRange sourceRange = constructor.getSourceRange();
		MethodDeclaration methodDecl = (MethodDeclaration) getASTNode(sourceRange, constructorTestType,
				constructorTestViewer);
		IInvocationContext context = getContext(sourceRange, constructor, methodDecl);

		List<IJavaCompletionProposal> proposals = processor.getAssists(methodDecl, context);
		assertTrue("No proposals expected", proposals.isEmpty());
	}

	public void testConstructorWithNoParam() throws JavaModelException {
		IMethod constructor = getConstructor(type, 0);
		ISourceRange sourceRange = constructor.getSourceRange();
		MethodDeclaration methodDecl = (MethodDeclaration) getASTNode(sourceRange, type, viewer);
		IInvocationContext context = getContext(sourceRange, constructor, methodDecl);

		List<IJavaCompletionProposal> proposals = processor.getAssists(methodDecl, context);
		assertTrue("No proposals expected", proposals.isEmpty());
	}

	public void testField() throws JavaModelException {
		IField field = type.getField("testBean1");
		ISourceRange sourceRange = field.getSourceRange();
		FieldDeclaration fieldDecl = (FieldDeclaration) getASTNode(sourceRange, type, viewer);
		IInvocationContext context = getContext(sourceRange, field, fieldDecl);

		List<IJavaCompletionProposal> proposals = processor.getAssists(fieldDecl, context);
		assertTrue("2 proposals are expected", proposals.size() == 2);
		assertTrue("AddAutowireCompletionProposal expected", proposals.get(0) instanceof AddAutowireCompletionProposal);
		assertTrue("AddAutowireCompletionProposal expected", proposals.get(1) instanceof AddAutowireCompletionProposal);
	}

	public void testFieldWithAutowired() throws JavaModelException {
		IField field = type.getField("testBean2");
		ISourceRange sourceRange = field.getSourceRange();
		FieldDeclaration fieldDecl = (FieldDeclaration) getASTNode(sourceRange, type, viewer);
		IInvocationContext context = getContext(sourceRange, field, fieldDecl);

		List<IJavaCompletionProposal> proposals = processor.getAssists(fieldDecl, context);
		assertTrue("No proposals expected", proposals.isEmpty());
	}

	public void testMethodWith1Param() throws JavaModelException {
		IMethod method = type.getMethod("testMethod1", new String[] { "QTestClass;" });
		ISourceRange sourceRange = method.getSourceRange();
		MethodDeclaration methodDecl = (MethodDeclaration) getASTNode(sourceRange, type, viewer);
		IInvocationContext context = getContext(sourceRange, method, methodDecl);

		List<IJavaCompletionProposal> proposals = processor.getAssists(methodDecl, context);
		assertTrue("2 proposals are expected", proposals.size() == 2);
		assertTrue("AddAutowireCompletionProposal expected", proposals.get(0) instanceof AddAutowireCompletionProposal);
		assertTrue("AddAutowireCompletionProposal expected", proposals.get(1) instanceof AddAutowireCompletionProposal);
	}

	public void testMethodWith1ParamWithAutowired() throws JavaModelException {
		IMethod method = type.getMethod("testMethod2", new String[] { "QTestClass;" });
		ISourceRange sourceRange = method.getSourceRange();
		MethodDeclaration methodDecl = (MethodDeclaration) getASTNode(sourceRange, type, viewer);
		IInvocationContext context = getContext(sourceRange, method, methodDecl);

		List<IJavaCompletionProposal> proposals = processor.getAssists(methodDecl, context);
		assertTrue("No proposals expected", proposals.isEmpty());
	}

	public void testMethodWithNoParam() throws JavaModelException {
		IMethod method = type.getMethod("testMethod3", new String[] {});
		ISourceRange sourceRange = method.getSourceRange();
		MethodDeclaration methodDecl = (MethodDeclaration) getASTNode(sourceRange, type, viewer);
		IInvocationContext context = getContext(sourceRange, method, methodDecl);

		List<IJavaCompletionProposal> proposals = processor.getAssists(methodDecl, context);
		assertTrue("No proposals expected", proposals.isEmpty());
	}

}
