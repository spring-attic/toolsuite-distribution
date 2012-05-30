/******************************************************************************************
 * Copyright (c) 2009 - 2010 SpringSource, a division of VMware, Inc. All rights reserved.
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
public class AggregatorModelElement extends Activity {

	public AggregatorModelElement() {
		super();
	}

	public AggregatorModelElement(IDOMElement input, AbstractConfigFlowDiagram diagram) {
		super(input, diagram);
	}

	@Override
	public String getInputName() {
		return IntegrationSchemaConstants.ELEM_AGGREGATOR;
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
	public List<String> getSecondaryOutgoingAttributes() {
		return Arrays.asList(IntegrationSchemaConstants.ATTR_DISCARD_CHANNEL);
	}

}
