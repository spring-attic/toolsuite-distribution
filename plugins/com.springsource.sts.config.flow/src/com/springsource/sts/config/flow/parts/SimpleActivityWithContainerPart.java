/******************************************************************************************
 * Copyright (c) 2009 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.flow.parts;

import org.eclipse.gef.EditPolicy;

import com.springsource.sts.config.flow.model.Activity;
import com.springsource.sts.config.flow.policies.StructuredActivityLayoutEditPolicy;

/**
 * @author Leo Dos Santos
 * @author Christian Dupuis
 */
public abstract class SimpleActivityWithContainerPart extends SimpleActivityPart {

	public SimpleActivityWithContainerPart(Activity activity) {
		super(activity);
	}

	@Override
	protected void createEditPolicies() {
		super.createEditPolicies();
		installEditPolicy(EditPolicy.LAYOUT_ROLE, new StructuredActivityLayoutEditPolicy());
	}

}
