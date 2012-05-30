/******************************************************************************************
 * Copyright (c) 2010 - 2011 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.ui.editors.integration.xmpp.graph.parts;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;

import com.springsource.sts.config.ui.editors.integration.graph.IntegrationImages;
import com.springsource.sts.config.ui.editors.integration.graph.parts.BorderedIntegrationPart;
import com.springsource.sts.config.ui.editors.integration.xmpp.graph.model.InboundChannelAdapterModelElement;

/**
 * @author Leo Dos Santos
 */
public class InboundChannelAdapterGraphicalEditPart extends BorderedIntegrationPart {

	public InboundChannelAdapterGraphicalEditPart(InboundChannelAdapterModelElement adapter) {
		super(adapter);
	}

	@Override
	protected IFigure createFigure() {
		Label l = (Label) super.createFigure();
		l.setIcon(IntegrationImages.getImageWithBadge(IntegrationImages.INBOUND_ADAPTER,
				IntegrationImages.BADGE_SI_XMPP));
		return l;
	}

	@Override
	public InboundChannelAdapterModelElement getModelElement() {
		return (InboundChannelAdapterModelElement) getModel();
	}

}
