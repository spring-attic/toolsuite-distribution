/******************************************************************************************
 * Copyright (c) 2009 - 2010 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.ui.editors.batch.graph.parts;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.gef.EditPolicy;
import org.eclipse.mylyn.commons.ui.CommonImages;

import com.springsource.sts.config.flow.parts.SimpleActivityWithContainerPart;
import com.springsource.sts.config.ui.editors.batch.graph.BatchImages;
import com.springsource.sts.config.ui.editors.batch.graph.StepNodeEditPolicy;
import com.springsource.sts.config.ui.editors.batch.graph.model.SplitModelElement;

/**
 * @author Leo Dos Santos
 * @author Christian Dupuis
 */
public class SplitGraphicalEditPart extends SimpleActivityWithContainerPart {

	public SplitGraphicalEditPart(SplitModelElement split) {
		super(split);
	}

	@Override
	protected void createEditPolicies() {
		super.createEditPolicies();
		installEditPolicy(EditPolicy.GRAPHICAL_NODE_ROLE, new StepNodeEditPolicy());
	}

	@Override
	protected IFigure createFigure() {
		Label l = (Label) super.createFigure();
		l.setIcon(CommonImages.getImage(BatchImages.SPLIT));
		return l;
	}

	@Override
	public SplitModelElement getModelElement() {
		return (SplitModelElement) getModel();
	}

}
