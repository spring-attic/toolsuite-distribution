/******************************************************************************************
 * Copyright (c) 2010 - 2011 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.ui.editors.integration.groovy.graph.parts;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;

import com.springsource.sts.config.ui.editors.integration.graph.IntegrationImages;
import com.springsource.sts.config.ui.editors.integration.graph.parts.BorderedIntegrationPart;
import com.springsource.sts.config.ui.editors.integration.groovy.graph.model.ControlBusModelElement;

/**
 * @author Leo Dos Santos
 */
public class ControlBusGraphicalEditPart extends BorderedIntegrationPart {

	public ControlBusGraphicalEditPart(ControlBusModelElement bus) {
		super(bus);
	}

	@Override
	protected IFigure createFigure() {
		Label l = (Label) super.createFigure();
		l.setIcon(IntegrationImages.getImageWithBadge(IntegrationImages.CONTROL_BUS, IntegrationImages.BADGE_SI_GROOVY));
		return l;
	}

	@Override
	public ControlBusModelElement getModelElement() {
		return (ControlBusModelElement) getModel();
	}

}
