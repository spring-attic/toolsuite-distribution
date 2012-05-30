/******************************************************************************************
 * Copyright (c) 2010 - 2011 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.ui.editors.webflow.graph.model;

import java.util.List;

import org.eclipse.wst.xml.core.internal.provisional.document.IDOMElement;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.springsource.sts.config.core.schemas.WebFlowSchemaConstants;
import com.springsource.sts.config.flow.model.AbstractConfigFlowDiagram;
import com.springsource.sts.config.flow.model.Activity;
import com.springsource.sts.config.flow.model.ParallelActivity;
import com.springsource.sts.config.flow.model.Transition;

/**
 * @author Leo Dos Santos
 */
@SuppressWarnings("restriction")
public class DecisionStateModelElement extends AbstractStateModelElement {

	public DecisionStateModelElement() {
		super();
	}

	public DecisionStateModelElement(IDOMElement input, AbstractConfigFlowDiagram diagram) {
		super(input, diagram);
	}

	@Override
	public String getInputName() {
		return WebFlowSchemaConstants.ELEM_DECISION_STATE;
	}

	@Override
	protected List<Transition> getOutgoingTransitionsFromXml() {
		List<Transition> list = super.getOutgoingTransitionsFromXml();
		List<Activity> registry = getDiagram().getModelRegistry();
		NodeList transitions = getInput().getChildNodes();
		for (int i = 0; i < transitions.getLength(); i++) {
			Node node = transitions.item(i);
			if (node instanceof IDOMElement && node.getLocalName().equals(WebFlowSchemaConstants.ELEM_IF)) {
				IDOMElement transition = (IDOMElement) node;
				String state = transition.getAttribute(WebFlowSchemaConstants.ATTR_THEN);
				if (state != null && state.trim().length() > 0) {
					Node stateRef = getDiagram().getReferencedNode(state);
					if (stateRef instanceof IDOMElement) {
						for (Activity activity : registry) {
							if (!(activity instanceof ParallelActivity) && activity.getInput().equals(stateRef)) {
								Transition trans = new IfThenTransition(this, activity, transition);
								list.add(trans);
							}
						}
					}
				}
				state = transition.getAttribute(WebFlowSchemaConstants.ATTR_ELSE);
				if (state != null && state.trim().length() > 0) {
					Node stateRef = getDiagram().getReferencedNode(state);
					if (stateRef instanceof IDOMElement) {
						for (Activity activity : registry) {
							if (!(activity instanceof ParallelActivity) && activity.getInput().equals(stateRef)) {
								Transition trans = new IfElseTransition(this, activity, transition);
								list.add(trans);
							}
						}
					}
				}
			}
		}
		return list;
	}

}
