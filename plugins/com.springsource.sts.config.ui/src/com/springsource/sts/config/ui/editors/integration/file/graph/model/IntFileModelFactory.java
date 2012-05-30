/******************************************************************************************
 * Copyright (c) 2010 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.ui.editors.integration.file.graph.model;

import java.util.List;

import org.eclipse.wst.xml.core.internal.provisional.document.IDOMElement;

import com.springsource.sts.config.core.schemas.IntFileSchemaConstants;
import com.springsource.sts.config.flow.model.Activity;
import com.springsource.sts.config.ui.editors.integration.graph.model.AbstractIntegrationModelFactory;

/**
 * @author Leo Dos Santos
 */
@SuppressWarnings("restriction")
public class IntFileModelFactory extends AbstractIntegrationModelFactory {

	public void getChildrenFromXml(List<Activity> list, IDOMElement input, Activity parent) {
		if (input.getLocalName().equals(IntFileSchemaConstants.ELEM_FILE_TO_BYTES_TRANSFORMER)) {
			FileToBytesTransformerModelElement transformer = new FileToBytesTransformerModelElement(input, parent
					.getDiagram());
			list.add(transformer);
		}
		else if (input.getLocalName().equals(IntFileSchemaConstants.ELEM_FILE_TO_STRING_TRANSFORMER)) {
			FileToStringTransformerModelElement transformer = new FileToStringTransformerModelElement(input, parent
					.getDiagram());
			list.add(transformer);
		}
		else if (input.getLocalName().equals(IntFileSchemaConstants.ELEM_INBOUND_CHANNEL_ADAPTER)) {
			InboundChannelAdapterModelElement adapter = new InboundChannelAdapterModelElement(input, parent
					.getDiagram());
			list.add(adapter);
		}
		else if (input.getLocalName().equals(IntFileSchemaConstants.ELEM_OUTBOUND_CHANNEL_ADAPTER)) {
			OutboundChannelAdapterModelElement adapter = new OutboundChannelAdapterModelElement(input, parent
					.getDiagram());
			list.add(adapter);
		}
		else if (input.getLocalName().equals(IntFileSchemaConstants.ELEM_OUTBOUND_GATEWAY)) {
			OutboundGatewayModelElement gateway = new OutboundGatewayModelElement(input, parent.getDiagram());
			list.add(gateway);
		}
	}

}
