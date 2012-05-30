/******************************************************************************************
 * Copyright (c) 2010 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.ui.editors.webflow.graph.parts;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.mylyn.commons.ui.CommonImages;

import com.springsource.sts.config.ui.editors.webflow.graph.WebFlowImages;
import com.springsource.sts.config.ui.editors.webflow.graph.model.ActionStateModelElement;

/**
 * @author Leo Dos Santos
 */
public class ActionStateGraphicalEditPart extends AbstractStateGraphicalEditPart {

	public ActionStateGraphicalEditPart(ActionStateModelElement state) {
		super(state);
	}

	@Override
	protected IFigure createFigure() {
		Label l = (Label) super.createFigure();
		l.setIcon(CommonImages.getImage(WebFlowImages.ACTION));
		l.setTextPlacement(PositionConstants.SOUTH);
		return l;
	}

	@Override
	public ActionStateModelElement getModelElement() {
		return (ActionStateModelElement) getModel();
	}

}
