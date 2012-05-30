/******************************************************************************************
 * Copyright (c) 2009 - 2010 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.flow.policies;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.ComponentEditPolicy;
import org.eclipse.gef.requests.GroupRequest;

import com.springsource.sts.config.flow.model.Activity;
import com.springsource.sts.config.flow.model.StructuredActivity;
import com.springsource.sts.config.flow.model.commands.DeleteCommand;

/**
 * @author Leo Dos Santos
 * @author Christian Dupuis
 */
public class ActivityEditPolicy extends ComponentEditPolicy {

	/**
	 * @see ComponentEditPolicy#createDeleteCommand(org.eclipse.gef.requests.GroupRequest)
	 */
	@Override
	protected Command createDeleteCommand(GroupRequest deleteRequest) {
		StructuredActivity parent = (StructuredActivity) (getHost().getParent().getModel());
		DeleteCommand deleteCmd = new DeleteCommand(parent.getDiagram().getTextEditor());
		deleteCmd.setParent(parent);
		deleteCmd.setChild((Activity) (getHost().getModel()));
		return deleteCmd;
	}

}
