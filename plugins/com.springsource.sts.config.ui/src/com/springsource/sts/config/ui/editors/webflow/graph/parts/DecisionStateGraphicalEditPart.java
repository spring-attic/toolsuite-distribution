/******************************************************************************************
 * Copyright (c) 2010 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.ui.editors.webflow.graph.parts;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.mylyn.commons.ui.CommonImages;

import com.springsource.sts.config.ui.editors.webflow.graph.WebFlowImages;
import com.springsource.sts.config.ui.editors.webflow.graph.model.DecisionStateModelElement;

/**
 * @author Leo Dos Santos
 */
public class DecisionStateGraphicalEditPart extends AbstractStateGraphicalEditPart {

	public DecisionStateGraphicalEditPart(DecisionStateModelElement state) {
		super(state);
	}

	@Override
	protected IFigure createFigure() {
		Label l = (Label) super.createFigure();
		l.setIcon(CommonImages.getImage(WebFlowImages.DECISION));
		return l;
	}

	@Override
	public DecisionStateModelElement getModelElement() {
		return (DecisionStateModelElement) getModel();
	}

}
