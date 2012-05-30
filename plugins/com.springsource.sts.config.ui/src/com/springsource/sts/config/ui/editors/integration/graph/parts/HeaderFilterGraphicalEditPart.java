/******************************************************************************************
 * Copyright (c) 2010 - 2011 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.ui.editors.integration.graph.parts;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;

import com.springsource.sts.config.flow.model.Activity;
import com.springsource.sts.config.ui.editors.integration.graph.IntegrationImages;
import com.springsource.sts.config.ui.editors.integration.graph.model.HeaderFilterModelElement;

/**
 * @author Leo Dos Santos
 */
public class HeaderFilterGraphicalEditPart extends BorderedIntegrationPart {

	public HeaderFilterGraphicalEditPart(HeaderFilterModelElement filter) {
		super(filter);
	}

	@Override
	protected IFigure createFigure() {
		Label l = (Label) super.createFigure();
		l.setIcon(IntegrationImages.getImageWithBadge(IntegrationImages.CONTENT_FILTER, IntegrationImages.BADGE_SI));
		return l;
	}

	@Override
	public Activity getModelElement() {
		return (HeaderFilterModelElement) getModel();
	}

}
