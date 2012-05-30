/******************************************************************************************
 * Copyright (c) 2010 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.ui.editors.integration.mail.graph.model;

import java.util.List;

import org.eclipse.wst.xml.core.internal.provisional.document.IDOMElement;

import com.springsource.sts.config.core.schemas.IntMailSchemaConstants;
import com.springsource.sts.config.flow.model.Activity;
import com.springsource.sts.config.ui.editors.integration.graph.model.AbstractIntegrationModelFactory;

/**
 * @author Leo Dos Santos
 */
@SuppressWarnings("restriction")
public class IntMailModelFactory extends AbstractIntegrationModelFactory {

	public void getChildrenFromXml(List<Activity> list, IDOMElement input, Activity parent) {
		if (input.getLocalName().equals(IntMailSchemaConstants.ELEM_HEADER_ENRICHER)) {
			HeaderEnricherModelElement enricher = new HeaderEnricherModelElement(input, parent.getDiagram());
			list.add(enricher);
		}
		else if (input.getLocalName().equals(IntMailSchemaConstants.ELEM_IMAP_IDLE_CHANNEL_ADAPTER)) {
			ImapIdleChannelAdapterModelElement adapter = new ImapIdleChannelAdapterModelElement(input, parent
					.getDiagram());
			list.add(adapter);
		}
		else if (input.getLocalName().equals(IntMailSchemaConstants.ELEM_INBOUND_CHANNEL_ADAPTER)) {
			InboundChannelAdapterModelElement adapter = new InboundChannelAdapterModelElement(input, parent
					.getDiagram());
			list.add(adapter);
		}
		else if (input.getLocalName().equals(IntMailSchemaConstants.ELEM_MAIL_TO_STRING_TRANSFORMER)) {
			MailToStringTransformerModelElement transformer = new MailToStringTransformerModelElement(input, parent
					.getDiagram());
			list.add(transformer);
		}
		else if (input.getLocalName().equals(IntMailSchemaConstants.ELEM_OUTBOUND_CHANNEL_ADAPTER)) {
			OutboundChannelAdapterModelElement adapter = new OutboundChannelAdapterModelElement(input, parent
					.getDiagram());
			list.add(adapter);
		}
	}

}
