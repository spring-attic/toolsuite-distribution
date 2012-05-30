/******************************************************************************************
 * Copyright (c) 2009, 2011 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.quickfix.rename;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.ltk.core.refactoring.Change;
import org.eclipse.ltk.core.refactoring.RefactoringStatus;
import org.eclipse.ltk.core.refactoring.participants.CheckConditionsContext;
import org.eclipse.ltk.core.refactoring.participants.RenameParticipant;

/**
 * Rename participant for when a method is renamed. If method is an @bean
 * method, all bean reference to this bean will be renamed
 * 
 * @author Terry Denney
 * @author Martin Lippert
 */
public class MethodRenameParticipant extends RenameParticipant {

	private final BeanRenameParticipant renameParticipant;

	public MethodRenameParticipant() {
		this.renameParticipant = new BeanRenameParticipant();
	}

	@Override
	public RefactoringStatus checkConditions(IProgressMonitor pm, CheckConditionsContext context)
			throws OperationCanceledException {
		return renameParticipant.checkConditions(pm, context);
	}

	@Override
	public Change createChange(IProgressMonitor pm) throws CoreException, OperationCanceledException {
		renameParticipant.setNewName(getArguments().getNewName());
		return renameParticipant.createChange(pm);
	}

	@Override
	public String getName() {
		return renameParticipant.getName();
	}

	@Override
	protected boolean initialize(Object element) {
		return renameParticipant.initialize(element);
	}

}
