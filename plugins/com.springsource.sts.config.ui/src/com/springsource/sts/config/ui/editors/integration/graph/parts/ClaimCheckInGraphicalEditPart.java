/******************************************************************************************
 * Copyright (c) 2010 - 2011 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.ui.editors.integration.graph.parts;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;

import com.springsource.sts.config.ui.editors.integration.graph.IntegrationImages;
import com.springsource.sts.config.ui.editors.integration.graph.model.ClaimCheckInModelElement;

/**
 * @author Leo Dos Santos
 */
public class ClaimCheckInGraphicalEditPart extends BorderedIntegrationPart {

	public ClaimCheckInGraphicalEditPart(ClaimCheckInModelElement claim) {
		super(claim);
	}

	@Override
	protected IFigure createFigure() {
		Label l = (Label) super.createFigure();
		l.setIcon(IntegrationImages.getImageWithBadge(IntegrationImages.CLAIM_CHECK, IntegrationImages.BADGE_SI));
		return l;
	}

	@Override
	public ClaimCheckInModelElement getModelElement() {
		return (ClaimCheckInModelElement) getModel();
	}

}
