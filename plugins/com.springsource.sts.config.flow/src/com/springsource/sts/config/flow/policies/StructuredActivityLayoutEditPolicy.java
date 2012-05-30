/******************************************************************************************
 * Copyright (c) 2009 - 2010 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.flow.policies;

import java.util.List;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.editpolicies.LayoutEditPolicy;
import org.eclipse.gef.editpolicies.NonResizableEditPolicy;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gef.requests.CreateRequest;

import com.springsource.sts.config.flow.model.Activity;
import com.springsource.sts.config.flow.model.StructuredActivity;
import com.springsource.sts.config.flow.model.commands.AddCommand;
import com.springsource.sts.config.flow.model.commands.CreateCommand;

/**
 * @author Leo Dos Santos
 * @author Christian Dupuis
 */
public class StructuredActivityLayoutEditPolicy extends LayoutEditPolicy {

	protected Command createAddCommand(EditPart child) {
		Activity activity = (Activity) child.getModel();
		EditPartViewer viewer = getHost().getViewer();
		AddCommand add = new AddCommand(activity.getDiagram().getTextEditor(), viewer);
		add.setParent((StructuredActivity) getHost().getModel());
		add.setChild(activity);
		return add;
	}

	/**
	 * @see org.eclipse.gef.editpolicies.OrderedLayoutEditPolicy#createChildEditPolicy(org.eclipse.gef.EditPart)
	 */
	@Override
	protected EditPolicy createChildEditPolicy(EditPart child) {
		return new NonResizableEditPolicy();
	}

	protected Command createMoveChildCommand(EditPart child, EditPart after) {
		return null;
	}

	@Override
	protected Command getAddCommand(Request req) {
		ChangeBoundsRequest request = (ChangeBoundsRequest) req;
		List editParts = request.getEditParts();
		CompoundCommand command = new CompoundCommand();
		for (int i = 0; i < editParts.size(); i++) {
			EditPart child = (EditPart) editParts.get(i);
			command.add(createAddCommand(child));
		}
		return command.unwrap();
	}

	/**
	 * @see LayoutEditPolicy#getCreateCommand(org.eclipse.gef.requests.CreateRequest)
	 */
	@Override
	protected Command getCreateCommand(CreateRequest request) {
		StructuredActivity parent = (StructuredActivity) getHost().getModel();
		EditPartViewer viewer = getHost().getViewer();
		CreateCommand command = new CreateCommand(parent.getDiagram().getTextEditor(), viewer);
		command.setParent(parent);
		command.setChild((Activity) request.getNewObject());
		return command;
	}

	/**
	 * @see org.eclipse.gef.editpolicies.LayoutEditPolicy#getMoveChildrenCommand(org.eclipse.gef.Request)
	 */
	@Override
	protected Command getMoveChildrenCommand(Request request) {
		return null;
	}

}
