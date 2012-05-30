/******************************************************************************************
 * Copyright (c) 2011 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.ui.editors.integration.redis.graph.parts;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;

import com.springsource.sts.config.ui.editors.integration.graph.IntegrationImages;
import com.springsource.sts.config.ui.editors.integration.graph.parts.BorderedIntegrationPart;
import com.springsource.sts.config.ui.editors.integration.redis.graph.model.OutboundChannelAdaperModelElement;

public class OutboundChannelAdapterGraphicalEditPart extends BorderedIntegrationPart {

	public OutboundChannelAdapterGraphicalEditPart(OutboundChannelAdaperModelElement adapter) {
		super(adapter);
	}

	@Override
	protected IFigure createFigure() {
		Label l = (Label) super.createFigure();
		l.setIcon(IntegrationImages.getImageWithBadge(IntegrationImages.OUTBOUND_ADAPTER,
				IntegrationImages.BADGE_SI_REDIS));
		return l;
	}

	@Override
	public OutboundChannelAdaperModelElement getModelElement() {
		return (OutboundChannelAdaperModelElement) getModel();
	}

}
