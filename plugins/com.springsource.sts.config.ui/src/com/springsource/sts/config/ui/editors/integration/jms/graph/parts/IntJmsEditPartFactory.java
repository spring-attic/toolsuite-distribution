/******************************************************************************************
 * Copyright (c) 2010 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.ui.editors.integration.jms.graph.parts;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;

import com.springsource.sts.config.ui.editors.integration.jms.graph.model.ChannelModelElement;
import com.springsource.sts.config.ui.editors.integration.jms.graph.model.HeaderEnricherModelElement;
import com.springsource.sts.config.ui.editors.integration.jms.graph.model.InboundChannelAdapterModelElement;
import com.springsource.sts.config.ui.editors.integration.jms.graph.model.InboundGatewayModelElement;
import com.springsource.sts.config.ui.editors.integration.jms.graph.model.MessageDrivenChannelAdapterModelElement;
import com.springsource.sts.config.ui.editors.integration.jms.graph.model.OutboundChannelAdapterModelElement;
import com.springsource.sts.config.ui.editors.integration.jms.graph.model.OutboundGatewayModelElement;
import com.springsource.sts.config.ui.editors.integration.jms.graph.model.PublishSubscribeChannelModelElement;

/**
 * @author Leo Dos Santos
 */
public class IntJmsEditPartFactory implements EditPartFactory {

	public EditPart createEditPart(EditPart context, Object model) {
		EditPart part = null;
		if (model instanceof ChannelModelElement) {
			part = new ChannelGraphicalEditPart((ChannelModelElement) model);
		}
		else if (model instanceof HeaderEnricherModelElement) {
			part = new HeaderEnricherGraphicalEditPart((HeaderEnricherModelElement) model);
		}
		else if (model instanceof InboundChannelAdapterModelElement) {
			part = new InboundChannelAdapterGraphicalEditPart((InboundChannelAdapterModelElement) model);
		}
		else if (model instanceof InboundGatewayModelElement) {
			part = new InboundGatewayGraphicalEditPart((InboundGatewayModelElement) model);
		}
		else if (model instanceof MessageDrivenChannelAdapterModelElement) {
			part = new MessageDrivenChannelAdapterGraphicalEditPart((MessageDrivenChannelAdapterModelElement) model);
		}
		else if (model instanceof OutboundChannelAdapterModelElement) {
			part = new OutboundChannelAdapterGraphicalEditPart((OutboundChannelAdapterModelElement) model);
		}
		else if (model instanceof OutboundGatewayModelElement) {
			part = new OutboundGatewayGraphicalEditPart((OutboundGatewayModelElement) model);
		}
		else if (model instanceof PublishSubscribeChannelModelElement) {
			part = new PublishSubscribeChannelGraphicalEditPart((PublishSubscribeChannelModelElement) model);
		}
		return part;
	}

}
