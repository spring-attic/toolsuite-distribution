/******************************************************************************************
 * Copyright (c) 2010 - 2011 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.ui.editors.integration.graph.model;

import java.util.List;

import org.eclipse.wst.xml.core.internal.provisional.document.IDOMElement;
import org.w3c.dom.Node;

import com.springsource.sts.config.core.schemas.IntegrationSchemaConstants;
import com.springsource.sts.config.flow.model.AbstractConfigFlowDiagram;
import com.springsource.sts.config.flow.model.Activity;
import com.springsource.sts.config.flow.model.IModelFactory;

/**
 * @author Leo Dos Santos
 */
@SuppressWarnings("restriction")
public abstract class AbstractIntegrationModelFactory implements IModelFactory {

	public void getNestedChildrenFromXml(List<Activity> list, IDOMElement input, AbstractConfigFlowDiagram diagram) {
		String channelRef = input.getAttribute(IntegrationSchemaConstants.ATTR_INPUT_CHANNEL);
		if (channelRef == null || channelRef.trim().length() == 0) {
			channelRef = input.getAttribute(IntegrationSchemaConstants.ATTR_REQUEST_CHANNEL);
		}
		if (channelRef != null && channelRef.trim().length() > 0) {
			Node channelNode = diagram.getReferencedNode(channelRef);
			if (channelNode == null) {
				ImplicitChannelModelElement channel = new ImplicitChannelModelElement(input, diagram);
				if (!diagram.listContainsElement(list, channel)) {
					list.add(channel);
				}
			}
		}

		channelRef = input.getAttribute(IntegrationSchemaConstants.ATTR_OUTPUT_CHANNEL);
		if (channelRef != null
				&& (channelRef.equalsIgnoreCase("errorChannel") || channelRef.equalsIgnoreCase("nullChannel"))) { //$NON-NLS-1$ //$NON-NLS-2$
			Node channelNode = diagram.getReferencedNode(channelRef);
			if (channelNode == null) {
				ImplicitOutputChannelModelElement channel = new ImplicitOutputChannelModelElement(input, diagram);
				if (!diagram.listContainsElement(list, channel)) {
					list.add(channel);
				}
			}
		}
	}

}
