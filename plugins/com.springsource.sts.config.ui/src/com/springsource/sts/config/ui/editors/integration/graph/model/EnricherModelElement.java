/******************************************************************************************
 * Copyright (c) 2011 - 2012 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.ui.editors.integration.graph.model;

import java.util.Arrays;
import java.util.List;

import org.eclipse.wst.xml.core.internal.provisional.document.IDOMElement;

import com.springsource.sts.config.core.schemas.IntegrationSchemaConstants;
import com.springsource.sts.config.flow.model.AbstractConfigFlowDiagram;
import com.springsource.sts.config.flow.model.Activity;

/**
 * @author Leo Dos Santos
 */
@SuppressWarnings("restriction")
public class EnricherModelElement extends Activity {

	public EnricherModelElement() {
		super();
	}

	public EnricherModelElement(IDOMElement input, AbstractConfigFlowDiagram diagram) {
		super(input, diagram);
	}

	@Override
	public String getInputName() {
		return IntegrationSchemaConstants.ELEM_ENRICHER;
	}

	@Override
	public List<String> getPrimaryIncomingAttributes() {
		return Arrays.asList(IntegrationSchemaConstants.ATTR_INPUT_CHANNEL);
	}

	@Override
	public List<String> getPrimaryOutgoingAttributes() {
		return Arrays.asList(IntegrationSchemaConstants.ATTR_OUTPUT_CHANNEL);
	}

	@Override
	public List<String> getSecondaryIncomingAttributes() {
		return Arrays.asList(IntegrationSchemaConstants.ATTR_REPLY_CHANNEL);
	}

	@Override
	public List<String> getSecondaryOutgoingAttributes() {
		return Arrays.asList(IntegrationSchemaConstants.ATTR_REQUEST_CHANNEL);
	}

}
