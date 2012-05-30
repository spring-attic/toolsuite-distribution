/******************************************************************************************
 * Copyright (c) 2010 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.ui.editors.integration.xml.graph.parts;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;

import com.springsource.sts.config.ui.editors.integration.graph.IntegrationImages;
import com.springsource.sts.config.ui.editors.integration.graph.parts.AbstractRouterGraphicalEditPart;
import com.springsource.sts.config.ui.editors.integration.xml.graph.model.XpathRouterModelElement;

/**
 * @author Leo Dos Santos
 */
public class XpathRouterGraphicalEditPart extends AbstractRouterGraphicalEditPart {

	public XpathRouterGraphicalEditPart(XpathRouterModelElement router) {
		super(router);
	}

	@Override
	protected IFigure createFigure() {
		Label l = (Label) super.createFigure();
		l.setIcon(IntegrationImages.getImageWithBadge(IntegrationImages.ROUTER, IntegrationImages.BADGE_SI_XML));
		return l;
	}

	@Override
	public XpathRouterModelElement getModelElement() {
		return (XpathRouterModelElement) getModel();
	}

}
