/******************************************************************************************
 * Copyright (c) 2010 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.ui.editors.integration.ip.graph.parts;

import java.util.List;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;

import com.springsource.sts.config.flow.figures.BidirectionalBorderedActivityLabel;
import com.springsource.sts.config.flow.figures.BorderedActivityLabel;
import com.springsource.sts.config.ui.editors.integration.graph.IntegrationImages;
import com.springsource.sts.config.ui.editors.integration.graph.parts.BorderedIntegrationPart;
import com.springsource.sts.config.ui.editors.integration.ip.graph.model.TcpOutboundGatewayModelElement;

/**
 * @author Leo Dos Santos
 */
public class TcpOutboundGatewayGraphicalEditPart extends BorderedIntegrationPart {

	public TcpOutboundGatewayGraphicalEditPart(TcpOutboundGatewayModelElement gateway) {
		super(gateway);
	}

	@Override
	protected BorderedActivityLabel createBorderedLabel(int direction, List<String> incomings, List<String> outgoings) {
		return new BidirectionalBorderedActivityLabel(direction, incomings, outgoings, true);
	}

	@Override
	protected IFigure createFigure() {
		Label l = (Label) super.createFigure();
		l.setIcon(IntegrationImages
				.getImageWithBadge(IntegrationImages.OUTBOUND_GATEWAY, IntegrationImages.BADGE_SI_IP));
		return l;
	}

	@Override
	public TcpOutboundGatewayModelElement getModelElement() {
		return (TcpOutboundGatewayModelElement) getModel();
	}

}
