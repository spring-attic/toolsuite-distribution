/******************************************************************************************
 * Copyright (c) 2010 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.ui.editors.integration.stream.graph.model;

import org.eclipse.wst.xml.core.internal.provisional.document.IDOMElement;

import com.springsource.sts.config.core.schemas.IntStreamSchemaConstants;
import com.springsource.sts.config.flow.model.AbstractConfigFlowDiagram;
import com.springsource.sts.config.ui.editors.integration.graph.model.AbstractOutboundChannelAdapterModelElement;

/**
 * @author Leo Dos Santos
 */
@SuppressWarnings("restriction")
public class StdoutChannelAdapterModelElement extends AbstractOutboundChannelAdapterModelElement {

	public StdoutChannelAdapterModelElement() {
		super();
	}

	public StdoutChannelAdapterModelElement(IDOMElement input, AbstractConfigFlowDiagram diagram) {
		super(input, diagram);
	}

	@Override
	public String getInputName() {
		return IntStreamSchemaConstants.ELEM_STDOUT_CHANNEL_ADAPTER;
	}

}
