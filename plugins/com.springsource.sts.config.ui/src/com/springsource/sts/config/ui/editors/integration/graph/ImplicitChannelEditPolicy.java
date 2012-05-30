/******************************************************************************************
 * Copyright (c) 2010 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.ui.editors.integration.graph;

import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.ComponentEditPolicy;

import com.springsource.sts.config.flow.model.StructuredActivity;
import com.springsource.sts.config.ui.editors.integration.graph.model.ImplicitChannelModelElement;

/**
 * @author Leo Dos Santos
 */
public class ImplicitChannelEditPolicy extends ComponentEditPolicy {

	@Override
	public Command getCommand(Request request) {
		if (CreateExplicitChannelAction.EXPLICIT_CHANNEL_REQ.equals(request.getType())) {
			return getExplicitChannelCommand();
		}
		return super.getCommand(request);
	}

	protected Command getExplicitChannelCommand() {
		StructuredActivity parent = (StructuredActivity) getHost().getParent().getModel();
		ImplicitChannelModelElement channel = (ImplicitChannelModelElement) getHost().getModel();
		CreateExplicitChannelCommand command = new CreateExplicitChannelCommand(parent.getDiagram().getTextEditor());
		command.setParent(parent);
		command.setChannel(channel);
		return command;
	}

}
