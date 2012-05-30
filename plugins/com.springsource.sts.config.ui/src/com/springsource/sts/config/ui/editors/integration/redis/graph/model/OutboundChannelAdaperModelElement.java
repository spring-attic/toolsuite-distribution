/******************************************************************************************
 * Copyright (c) 2011 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.ui.editors.integration.redis.graph.model;

import org.eclipse.wst.xml.core.internal.provisional.document.IDOMElement;

import com.springsource.sts.config.flow.model.AbstractConfigFlowDiagram;
import com.springsource.sts.config.ui.editors.integration.graph.model.AbstractOutboundChannelAdapterModelElement;

/**
 * @author Leo Dos Santos
 */
@SuppressWarnings("restriction")
public class OutboundChannelAdaperModelElement extends AbstractOutboundChannelAdapterModelElement {

	public OutboundChannelAdaperModelElement() {
		super();
	}

	public OutboundChannelAdaperModelElement(IDOMElement input, AbstractConfigFlowDiagram diagram) {
		super(input, diagram);
	}

}
