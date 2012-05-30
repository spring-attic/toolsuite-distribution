/******************************************************************************************
 * Copyright (c) 2010 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.ui.editors.integration.xmpp.graph.model;

import java.util.List;

import org.eclipse.wst.xml.core.internal.provisional.document.IDOMElement;

import com.springsource.sts.config.core.schemas.IntXmppSchemaConstants;
import com.springsource.sts.config.flow.model.Activity;
import com.springsource.sts.config.ui.editors.integration.graph.model.AbstractIntegrationModelFactory;

/**
 * @author Leo Dos Santos
 */
@SuppressWarnings("restriction")
public class IntXmppModelFactory extends AbstractIntegrationModelFactory {

	public void getChildrenFromXml(List<Activity> list, IDOMElement input, Activity parent) {
		if (input.getLocalName().equals(IntXmppSchemaConstants.ELEM_HEADER_ENRICHER)) {
			HeaderEnricherModelElement enricher = new HeaderEnricherModelElement(input, parent.getDiagram());
			list.add(enricher);
		}
		else if (input.getLocalName().equals(IntXmppSchemaConstants.ELEM_INBOUND_CHANNEL_ADAPTER)) {
			InboundChannelAdapterModelElement adapter = new InboundChannelAdapterModelElement(input,
					parent.getDiagram());
			list.add(adapter);
		}
		else if (input.getLocalName().equals(IntXmppSchemaConstants.ELEM_OUTBOUND_CHANNEL_ADAPTER)) {
			OutboundChannelAdapterModelElement adapter = new OutboundChannelAdapterModelElement(input,
					parent.getDiagram());
			list.add(adapter);
		}
		else if (input.getLocalName().equals(IntXmppSchemaConstants.ELEM_PRESENCE_INBOUND_CHANNEL_ADAPTER)) {
			PresenceInboundChannelAdapterModelElement adapter = new PresenceInboundChannelAdapterModelElement(
					input, parent.getDiagram());
			list.add(adapter);
		}
		else if (input.getLocalName().equals(IntXmppSchemaConstants.ELEM_PRESENCE_OUTBOUND_CHANNEL_ADAPTER)) {
			PresenceOutboundChannelAdapterModelElement adapter = new PresenceOutboundChannelAdapterModelElement(
					input, parent.getDiagram());
			list.add(adapter);
		}
	}

}
