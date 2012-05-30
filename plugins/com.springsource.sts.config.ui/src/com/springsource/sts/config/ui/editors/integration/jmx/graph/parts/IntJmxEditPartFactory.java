/******************************************************************************************
 * Copyright (c) 2010 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.ui.editors.integration.jmx.graph.parts;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;

import com.springsource.sts.config.ui.editors.integration.jmx.graph.model.AttributePollingChannelAdapterModelElement;
import com.springsource.sts.config.ui.editors.integration.jmx.graph.model.NotificationListeningChannelAdapterModelElement;
import com.springsource.sts.config.ui.editors.integration.jmx.graph.model.NotificationPublishingChannelAdapterModelElement;
import com.springsource.sts.config.ui.editors.integration.jmx.graph.model.OperationInvokingChannelAdapterModelElement;
import com.springsource.sts.config.ui.editors.integration.jmx.graph.model.OperationInvokingOutboundGatewayModelElement;

/**
 * @author Leo Dos Santos
 */
public class IntJmxEditPartFactory implements EditPartFactory {

	public EditPart createEditPart(EditPart context, Object model) {
		EditPart part = null;
		if (model instanceof AttributePollingChannelAdapterModelElement) {
			part = new AttributePollingChannelAdapterGraphicalEditPart(
					(AttributePollingChannelAdapterModelElement) model);
		}
		else if (model instanceof NotificationListeningChannelAdapterModelElement) {
			part = new NotificationListeningChannelAdapterGraphicalEditPart(
					(NotificationListeningChannelAdapterModelElement) model);
		}
		else if (model instanceof NotificationPublishingChannelAdapterModelElement) {
			part = new NotificationPublishingChannelAdapterGraphicalEditPart(
					(NotificationPublishingChannelAdapterModelElement) model);
		}
		else if (model instanceof OperationInvokingChannelAdapterModelElement) {
			part = new OperationInvokingChannelAdapterGraphicalEditPart(
					(OperationInvokingChannelAdapterModelElement) model);
		}
		else if (model instanceof OperationInvokingOutboundGatewayModelElement) {
			part = new OperationInvokingOutboundGatewayGraphicalEditPart(
					(OperationInvokingOutboundGatewayModelElement) model);
		}
		return part;
	}

}
