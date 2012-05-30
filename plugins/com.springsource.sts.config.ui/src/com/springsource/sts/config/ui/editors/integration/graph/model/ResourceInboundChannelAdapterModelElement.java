/******************************************************************************************
 * Copyright (c) 2011 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.ui.editors.integration.graph.model;

import org.eclipse.wst.xml.core.internal.provisional.document.IDOMElement;

import com.springsource.sts.config.flow.model.AbstractConfigFlowDiagram;

/**
 * @author Leo Dos Santos
 */
@SuppressWarnings("restriction")
public class ResourceInboundChannelAdapterModelElement extends AbstractInboundChannelAdapterModelElement {

	public ResourceInboundChannelAdapterModelElement() {
		super();
	}

	public ResourceInboundChannelAdapterModelElement(IDOMElement input, AbstractConfigFlowDiagram diagram) {
		super(input, diagram);
	}

}
