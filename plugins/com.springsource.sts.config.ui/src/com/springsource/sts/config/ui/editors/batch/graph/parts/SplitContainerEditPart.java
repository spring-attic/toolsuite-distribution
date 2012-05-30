/******************************************************************************************
 * Copyright (c) 2009 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.ui.editors.batch.graph.parts;

import org.eclipse.gef.EditPolicy;

import com.springsource.sts.config.flow.parts.ParallelActivityPart;
import com.springsource.sts.config.ui.editors.batch.graph.StepNodeEditPolicy;
import com.springsource.sts.config.ui.editors.batch.graph.model.SplitContainerElement;

/**
 * @author Leo Dos Santos
 * @author Christian Dupuis
 */
public class SplitContainerEditPart extends ParallelActivityPart {

	public SplitContainerEditPart(SplitContainerElement split) {
		super(split);
	}

	@Override
	protected void createEditPolicies() {
		super.createEditPolicies();
		installEditPolicy(EditPolicy.GRAPHICAL_NODE_ROLE, new StepNodeEditPolicy());
	}

	@Override
	public SplitContainerElement getModelElement() {
		return (SplitContainerElement) getModel();
	}

}
