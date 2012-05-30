/******************************************************************************************
 * Copyright (c) 2010 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.ui.editors.batch.graph.parts;

import org.eclipse.gef.EditPolicy;

import com.springsource.sts.config.flow.parts.SimpleActivityWithContainerPart;
import com.springsource.sts.config.ui.editors.batch.graph.StepNodeEditPolicy;
import com.springsource.sts.config.ui.editors.batch.graph.model.FlowAnchorElement;

/**
 * @author Leo Dos Santos
 */
public class FlowAnchorEditPart extends SimpleActivityWithContainerPart {

	public FlowAnchorEditPart(FlowAnchorElement flow) {
		super(flow);
	}

	@Override
	protected void createEditPolicies() {
		super.createEditPolicies();
		installEditPolicy(EditPolicy.GRAPHICAL_NODE_ROLE, new StepNodeEditPolicy());
	}

	@Override
	public FlowAnchorElement getModelElement() {
		return (FlowAnchorElement) getModel();
	}

}
