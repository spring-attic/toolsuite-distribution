/******************************************************************************************
 * Copyright (c) 2010 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.ui.editors.integration.ip.graph.parts;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;

import com.springsource.sts.config.ui.editors.integration.ip.graph.model.UdpInboundChannelAdapterModelElement;
import com.springsource.sts.config.ui.editors.integration.ip.graph.model.TcpInboundGatewayModelElement;
import com.springsource.sts.config.ui.editors.integration.ip.graph.model.UdpOutboundChannelAdapterModelElement;
import com.springsource.sts.config.ui.editors.integration.ip.graph.model.TcpOutboundGatewayModelElement;
import com.springsource.sts.config.ui.editors.integration.ip.graph.model.TcpInboundChannelAdapterModelElement;
import com.springsource.sts.config.ui.editors.integration.ip.graph.model.TcpOutboundChannelAdapterModelElement;

/**
 * @author Leo Dos Santos
 */
public class IntIpEditPartFactory implements EditPartFactory {

	public EditPart createEditPart(EditPart context, Object model) {
		EditPart part = null;
		if (model instanceof UdpInboundChannelAdapterModelElement) {
			part = new UdpInboundChannelAdapterGraphicalEditPart((UdpInboundChannelAdapterModelElement) model);
		}
		else if (model instanceof TcpInboundGatewayModelElement) {
			part = new TcpInboundGatewayGraphicalEditPart((TcpInboundGatewayModelElement) model);
		}
		else if (model instanceof UdpOutboundChannelAdapterModelElement) {
			part = new UdpOutboundChannelAdapterGraphicalEditPart((UdpOutboundChannelAdapterModelElement) model);
		}
		else if (model instanceof TcpOutboundGatewayModelElement) {
			part = new TcpOutboundGatewayGraphicalEditPart((TcpOutboundGatewayModelElement) model);
		}
		else if (model instanceof TcpInboundChannelAdapterModelElement) {
			part = new TcpInboundChannelAdapterGraphicalEditPart((TcpInboundChannelAdapterModelElement) model);
		}
		else if (model instanceof TcpOutboundChannelAdapterModelElement) {
			part = new TcpOutboundChannelAdapterGraphicalEditPart((TcpOutboundChannelAdapterModelElement) model);
		}
		return part;
	}

}
