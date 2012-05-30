/******************************************************************************************
 * Copyright (c) 2011 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.ui.editors.webflow.graph;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.requests.DirectEditRequest;

import com.springsource.sts.config.flow.model.Activity;
import com.springsource.sts.config.flow.policies.ActivityDirectEditPolicy;

/**
 * @author Leo Dos Santos
 */
public class StateDirectEditPolicy extends ActivityDirectEditPolicy {

	@Override
	protected Command getDirectEditCommand(DirectEditRequest request) {
		Activity source = (Activity) getHost().getModel();
		RenameStateCommand cmd = new RenameStateCommand(source.getDiagram().getTextEditor());
		cmd.setSource(source);
		cmd.setOldName(((Activity) getHost().getModel()).getName());
		cmd.setName((String) request.getCellEditor().getValue());
		return cmd;
	}

}
