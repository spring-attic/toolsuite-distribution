/******************************************************************************************
 * Copyright (c) 2010 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.ui.editors.integration.ip.graph.model;

import org.eclipse.wst.xml.core.internal.provisional.document.IDOMElement;

import com.springsource.sts.config.core.schemas.IntIpSchemaConstants;
import com.springsource.sts.config.flow.model.AbstractConfigFlowDiagram;
import com.springsource.sts.config.ui.editors.integration.graph.model.AbstractOutboundGatewayModelElement;

/**
 * @author Leo Dos Santos
 */
@SuppressWarnings("restriction")
public class TcpOutboundGatewayModelElement extends AbstractOutboundGatewayModelElement {

	public TcpOutboundGatewayModelElement() {
		super();
	}

	public TcpOutboundGatewayModelElement(IDOMElement input, AbstractConfigFlowDiagram diagram) {
		super(input, diagram);
	}

	@Override
	public String getInputName() {
		return IntIpSchemaConstants.ELEM_TCP_OUTBOUND_GATEWAY;
	}

}
