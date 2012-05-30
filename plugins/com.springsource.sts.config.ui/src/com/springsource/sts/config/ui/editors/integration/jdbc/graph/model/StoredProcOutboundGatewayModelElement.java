/******************************************************************************************
 * Copyright (c) 2011 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.ui.editors.integration.jdbc.graph.model;

import org.eclipse.wst.xml.core.internal.provisional.document.IDOMElement;

import com.springsource.sts.config.core.schemas.IntJdbcSchemaConstants;
import com.springsource.sts.config.flow.model.AbstractConfigFlowDiagram;
import com.springsource.sts.config.ui.editors.integration.graph.model.AbstractOutboundGatewayModelElement;

/**
 * @author Leo Dos Santos
 */
@SuppressWarnings("restriction")
public class StoredProcOutboundGatewayModelElement extends AbstractOutboundGatewayModelElement {

	public StoredProcOutboundGatewayModelElement() {
		super();
	}

	public StoredProcOutboundGatewayModelElement(IDOMElement input, AbstractConfigFlowDiagram diagram) {
		super(input, diagram);
	}

	@Override
	public String getInputName() {
		return IntJdbcSchemaConstants.ELEM_STORED_PROC_OUTBOUND_GATEWAY;
	}

}
