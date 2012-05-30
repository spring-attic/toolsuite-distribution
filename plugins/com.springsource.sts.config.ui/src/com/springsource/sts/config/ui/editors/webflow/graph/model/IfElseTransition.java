/******************************************************************************************
 * Copyright (c) 2011 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.ui.editors.webflow.graph.model;

import org.eclipse.wst.xml.core.internal.provisional.document.IDOMElement;

import com.springsource.sts.config.core.schemas.WebFlowSchemaConstants;
import com.springsource.sts.config.flow.model.Activity;
import com.springsource.sts.config.flow.model.LabelledTransition;

@SuppressWarnings("restriction")
public class IfElseTransition extends LabelledTransition {

	public IfElseTransition(Activity source, Activity target, IDOMElement input) {
		super(source, target, input);
	}

	@Override
	public String getLabel() {
		String label = WebFlowSchemaConstants.ELEM_IF;
		String test = getInput().getAttribute(WebFlowSchemaConstants.ATTR_TEST);
		if (test != null && test.trim().length() > 0) {
			label = label + " " + test;
		}
		return (label + " false");
	}

}
