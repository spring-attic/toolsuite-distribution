/******************************************************************************************
 * Copyright (c) 2011 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.ui.editors.integration.gemfire.graph.parts;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;

import com.springsource.sts.config.ui.editors.integration.gemfire.graph.model.CqInboundChannelAdapterModelElement;
import com.springsource.sts.config.ui.editors.integration.graph.IntegrationImages;
import com.springsource.sts.config.ui.editors.integration.graph.parts.BorderedIntegrationPart;

/**
 * @author Leo Dos Santos
 */
public class CqInboundChannelAdapterGraphicalEditPart extends BorderedIntegrationPart {

	public CqInboundChannelAdapterGraphicalEditPart(CqInboundChannelAdapterModelElement adapter) {
		super(adapter);
	}

	@Override
	protected IFigure createFigure() {
		Label l = (Label) super.createFigure();
		l.setIcon(IntegrationImages.getImageWithBadge(IntegrationImages.INBOUND_ADAPTER,
				IntegrationImages.BADGE_SI_GEMFIRE));
		return l;
	}

	@Override
	public CqInboundChannelAdapterModelElement getModelElement() {
		return (CqInboundChannelAdapterModelElement) getModel();
	}

}
