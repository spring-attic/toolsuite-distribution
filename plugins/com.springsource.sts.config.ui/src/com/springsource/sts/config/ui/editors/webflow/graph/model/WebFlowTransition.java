/******************************************************************************************
 * Copyright (c) 2011 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.ui.editors.webflow.graph.model;

import org.eclipse.wst.xml.core.internal.provisional.document.IDOMElement;

import com.springsource.sts.config.core.schemas.WebFlowSchemaConstants;
import com.springsource.sts.config.flow.model.Activity;
import com.springsource.sts.config.flow.model.LabelledTransition;

/**
 * @author Leo Dos Santos
 */
@SuppressWarnings("restriction")
public class WebFlowTransition extends LabelledTransition {

	public WebFlowTransition(Activity source, Activity target, IDOMElement input) {
		super(source, target, input);
	}

	@Override
	public String getLabel() {
		String id = getInput().getAttribute(WebFlowSchemaConstants.ATTR_ON);
		if (id != null && id.trim().length() > 0) {
			return id;
		}
		else {
			return WebFlowSchemaConstants.ELEM_TRANSITION;
		}
	}

}
