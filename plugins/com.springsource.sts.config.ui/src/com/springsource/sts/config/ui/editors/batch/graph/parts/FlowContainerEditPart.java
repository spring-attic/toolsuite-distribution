/******************************************************************************************
 * Copyright (c) 2010 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.ui.editors.batch.graph.parts;

import org.eclipse.gef.EditPolicy;

import com.springsource.sts.config.flow.parts.ParallelActivityPart;
import com.springsource.sts.config.ui.editors.batch.graph.StepNodeEditPolicy;
import com.springsource.sts.config.ui.editors.batch.graph.model.FlowContainerElement;

/**
 * @author Leo Dos Santos
 */
public class FlowContainerEditPart extends ParallelActivityPart {

	public FlowContainerEditPart(FlowContainerElement flow) {
		super(flow);
	}

	@Override
	protected void createEditPolicies() {
		super.createEditPolicies();
		installEditPolicy(EditPolicy.GRAPHICAL_NODE_ROLE, new StepNodeEditPolicy());
	}

	@Override
	public FlowContainerElement getModelElement() {
		return (FlowContainerElement) getModel();
	}

}
