/******************************************************************************************
 * Copyright (c) 2010 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.ui.editors.integration.graph.model;

import java.util.Arrays;
import java.util.List;

import org.eclipse.wst.xml.core.internal.provisional.document.IDOMElement;

import com.springsource.sts.config.core.schemas.IntHttpSchemaConstants;
import com.springsource.sts.config.flow.model.AbstractConfigFlowDiagram;

/**
 * @author Leo Dos Santos
 */
@SuppressWarnings("restriction")
public abstract class AbstractOutboundGatewayModelElement extends AbstractGatewayModelElement {

	public AbstractOutboundGatewayModelElement() {
		super();
	}

	public AbstractOutboundGatewayModelElement(IDOMElement input, AbstractConfigFlowDiagram diagram) {
		super(input, diagram);
	}

	@Override
	public String getInputName() {
		return IntHttpSchemaConstants.ELEM_OUTBOUND_GATEWAY;
	}

	@Override
	public List<String> getPrimaryIncomingAttributes() {
		return Arrays.asList(IntHttpSchemaConstants.ATTR_REQUEST_CHANNEL);
	}

	@Override
	public List<String> getPrimaryOutgoingAttributes() {
		return Arrays.asList(IntHttpSchemaConstants.ATTR_REPLY_CHANNEL);
	}
	
}
