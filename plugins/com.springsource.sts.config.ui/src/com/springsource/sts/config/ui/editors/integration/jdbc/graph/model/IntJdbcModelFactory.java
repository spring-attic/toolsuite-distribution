/******************************************************************************************
 * Copyright (c) 2010 - 2011 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.ui.editors.integration.jdbc.graph.model;

import java.util.List;

import org.eclipse.wst.xml.core.internal.provisional.document.IDOMElement;

import com.springsource.sts.config.core.schemas.IntJdbcSchemaConstants;
import com.springsource.sts.config.flow.model.Activity;
import com.springsource.sts.config.ui.editors.integration.graph.model.AbstractIntegrationModelFactory;

/**
 * @author Leo Dos Santos
 */
@SuppressWarnings("restriction")
public class IntJdbcModelFactory extends AbstractIntegrationModelFactory {

	public void getChildrenFromXml(List<Activity> list, IDOMElement input, Activity parent) {
		if (input.getLocalName().equals(IntJdbcSchemaConstants.ELEM_INBOUND_CHANNEL_ADAPTER)) {
			InboundChannelAdapterModelElement adapter = new InboundChannelAdapterModelElement(input,
					parent.getDiagram());
			list.add(adapter);
		}
		else if (input.getLocalName().equals(IntJdbcSchemaConstants.ELEM_OUTBOUND_CHANNEL_ADAPTER)) {
			OutboundChannelAdapterModelElement adapter = new OutboundChannelAdapterModelElement(input,
					parent.getDiagram());
			list.add(adapter);
		}
		else if (input.getLocalName().equals(IntJdbcSchemaConstants.ELEM_OUTBOUND_GATEWAY)) {
			OutboundGatewayModelElement gateway = new OutboundGatewayModelElement(input, parent.getDiagram());
			list.add(gateway);
		}
		else if (input.getLocalName().equals(IntJdbcSchemaConstants.ELEM_STORED_PROC_INBOUND_CHANNEL_ADAPTER)) {
			StoredProcInboundChannelAdapterModelElement adapter = new StoredProcInboundChannelAdapterModelElement(
					input, parent.getDiagram());
			list.add(adapter);
		}
		else if (input.getLocalName().equals(IntJdbcSchemaConstants.ELEM_STORED_PROC_OUTBOUND_CHANNEL_ADAPTER)) {
			StoredProcOutboundChannelAdapterModelElement adapter = new StoredProcOutboundChannelAdapterModelElement(
					input, parent.getDiagram());
			list.add(adapter);
		}
		else if (input.getLocalName().equals(IntJdbcSchemaConstants.ELEM_STORED_PROC_OUTBOUND_GATEWAY)) {
			StoredProcOutboundGatewayModelElement gateway = new StoredProcOutboundGatewayModelElement(input,
					parent.getDiagram());
			list.add(gateway);
		}
	}

}
