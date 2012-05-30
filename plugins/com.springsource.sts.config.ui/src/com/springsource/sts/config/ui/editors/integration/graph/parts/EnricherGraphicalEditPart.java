/******************************************************************************************
 * Copyright (c) 2011 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.ui.editors.integration.graph.parts;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;

import com.springsource.sts.config.ui.editors.integration.graph.IntegrationImages;
import com.springsource.sts.config.ui.editors.integration.graph.model.EnricherModelElement;

/**
 * @author Leo Dos Santos
 */
public class EnricherGraphicalEditPart extends BorderedIntegrationPart {

	public EnricherGraphicalEditPart(EnricherModelElement enricher) {
		super(enricher);
	}

	@Override
	protected IFigure createFigure() {
		Label l = (Label) super.createFigure();
		l.setIcon(IntegrationImages.getImageWithBadge(IntegrationImages.ENRICHER, IntegrationImages.BADGE_SI));
		return l;
	}

	@Override
	public EnricherModelElement getModelElement() {
		return (EnricherModelElement) getModel();
	}

}
