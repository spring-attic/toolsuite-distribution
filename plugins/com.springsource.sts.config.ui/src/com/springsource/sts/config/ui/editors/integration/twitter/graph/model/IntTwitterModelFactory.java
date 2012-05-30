/******************************************************************************************
 * Copyright (c) 2010 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.ui.editors.integration.twitter.graph.model;

import java.util.List;

import org.eclipse.wst.xml.core.internal.provisional.document.IDOMElement;

import com.springsource.sts.config.core.schemas.IntTwitterSchemaConstants;
import com.springsource.sts.config.flow.model.Activity;
import com.springsource.sts.config.ui.editors.integration.graph.model.AbstractIntegrationModelFactory;

/**
 * @author Leo Dos Santos
 */
@SuppressWarnings("restriction")
public class IntTwitterModelFactory extends AbstractIntegrationModelFactory {

	public void getChildrenFromXml(List<Activity> list, IDOMElement input, Activity parent) {
		if (input.getLocalName().equals(IntTwitterSchemaConstants.ELEM_DM_INBOUND_CHANNEL_ADAPTER)) {
			DmInboundChannelAdapterModelElement adapter = new DmInboundChannelAdapterModelElement(input,
					parent.getDiagram());
			list.add(adapter);
		}
		else if (input.getLocalName().equals(IntTwitterSchemaConstants.ELEM_DM_OUTBOUND_CHANNEL_ADAPTER)) {
			DmOutboundChannelAdapterModelElement adapter = new DmOutboundChannelAdapterModelElement(input,
					parent.getDiagram());
			list.add(adapter);
		}
		else if (input.getLocalName().equals(IntTwitterSchemaConstants.ELEM_INBOUND_CHANNEL_ADAPTER)) {
			InboundChannelAdapterModelElement adapter = new InboundChannelAdapterModelElement(input,
					parent.getDiagram());
			list.add(adapter);
		}
		else if (input.getLocalName().equals(IntTwitterSchemaConstants.ELEM_MENTIONS_INBOUND_CHANNEL_ADAPTER)) {
			MentionsInboundChannelAdapterModelElement adapter = new MentionsInboundChannelAdapterModelElement(input,
					parent.getDiagram());
			list.add(adapter);
		}
		else if (input.getLocalName().equals(IntTwitterSchemaConstants.ELEM_OUTBOUND_CHANNEL_ADAPTER)) {
			OutboundChannelAdapterModelElement adapter = new OutboundChannelAdapterModelElement(input,
					parent.getDiagram());
			list.add(adapter);
		}
		else if (input.getLocalName().equals(IntTwitterSchemaConstants.ELEM_SEARCH_INBOUND_CHANNEL_ADAPTER)) {
			SearchInboundChannelAdapterModelElement adapter = new SearchInboundChannelAdapterModelElement(input,
					parent.getDiagram());
			list.add(adapter);
		}
	}

}
