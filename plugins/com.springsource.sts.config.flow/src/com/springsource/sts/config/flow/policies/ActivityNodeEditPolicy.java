/******************************************************************************************
 * Copyright (c) 2009 - 2010 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.flow.policies;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.GraphicalNodeEditPolicy;
import org.eclipse.gef.requests.CreateConnectionRequest;
import org.eclipse.gef.requests.ReconnectRequest;

import com.springsource.sts.config.flow.model.Activity;
import com.springsource.sts.config.flow.model.commands.AbstractConnectionCreateCommand;
import com.springsource.sts.config.flow.parts.ActivityPart;

/**
 * Created on Jul 17, 2003
 * @author Leo Dos Santos
 * @author Christian Dupuis
 */
public class ActivityNodeEditPolicy extends GraphicalNodeEditPolicy {

	/**
	 * Returns the model associated with the EditPart on which this EditPolicy
	 * is installed
	 * @return the model
	 */
	protected Activity getActivity() {
		return (Activity) getHost().getModel();
	}

	/**
	 * Returns the ActivityPart on which this EditPolicy is installed
	 * @return the
	 */
	protected ActivityPart getActivityPart() {
		return (ActivityPart) getHost();
	}

	/**
	 * @see GraphicalNodeEditPolicy#getConnectionCompleteCommand(CreateConnectionRequest)
	 */
	@Override
	protected Command getConnectionCompleteCommand(CreateConnectionRequest request) {
		AbstractConnectionCreateCommand cmd = (AbstractConnectionCreateCommand) request.getStartCommand();
		cmd.setTarget(getActivity());
		return cmd;
	}

	/**
	 * @see GraphicalNodeEditPolicy#getConnectionCreateCommand(CreateConnectionRequest)
	 */
	@Override
	protected Command getConnectionCreateCommand(CreateConnectionRequest request) {
		return null;
	}

	/**
	 * @see GraphicalNodeEditPolicy#getReconnectSourceCommand(ReconnectRequest)
	 */
	@Override
	protected Command getReconnectSourceCommand(ReconnectRequest request) {
		// Activity source = getActivity();
		// ReconnectSourceCommand cmd = new
		// ReconnectSourceCommand(source.getDiagram().getTextEditor());
		// cmd.setTransition((Transition)
		// request.getConnectionEditPart().getModel());
		// cmd.setSource(source);
		// return cmd;
		return null;
	}

	/**
	 * @see GraphicalNodeEditPolicy#getReconnectTargetCommand(ReconnectRequest)
	 */
	@Override
	protected Command getReconnectTargetCommand(ReconnectRequest request) {
		// Activity target = getActivity();
		// ReconnectTargetCommand cmd = new
		// ReconnectTargetCommand(target.getDiagram().getTextEditor());
		// cmd.setTransition((Transition)
		// request.getConnectionEditPart().getModel());
		// cmd.setTarget(target);
		// return cmd;
		return null;
	}

}
