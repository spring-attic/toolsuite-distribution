/******************************************************************************************
 * Copyright (c) 2010 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.ui.editors.webflow.graph.parts;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.mylyn.commons.ui.CommonImages;

import com.springsource.sts.config.ui.editors.webflow.graph.WebFlowImages;
import com.springsource.sts.config.ui.editors.webflow.graph.model.SubflowStateModelElement;

/**
 * @author Leo Dos Santos
 */
public class SubflowStateGraphicalEditPart extends AbstractStateGraphicalEditPart {

	public SubflowStateGraphicalEditPart(SubflowStateModelElement state) {
		super(state);
	}

	@Override
	protected IFigure createFigure() {
		Label l = (Label) super.createFigure();
		l.setIcon(CommonImages.getImage(WebFlowImages.SUBFLOW));
		return l;
	}

	@Override
	public SubflowStateModelElement getModelElement() {
		return (SubflowStateModelElement) getModel();
	}

}
