/******************************************************************************************
 * Copyright (c) 2010 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.ui.editors.integration.file.graph.parts;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;

import com.springsource.sts.config.ui.editors.integration.file.graph.model.OutboundChannelAdapterModelElement;
import com.springsource.sts.config.ui.editors.integration.graph.IntegrationImages;
import com.springsource.sts.config.ui.editors.integration.graph.parts.BorderedIntegrationPart;

/**
 * @author Leo Dos Santos
 */
public class OutboundChannelAdapterGraphicalEditPart extends BorderedIntegrationPart {

	public OutboundChannelAdapterGraphicalEditPart(OutboundChannelAdapterModelElement adapter) {
		super(adapter);
	}

	@Override
	protected IFigure createFigure() {
		Label l = (Label) super.createFigure();
		l.setIcon(IntegrationImages.getImageWithBadge(IntegrationImages.OUTBOUND_ADAPTER,
				IntegrationImages.BADGE_SI_FILE));
		return l;
	}

	@Override
	public OutboundChannelAdapterModelElement getModelElement() {
		return (OutboundChannelAdapterModelElement) getModel();
	}

}
