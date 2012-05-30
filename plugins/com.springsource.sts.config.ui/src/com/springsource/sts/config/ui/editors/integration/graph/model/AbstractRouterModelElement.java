/******************************************************************************************
 * Copyright (c) 2010 SpringSource, a division of VMware, Inc. All rights reserved.
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
import com.springsource.sts.config.flow.model.Transition;

/**
 * @author Leo Dos Santos
 */
@SuppressWarnings("restriction")
public abstract class AbstractRouterModelElement extends Activity {

	public AbstractRouterModelElement() {
		super();
	}

	public AbstractRouterModelElement(IDOMElement input, AbstractConfigFlowDiagram diagram) {
		super(input, diagram);
	}

	@Override
	protected List<Transition> getOutgoingTransitionsFromXml() {
		List<Transition> list = super.getOutgoingTransitionsFromXml();
		List<Activity> registry = getDiagram().getModelRegistry();
		NodeList mappings = getInput().getChildNodes();
		for (int i = 0; i < mappings.getLength(); i++) {
			Node node = mappings.item(i);
			if (node instanceof IDOMElement && node.getLocalName().equals(IntegrationSchemaConstants.ELEM_MAPPING)) {
				IDOMElement mapping = (IDOMElement) node;
				String channel = mapping.getAttribute(IntegrationSchemaConstants.ATTR_CHANNEL);
				if (channel != null && channel.trim().length() > 0) {
					Node channelRef = getDiagram().getReferencedNode(channel);
					if (channelRef instanceof IDOMElement) {
						for (Activity activity : registry) {
							if (!(activity instanceof ParallelActivity) && activity.getInput().equals(channelRef)) {
								Transition trans = new AlternateTransition(this, activity, mapping);
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
