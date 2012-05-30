/******************************************************************************************
 * Copyright (c) 2010 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.ui.editors.integration.graph.parts;

import org.eclipse.gef.EditPolicy;

import com.springsource.sts.config.flow.model.Transition;
import com.springsource.sts.config.flow.parts.TransitionPart;

/**
 * @author Leo Dos Santos
 */
public class ImplicitTransitionPart extends TransitionPart {

	public ImplicitTransitionPart(Transition model) {
		super(model);
	}

	@Override
	protected void createEditPolicies() {
		super.createEditPolicies();
		installEditPolicy(EditPolicy.CONNECTION_ROLE, null);
		installEditPolicy(EditPolicy.CONNECTION_ENDPOINTS_ROLE, null);
	}

}
