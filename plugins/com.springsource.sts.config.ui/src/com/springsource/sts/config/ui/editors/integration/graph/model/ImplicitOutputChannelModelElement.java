/******************************************************************************************
 * Copyright (c) 2011 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.ui.editors.integration.graph.model;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.wst.xml.core.internal.provisional.document.IDOMAttr;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMElement;

import com.springsource.sts.config.core.schemas.IntegrationSchemaConstants;
import com.springsource.sts.config.flow.model.AbstractConfigFlowDiagram;
import com.springsource.sts.config.flow.model.Activity;
import com.springsource.sts.config.flow.model.Transition;

/**
 * @author Leo Dos Santos
 */
@SuppressWarnings("restriction")
public class ImplicitOutputChannelModelElement extends ImplicitChannelModelElement {

	public ImplicitOutputChannelModelElement() {
		super();
	}

	public ImplicitOutputChannelModelElement(IDOMElement input, AbstractConfigFlowDiagram diagram) {
		super(input, diagram);
	}

	@Override
	protected List<Transition> getIncomingTransitionsFromXml() {
		List<Transition> list = new ArrayList<Transition>();
		List<Activity> registry = getDiagram().getModelRegistry();
		for (Activity activity : registry) {
			List<String> labels = new ArrayList<String>();
			labels.addAll(activity.getPrimaryOutgoingAttributes());
			labels.addAll(activity.getSecondaryOutgoingAttributes());
			for (String attr : labels) {
				String ref = activity.getInput().getAttribute(attr);
				String id = getInput().getAttribute(IntegrationSchemaConstants.ATTR_OUTPUT_CHANNEL);
				if (ref != null && id != null && ref.equals(id)) {
					Transition trans = new ImplicitTransition(activity, this, (IDOMAttr) activity.getInput()
							.getAttributeNode(attr));
					list.add(trans);
				}
			}
		}
		return list;
	}

	@Override
	protected List<Transition> getOutgoingTransitionsFromXml() {
		List<Transition> list = new ArrayList<Transition>();
		List<Activity> registry = getDiagram().getModelRegistry();
		for (Activity activity : registry) {
			List<String> labels = new ArrayList<String>();
			labels.addAll(activity.getPrimaryIncomingAttributes());
			labels.addAll(activity.getSecondaryIncomingAttributes());
			for (String attr : labels) {
				String ref = activity.getInput().getAttribute(attr);
				String id = getInput().getAttribute(IntegrationSchemaConstants.ATTR_OUTPUT_CHANNEL);
				if (ref != null && id != null && ref.equals(id)) {
					Transition trans = new ImplicitTransition(this, activity, (IDOMAttr) activity.getInput()
							.getAttributeNode(attr));
					list.add(trans);
				}
			}
		}
		return list;
	}

	@Override
	protected void internalSetName() {
		String id = getInput().getAttribute(IntegrationSchemaConstants.ATTR_OUTPUT_CHANNEL);
		if (id != null && id.trim().length() > 0) {
			setName(id);
		}
		else {
			super.internalSetName();
		}
	}

}
