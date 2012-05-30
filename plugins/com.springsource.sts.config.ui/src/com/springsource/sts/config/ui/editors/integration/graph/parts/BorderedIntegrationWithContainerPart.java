/******************************************************************************************
 * Copyright (c) 2010 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.ui.editors.integration.graph.parts;

import org.eclipse.gef.EditPolicy;

import com.springsource.sts.config.flow.model.Activity;
import com.springsource.sts.config.flow.policies.StructuredActivityLayoutEditPolicy;

/**
 * @author Leo Dos Santos
 */
public abstract class BorderedIntegrationWithContainerPart extends BorderedIntegrationPart {

	public BorderedIntegrationWithContainerPart(Activity activity) {
		super(activity);
	}

	@Override
	protected void createEditPolicies() {
		super.createEditPolicies();
		installEditPolicy(EditPolicy.LAYOUT_ROLE, new StructuredActivityLayoutEditPolicy());
	}

}
