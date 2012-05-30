/******************************************************************************************
 * Copyright (c) 2009 - 2010 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.ui.editors.batch.graph;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.requests.CreateConnectionRequest;

import com.springsource.sts.config.flow.model.Activity;
import com.springsource.sts.config.flow.policies.ActivityNodeEditPolicy;

/**
 * @author Leo Dos Santos
 */
public class StopNodeEditPolicy extends ActivityNodeEditPolicy {

	@Override
	protected Command getConnectionCreateCommand(CreateConnectionRequest request) {
		int style = ((Integer) request.getNewObjectType()).intValue();
		Activity source = getActivity();
		StopConnectionCreateCommand cmd = new StopConnectionCreateCommand(source.getDiagram().getTextEditor(), style);
		cmd.setSource(source);
		request.setStartCommand(cmd);
		return cmd;
	}

}
