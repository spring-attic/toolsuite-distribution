/******************************************************************************************
 * Copyright (c) 2009 - 2010 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.ui.editors.integration.graph.model;

import org.eclipse.wst.xml.core.internal.provisional.document.IDOMElement;

import com.springsource.sts.config.core.schemas.IntegrationSchemaConstants;
import com.springsource.sts.config.flow.model.AbstractConfigFlowDiagram;

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

	// @Override
	// protected String getContainerInputName() {
	// return IntegrationSchemaConstants.ELEM_INTERCEPTORS;
	// }

	@Override
	public String getInputName() {
		return IntegrationSchemaConstants.ELEM_PUBLISH_SUBSCRIBE_CHANNEL;
	}

}
