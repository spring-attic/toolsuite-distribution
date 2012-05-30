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
 * @author Christian Dupuis
 */
public class StepNodeEditPolicy extends ActivityNodeEditPolicy {

	@Override
	protected Command getConnectionCreateCommand(CreateConnectionRequest request) {
		int style = ((Integer) request.getNewObjectType()).intValue();
		Activity source = getActivity();
		StepConnectionCreateCommand cmd = new StepConnectionCreateCommand(source.getDiagram().getTextEditor(), style);
		cmd.setSource(source);
		request.setStartCommand(cmd);
		return cmd;
	}

}
