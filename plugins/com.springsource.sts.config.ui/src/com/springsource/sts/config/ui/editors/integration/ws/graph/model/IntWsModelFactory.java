/******************************************************************************************
 * Copyright (c) 2010 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.ui.editors.integration.ws.graph.model;

import java.util.List;

import org.eclipse.wst.xml.core.internal.provisional.document.IDOMElement;

import com.springsource.sts.config.core.schemas.IntWsSchemaConstants;
import com.springsource.sts.config.flow.model.Activity;
import com.springsource.sts.config.ui.editors.integration.graph.model.AbstractIntegrationModelFactory;

/**
 * @author Leo Dos Santos
 */
@SuppressWarnings("restriction")
public class IntWsModelFactory extends AbstractIntegrationModelFactory {

	public void getChildrenFromXml(List<Activity> list, IDOMElement input, Activity parent) {
		if (input.getLocalName().equals(IntWsSchemaConstants.ELEM_HEADER_ENRICHER)) {
			HeaderEnricherModelElement enricher = new HeaderEnricherModelElement(input, parent.getDiagram());
			list.add(enricher);
		}
		else if (input.getLocalName().equals(IntWsSchemaConstants.ELEM_INBOUND_GATEWAY)) {
			InboundGatewayModelElement gateway = new InboundGatewayModelElement(input, parent.getDiagram());
			list.add(gateway);
		}
		else if (input.getLocalName().equals(IntWsSchemaConstants.ELEM_OUTBOUND_GATEWAY)) {
			OutboundGatewayModelElement gateway = new OutboundGatewayModelElement(input, parent.getDiagram());
			list.add(gateway);
		}
	}

}
