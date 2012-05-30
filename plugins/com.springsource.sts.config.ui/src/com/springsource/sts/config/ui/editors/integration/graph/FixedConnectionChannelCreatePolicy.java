/******************************************************************************************
 * Copyright (c) 2010 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.ui.editors.integration.graph;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.requests.CreateConnectionRequest;
import org.eclipse.gef.requests.ReconnectRequest;
import org.eclipse.ui.texteditor.ITextEditor;

import com.springsource.sts.config.flow.model.Activity;
import com.springsource.sts.config.flow.model.Transition;
import com.springsource.sts.config.flow.model.commands.FixedConnectionCreateCommand;
import com.springsource.sts.config.flow.policies.FixedConnectionNodeEditPolicy;
import com.springsource.sts.config.ui.editors.integration.graph.model.AlternateTransition;
import com.springsource.sts.config.ui.editors.integration.graph.parts.AlternateTransitionPart;

/**
 * @author Leo Dos Santos
 */
public class FixedConnectionChannelCreatePolicy extends FixedConnectionNodeEditPolicy {

	@Override
	protected Command getConnectionCreateCommand(CreateConnectionRequest request) {
		if (AlternateTransition.class == request.getNewObjectType()) {
			return null;
		}
		return super.getConnectionCreateCommand(request);
	}

	@Override
	protected FixedConnectionCreateCommand getConnectionCreateCommand(ITextEditor textEditor, int style) {
		return new FixedConnectionChannelCreateCommand(textEditor, style);
	}

	@Override
	protected Command getReconnectTargetCommand(ReconnectRequest request) {
		if (request.getConnectionEditPart() instanceof AlternateTransitionPart) {
			Activity target = getActivity();
			ReconnectTargetChannelCommand cmd = new ReconnectTargetChannelCommand(target.getDiagram().getTextEditor());
			cmd.setTransition((Transition) request.getConnectionEditPart().getModel());
			cmd.setTarget(target);
			return cmd;
		}
		return super.getReconnectTargetCommand(request);
	}

}
