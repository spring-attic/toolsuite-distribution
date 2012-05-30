/******************************************************************************************
 * Copyright (c) 2009 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.ui.editors.batch.graph.parts;

import org.eclipse.gef.EditPolicy;

import com.springsource.sts.config.flow.parts.SimpleActivityWithContainerPart;
import com.springsource.sts.config.ui.editors.batch.graph.StepNodeEditPolicy;
import com.springsource.sts.config.ui.editors.batch.graph.model.StepModelElement;

/**
 * @author Leo Dos Santos
 * @author Christian Dupuis
 */
public class StepGraphicalEditPart extends SimpleActivityWithContainerPart {

	public StepGraphicalEditPart(StepModelElement step) {
		super(step);
	}

	@Override
	protected void createEditPolicies() {
		super.createEditPolicies();
		installEditPolicy(EditPolicy.GRAPHICAL_NODE_ROLE, new StepNodeEditPolicy());
	}

	@Override
	public StepModelElement getModelElement() {
		return (StepModelElement) getModel();
	}

}
