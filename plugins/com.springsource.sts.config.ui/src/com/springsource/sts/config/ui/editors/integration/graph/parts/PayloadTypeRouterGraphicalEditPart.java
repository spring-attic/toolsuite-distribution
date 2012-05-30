/******************************************************************************************
 * Copyright (c) 2009 - 2010 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.ui.editors.integration.graph.parts;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;

import com.springsource.sts.config.ui.editors.integration.graph.IntegrationImages;
import com.springsource.sts.config.ui.editors.integration.graph.model.PayloadTypeRouterModelElement;

/**
 * @author Leo Dos Santos
 */
public class PayloadTypeRouterGraphicalEditPart extends AbstractRouterGraphicalEditPart {

	public PayloadTypeRouterGraphicalEditPart(PayloadTypeRouterModelElement router) {
		super(router);
	}

	@Override
	protected IFigure createFigure() {
		Label l = (Label) super.createFigure();
		l.setIcon(IntegrationImages.getImageWithBadge(IntegrationImages.ROUTER, IntegrationImages.BADGE_SI));
		return l;
	}

	@Override
	public PayloadTypeRouterModelElement getModelElement() {
		return (PayloadTypeRouterModelElement) getModel();
	}

}
