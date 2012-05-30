/******************************************************************************************
 * Copyright (c) 2011 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.ui.editors.integration.xml.graph.parts;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;

import com.springsource.sts.config.ui.editors.integration.graph.IntegrationImages;
import com.springsource.sts.config.ui.editors.integration.graph.parts.BorderedIntegrationPart;
import com.springsource.sts.config.ui.editors.integration.xml.graph.model.XpathFilterModelElement;

/**
 * @author Leo Dos Santos
 */
public class XpathFilterGraphicalEditPart extends BorderedIntegrationPart {

	public XpathFilterGraphicalEditPart(XpathFilterModelElement filter) {
		super(filter);
	}

	@Override
	protected IFigure createFigure() {
		Label l = (Label) super.createFigure();
		l.setIcon(IntegrationImages.getImageWithBadge(IntegrationImages.FILTER, IntegrationImages.BADGE_SI_XML));
		return l;
	}

	@Override
	public XpathFilterModelElement getModelElement() {
		return (XpathFilterModelElement) getModel();
	}

}
