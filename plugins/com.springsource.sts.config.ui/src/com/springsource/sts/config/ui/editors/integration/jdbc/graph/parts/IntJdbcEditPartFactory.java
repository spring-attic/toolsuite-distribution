/******************************************************************************************
 * Copyright (c) 2010 - 2011 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.ui.editors.integration.jdbc.graph.parts;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;

import com.springsource.sts.config.ui.editors.integration.jdbc.graph.model.InboundChannelAdapterModelElement;
import com.springsource.sts.config.ui.editors.integration.jdbc.graph.model.OutboundChannelAdapterModelElement;
import com.springsource.sts.config.ui.editors.integration.jdbc.graph.model.OutboundGatewayModelElement;
import com.springsource.sts.config.ui.editors.integration.jdbc.graph.model.StoredProcInboundChannelAdapterModelElement;
import com.springsource.sts.config.ui.editors.integration.jdbc.graph.model.StoredProcOutboundChannelAdapterModelElement;
import com.springsource.sts.config.ui.editors.integration.jdbc.graph.model.StoredProcOutboundGatewayModelElement;

/**
 * @author Leo Dos Santos
 */
public class IntJdbcEditPartFactory implements EditPartFactory {

	public EditPart createEditPart(EditPart context, Object model) {
		EditPart part = null;
		if (model instanceof InboundChannelAdapterModelElement) {
			part = new InboundChannelAdapterGraphicalEditPart((InboundChannelAdapterModelElement) model);
		}
		else if (model instanceof OutboundChannelAdapterModelElement) {
			part = new OutboundChannelAdapterGraphicalEditPart((OutboundChannelAdapterModelElement) model);
		}
		else if (model instanceof OutboundGatewayModelElement) {
			part = new OutboundGatewayGraphicalEditPart((OutboundGatewayModelElement) model);
		}
		else if (model instanceof StoredProcInboundChannelAdapterModelElement) {
			part = new StoredProcInboundChannelAdapterGraphicalEditPart(
					(StoredProcInboundChannelAdapterModelElement) model);
		}
		else if (model instanceof StoredProcOutboundChannelAdapterModelElement) {
			part = new StoredProcOutboundChannelAdapterGraphicalEditPart(
					(StoredProcOutboundChannelAdapterModelElement) model);
		}
		else if (model instanceof StoredProcOutboundGatewayModelElement) {
			part = new StoredProcOutboundGatewayGraphicalEditPart((StoredProcOutboundGatewayModelElement) model);
		}
		return part;
	}

}
