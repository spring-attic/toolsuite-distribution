/******************************************************************************************
 * Copyright (c) 2010 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.ui.editors.integration.graph;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.requests.CreateConnectionRequest;

import com.springsource.sts.config.flow.model.Activity;
import com.springsource.sts.config.ui.editors.integration.graph.model.AlternateTransition;

/**
 * @author Leo Dos Santos
 */
public class RouterNodeEditPolicy extends FixedConnectionChannelCreatePolicy {

	@Override
	protected Command getConnectionCreateCommand(CreateConnectionRequest request) {
		if (AlternateTransition.class == request.getNewObjectType()) {
			Activity source = getActivity();
			MappingConnectionCreateCommand cmd = new MappingConnectionCreateCommand(source.getDiagram().getTextEditor());
			cmd.setSource(source);
			request.setStartCommand(cmd);
			return cmd;
		}
		return super.getConnectionCreateCommand(request);
	}

}
