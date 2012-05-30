/******************************************************************************************
 * Copyright (c) 2010 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.ui.editors.webflow.graph.model;

import org.eclipse.wst.xml.core.internal.provisional.document.IDOMElement;

import com.springsource.sts.config.core.schemas.WebFlowSchemaConstants;
import com.springsource.sts.config.flow.model.AbstractConfigFlowDiagram;

/**
 * @author Leo Dos Santos
 */
@SuppressWarnings("restriction")
public class ViewStateModelElement extends AbstractStateModelElement {

	public ViewStateModelElement() {
		super();
	}

	public ViewStateModelElement(IDOMElement input, AbstractConfigFlowDiagram diagram) {
		super(input, diagram);
	}

	@Override
	public String getInputName() {
		return WebFlowSchemaConstants.ELEM_VIEW_STATE;
	}

}
