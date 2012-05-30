/******************************************************************************************
 * Copyright (c) 2009 - 2010 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.ui.editors.integration.graph.model;

import org.eclipse.wst.xml.core.internal.provisional.document.IDOMElement;

import com.springsource.sts.config.flow.model.AbstractConfigFlowDiagram;

/**
 * @author Leo Dos Santos
 */
@SuppressWarnings("restriction")
public class ChannelModelElement extends AbstractChannelModelElement {

	public ChannelModelElement() {
		super();
	}

	// @Override
	// protected String getContainerInputName() {
	// return IntegrationSchemaConstants.ELEM_INTERCEPTORS;
	// }

	public ChannelModelElement(IDOMElement input, AbstractConfigFlowDiagram diagram) {
		super(input, diagram);
	}

}
