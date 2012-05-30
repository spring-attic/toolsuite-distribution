/******************************************************************************************
 * Copyright (c) 2011 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.ui.editors.integration.gemfire.graph.model;

import org.eclipse.wst.xml.core.internal.provisional.document.IDOMElement;

import com.springsource.sts.config.flow.model.AbstractConfigFlowDiagram;
import com.springsource.sts.config.ui.editors.integration.graph.model.AbstractInboundChannelAdapterModelElement;

/**
 * @author Leo Dos Santos
 */
@SuppressWarnings("restriction")
public class CqInboundChannelAdapterModelElement extends AbstractInboundChannelAdapterModelElement {

	public CqInboundChannelAdapterModelElement() {
		super();
	}

	public CqInboundChannelAdapterModelElement(IDOMElement input, AbstractConfigFlowDiagram diagram) {
		super(input, diagram);
	}

}
