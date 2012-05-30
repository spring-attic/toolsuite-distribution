/******************************************************************************************
 * Copyright (c) 2010 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.ui.editors.integration.jms.graph.model;

import org.eclipse.wst.xml.core.internal.provisional.document.IDOMElement;

import com.springsource.sts.config.core.schemas.IntJmsSchemaConstants;
import com.springsource.sts.config.flow.model.AbstractConfigFlowDiagram;
import com.springsource.sts.config.ui.editors.integration.graph.model.AbstractChannelModelElement;

/**
 * @author Leo Dos Santos
 */
@SuppressWarnings("restriction")
public class PublishSubscribeChannelModelElement extends AbstractChannelModelElement {

	public PublishSubscribeChannelModelElement() {
		super();
	}

	public PublishSubscribeChannelModelElement(IDOMElement input, AbstractConfigFlowDiagram diagram) {
		super(input, diagram);
	}

	@Override
	public String getInputName() {
		return IntJmsSchemaConstants.ELEM_PUBLISH_SUBSCRIBE_CHANNEL;
	}

}
