/******************************************************************************************
 * Copyright (c) 2009 - 2010 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.flow.policies;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.ConnectionEditPolicy;
import org.eclipse.gef.requests.GroupRequest;

import com.springsource.sts.config.flow.model.Activity;
import com.springsource.sts.config.flow.model.Transition;
import com.springsource.sts.config.flow.model.commands.DeleteConnectionCommand;

/**
 * EditPolicy for Transitions. Supports deletion and "splitting", i.e. adding an
 * Activity that splits the transition into an incoming and outgoing connection
 * to the new Activity.
 * @author Leo Dos Santos
 * @author Christian Dupuis
 */
public class TransitionEditPolicy extends ConnectionEditPolicy {

	/**
	 * @see org.eclipse.gef.editpolicies.AbstractEditPolicy#eraseTargetFeedback(org.eclipse.gef.Request)
	 */
	// @Override
	// public void eraseTargetFeedback(Request request) {
	// if (REQ_CREATE.equals(request.getType())) {
	// getConnectionFigure().setLineWidth(1);
	// }
	// }

	/**
	 * @see org.eclipse.gef.editpolicies.ConnectionEditPolicy#getCommand(org.eclipse.gef.Request)
	 */
	// @Override
	// public Command getCommand(Request request) {
	// if (REQ_CREATE.equals(request.getType())) {
	// return getSplitTransitionCommand(request);
	// }
	// return super.getCommand(request);
	// }

	// private PolylineConnection getConnectionFigure() {
	// return ((PolylineConnection) ((TransitionPart) getHost()).getFigure());
	// }

	/**
	 * @see ConnectionEditPolicy#getDeleteCommand(org.eclipse.gef.requests.GroupRequest)
	 */
	@Override
	protected Command getDeleteCommand(GroupRequest request) {
		Transition transition = (Transition) getHost().getModel();
		Activity source = transition.source;
		DeleteConnectionCommand cmd = new DeleteConnectionCommand(source.getDiagram().getTextEditor());
		cmd.setTransition(transition);
		cmd.setSource(source);
		cmd.setTarget(transition.target);
		return cmd;
	}

	// protected Command getSplitTransitionCommand(Request request) {
	// SplitTransitionCommand cmd = new SplitTransitionCommand();
	// cmd.setTransition(((Transition) getHost().getModel()));
	// cmd.setParent(((StructuredActivity) ((TransitionPart)
	// getHost()).getSource().getParent().getModel()));
	// cmd.setNewActivity(((Activity) ((CreateRequest)
	// request).getNewObject()));
	// return cmd;
	// }

	/**
	 * @see org.eclipse.gef.editpolicies.AbstractEditPolicy#getTargetEditPart(org.eclipse.gef.Request)
	 */
	// @Override
	// public EditPart getTargetEditPart(Request request) {
	// if (REQ_CREATE.equals(request.getType())) {
	// return getHost();
	// }
	// return null;
	// }

	/**
	 * @see org.eclipse.gef.editpolicies.AbstractEditPolicy#showTargetFeedback(org.eclipse.gef.Request)
	 */
	// @Override
	// public void showTargetFeedback(Request request) {
	// if (REQ_CREATE.equals(request.getType())) {
	// getConnectionFigure().setLineWidth(2);
	// }
	// }

}
