/******************************************************************************************
 * Copyright (c) 2011 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.ui.editors.integration.amqp.graph.model;

import org.eclipse.wst.xml.core.internal.provisional.document.IDOMElement;

import com.springsource.sts.config.core.schemas.IntAmqpSchemaConstants;
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
		return IntAmqpSchemaConstants.ELEM_PUBLISH_SUBSCRIBE_CHANNEL;
	}

}
