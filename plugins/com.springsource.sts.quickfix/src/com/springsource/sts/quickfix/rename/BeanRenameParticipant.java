/******************************************************************************************
 * Copyright (c) 2011 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.quickfix.rename;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.jdt.core.IAnnotation;
import org.eclipse.jdt.core.IMethod;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.ltk.core.refactoring.Change;
import org.eclipse.ltk.core.refactoring.RefactoringStatus;
import org.eclipse.ltk.core.refactoring.participants.CheckConditionsContext;

/**
 * Rename participant for when a method is renamed from various sources. If
 * method is an @bean method, all bean reference to this bean will be renamed
 * 
 * @author Terry Denney
 * @author Martin Lippert
 * @since 2.6.0
 */
public class BeanRenameParticipant {

	private IMethod method;

	private String newName;

	public void setNewName(String newName) {
		this.newName = newName;
	}

	public RefactoringStatus checkConditions(IProgressMonitor pm, CheckConditionsContext context)
			throws OperationCanceledException {
		return new RefactoringStatus();
	}

	public Change createChange(IProgressMonitor pm) throws CoreException, OperationCanceledException {
		if (!isBean()) {
			return null;
		}

		// TODO: need to deal with @bean with value specified

		// by default the method name is the bean name
		IResource resource = method.getResource();
		if (resource instanceof IFile) {
			RenameBeanIdRefsRefactoring refactoring = new RenameBeanIdRefsRefactoring(method, newName);
			return refactoring.createChange(pm);
		}
		return null;
	}

	public String getName() {
		return "";
	}

	protected boolean initialize(Object element) {
		if (element instanceof IMethod) {
			method = (IMethod) element;
			if (method.getCompilationUnit().getImport("org.springframework.context.annotation.Bean") != null) {
				method = (IMethod) element;
				return true;
			}
		}
		return false;
	}

	private boolean isBean() throws JavaModelException {
		IAnnotation[] annotations = method.getAnnotations();

		for (IAnnotation annotation : annotations) {
			if ("Bean".equals(annotation.getElementName())) {
				return true;
			}
		}
		return false;
	}
}
