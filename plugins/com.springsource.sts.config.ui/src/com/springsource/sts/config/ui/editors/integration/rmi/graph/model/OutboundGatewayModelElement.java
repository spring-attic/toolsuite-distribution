/******************************************************************************************
 * Copyright (c) 2010 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.ui.editors.integration.rmi.graph.model;

import org.eclipse.wst.xml.core.internal.provisional.document.IDOMElement;

import com.springsource.sts.config.flow.model.AbstractConfigFlowDiagram;
import com.springsource.sts.config.ui.editors.integration.graph.model.AbstractOutboundGatewayModelElement;

/**
 * @author Leo Dos Santos
 */
@SuppressWarnings("restriction")
public class OutboundGatewayModelElement extends AbstractOutboundGatewayModelElement {

	public OutboundGatewayModelElement() {
		super();
	}

	public OutboundGatewayModelElement(IDOMElement input, AbstractConfigFlowDiagram diagram) {
		super(input, diagram);
	}

}
