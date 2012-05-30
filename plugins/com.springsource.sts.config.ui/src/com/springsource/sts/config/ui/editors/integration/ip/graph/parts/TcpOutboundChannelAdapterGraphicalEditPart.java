/******************************************************************************************
 * Copyright (c) 2010 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.ui.editors.integration.ip.graph.parts;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;

import com.springsource.sts.config.ui.editors.integration.graph.IntegrationImages;
import com.springsource.sts.config.ui.editors.integration.graph.parts.BorderedIntegrationPart;
import com.springsource.sts.config.ui.editors.integration.ip.graph.model.TcpOutboundChannelAdapterModelElement;

/**
 * @author Leo Dos Santos
 */
public class TcpOutboundChannelAdapterGraphicalEditPart extends BorderedIntegrationPart {

	public TcpOutboundChannelAdapterGraphicalEditPart(TcpOutboundChannelAdapterModelElement adapter) {
		super(adapter);
	}

	@Override
	protected IFigure createFigure() {
		Label l = (Label) super.createFigure();
		l.setIcon(IntegrationImages
				.getImageWithBadge(IntegrationImages.OUTBOUND_ADAPTER, IntegrationImages.BADGE_SI_IP));
		return l;
	}

	@Override
	public TcpOutboundChannelAdapterModelElement getModelElement() {
		return (TcpOutboundChannelAdapterModelElement) getModel();
	}

}
