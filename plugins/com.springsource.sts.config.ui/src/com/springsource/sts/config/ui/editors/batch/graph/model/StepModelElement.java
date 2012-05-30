/******************************************************************************************
 * Copyright (c) 2009 - 2010 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.ui.editors.batch.graph.model;

import java.util.Arrays;
import java.util.List;

import org.eclipse.wst.xml.core.internal.provisional.document.IDOMElement;
import org.w3c.dom.Node;

import com.springsource.sts.config.core.schemas.BatchSchemaConstants;
import com.springsource.sts.config.flow.model.AbstractConfigFlowDiagram;
import com.springsource.sts.config.flow.model.SimpleActivityWithContainer;

/**
 * @author Leo Dos Santos
 * @author Christian Dupuis
 */
@SuppressWarnings("restriction")
public class StepModelElement extends SimpleActivityWithContainer {

	public StepModelElement() {
		super();
	}

	public StepModelElement(IDOMElement input, AbstractConfigFlowDiagram diagram) {
		super(input, diagram);
	}

	@Override
	protected void createInput(String uri) {
		super.createInput(uri);
		getInput().setAttribute(BatchSchemaConstants.ATTR_ID, getNewStepId());
	}

	@Override
	public String getInputName() {
		return BatchSchemaConstants.ELEM_STEP;
	}

	private String getNewStepId() {
		String id = getInputName() + ((BatchDiagram) getDiagram()).getNewStepId();
		Node ref = getDiagram().getReferencedNode(id);
		if (ref instanceof IDOMElement) {
			// We have a duplicate. Continue to increment.
			return getNewStepId();
		}
		return id;
	}

	@Override
	public List<String> getPrimaryOutgoingAttributes() {
		return Arrays.asList(BatchSchemaConstants.ATTR_NEXT);
	}

}
