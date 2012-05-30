/******************************************************************************************
 * Copyright (c) 2009 - 2010 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.ui.editors.integration.graph.model;

import java.util.List;

import org.eclipse.wst.xml.core.internal.provisional.document.IDOMElement;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.springsource.sts.config.core.schemas.IntegrationSchemaConstants;
import com.springsource.sts.config.flow.model.AbstractConfigFlowDiagram;
import com.springsource.sts.config.flow.model.Activity;
import com.springsource.sts.config.flow.model.ParallelActivity;

/**
 * @author Leo Dos Santos
 */
@SuppressWarnings("restriction")
public class ChainContainerElement extends ParallelActivity {

	public ChainContainerElement() {
		super();
	}

	public ChainContainerElement(IDOMElement input, AbstractConfigFlowDiagram diagram) {
		super(input, diagram);
	}

	@Override
	public String getInputName() {
		return IntegrationSchemaConstants.ELEM_CHAIN;
	}

	@Override
	protected void updateTransitionsFromXml() {
		super.updateTransitionsFromXml();
		if (getChildren().size() > 1) {
			IDOMElement input = getInput();
			if (input != null && input.hasChildNodes()) {
				IDOMElement prev = null;
				IDOMElement next = null;
				NodeList children = input.getChildNodes();
				for (int i = 0; i < children.getLength(); i++) {
					Node child = children.item(i);
					if (child instanceof IDOMElement) {
						prev = next;
						next = (IDOMElement) child;
					}
					if (prev != null && next != null) {
						Activity prevActivity = null;
						Activity nextActivity = null;
						List<Activity> parts = getChildren();
						for (Activity activity : parts) {
							if (prev.equals(activity.getInput())) {
								prevActivity = activity;
							}
							if (next.equals(activity.getInput())) {
								nextActivity = activity;
							}
						}
						if (nextActivity != null && prevActivity != null) {
							new ImplicitTransition(prevActivity, nextActivity, null);
						}
					}
				}
			}
		}
	}

}
