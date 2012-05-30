/******************************************************************************************
 * Copyright (c) 2010 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.ui.editors.batch.graph.model;

import java.util.Arrays;
import java.util.List;

import org.eclipse.wst.xml.core.internal.provisional.document.IDOMElement;

import com.springsource.sts.config.core.schemas.BatchSchemaConstants;
import com.springsource.sts.config.flow.model.AbstractConfigFlowDiagram;
import com.springsource.sts.config.flow.model.ParallelActivity;

/**
 * @author Leo Dos Santos
 */
@SuppressWarnings("restriction")
public class FlowContainerElement extends ParallelActivity {

	public FlowContainerElement() {
		super();
	}

	public FlowContainerElement(IDOMElement input, AbstractConfigFlowDiagram diagram) {
		super(input, diagram);
	}

	@Override
	public String getInputName() {
		return BatchSchemaConstants.ELEM_FLOW;

	}

	@Override
	public List<String> getPrimaryOutgoingAttributes() {
		return Arrays.asList(BatchSchemaConstants.ATTR_NEXT);
	}

}
