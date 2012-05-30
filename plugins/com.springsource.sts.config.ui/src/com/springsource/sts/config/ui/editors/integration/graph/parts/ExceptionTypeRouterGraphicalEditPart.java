/******************************************************************************************
 * Copyright (c) 2011 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.ui.editors.integration.graph.parts;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;

import com.springsource.sts.config.ui.editors.integration.graph.IntegrationImages;
import com.springsource.sts.config.ui.editors.integration.graph.model.ExceptionTypeRouterModelElement;

/**
 * @author Leo Dos Santos
 */
public class ExceptionTypeRouterGraphicalEditPart extends AbstractRouterGraphicalEditPart {

	public ExceptionTypeRouterGraphicalEditPart(ExceptionTypeRouterModelElement router) {
		super(router);
	}

	@Override
	public ExceptionTypeRouterModelElement getModelElement() {
		return (ExceptionTypeRouterModelElement) getModel();
	}

	@Override
	protected IFigure createFigure() {
		Label l = (Label) super.createFigure();
		l.setIcon(IntegrationImages.getImageWithBadge(IntegrationImages.EXCEPTION_ROUTER, IntegrationImages.BADGE_SI));
		return l;
	}

}
