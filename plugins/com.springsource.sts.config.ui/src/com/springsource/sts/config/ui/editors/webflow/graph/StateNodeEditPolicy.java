/******************************************************************************************
 * Copyright (c) 2010 - 2011 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.ui.editors.webflow.graph;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.requests.CreateConnectionRequest;

import com.springsource.sts.config.flow.model.Activity;
import com.springsource.sts.config.flow.policies.ActivityNodeEditPolicy;
import com.springsource.sts.config.ui.editors.webflow.graph.model.DecisionStateModelElement;

/**
 * @author Leo Dos Santos
 */
public class StateNodeEditPolicy extends ActivityNodeEditPolicy {

	@Override
	protected Command getConnectionCreateCommand(CreateConnectionRequest request) {
		int style = ((Integer) request.getNewObjectType()).intValue();
		Activity source = getActivity();
		if (!(source instanceof DecisionStateModelElement)) {
			StateConnectionCreateCommand cmd = new StateConnectionCreateCommand(source.getDiagram().getTextEditor(),
					style);
			cmd.setSource(source);
			request.setStartCommand(cmd);
			return cmd;
		}
		return super.getConnectionCreateCommand(request);
	}

}
