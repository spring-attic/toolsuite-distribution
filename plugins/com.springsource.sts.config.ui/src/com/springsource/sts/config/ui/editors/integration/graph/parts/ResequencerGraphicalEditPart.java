/******************************************************************************************
 * Copyright (c) 2009 - 2010 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.ui.editors.integration.graph.parts;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;

import com.springsource.sts.config.ui.editors.integration.graph.IntegrationImages;
import com.springsource.sts.config.ui.editors.integration.graph.model.ResequencerModelElement;

/**
 * @author Leo Dos Santos
 */
public class ResequencerGraphicalEditPart extends BorderedIntegrationPart {

	public ResequencerGraphicalEditPart(ResequencerModelElement resequencer) {
		super(resequencer);
	}

	@Override
	protected IFigure createFigure() {
		Label l = (Label) super.createFigure();
		l.setIcon(IntegrationImages.getImageWithBadge(IntegrationImages.RESEQUENCER, IntegrationImages.BADGE_SI));
		return l;
	}

	@Override
	public ResequencerModelElement getModelElement() {
		return (ResequencerModelElement) getModel();
	}

}
