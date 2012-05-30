/******************************************************************************************
 * Copyright (c) 2010 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.ui.editors.integration.stream.graph.model;

import java.util.List;

import org.eclipse.wst.xml.core.internal.provisional.document.IDOMElement;

import com.springsource.sts.config.core.schemas.IntStreamSchemaConstants;
import com.springsource.sts.config.flow.model.Activity;
import com.springsource.sts.config.ui.editors.integration.graph.model.AbstractIntegrationModelFactory;

/**
 * @author Leo Dos Santos
 */
@SuppressWarnings("restriction")
public class IntStreamModelFactory extends AbstractIntegrationModelFactory {

	public void getChildrenFromXml(List<Activity> list, IDOMElement input, Activity parent) {
		if (input.getLocalName().equals(IntStreamSchemaConstants.ELEM_STDERR_CHANNEL_ADAPTER)) {
			StderrChannelAdapterModelElement adapter = new StderrChannelAdapterModelElement(input, parent.getDiagram());
			list.add(adapter);
		}
		else if (input.getLocalName().equals(IntStreamSchemaConstants.ELEM_STDIN_CHANNEL_ADAPTER)) {
			StdinChannelAdapterModelElement adapter = new StdinChannelAdapterModelElement(input, parent.getDiagram());
			list.add(adapter);
		}
		else if (input.getLocalName().equals(IntStreamSchemaConstants.ELEM_STDOUT_CHANNEL_ADAPTER)) {
			StdoutChannelAdapterModelElement adapter = new StdoutChannelAdapterModelElement(input, parent.getDiagram());
			list.add(adapter);
		}
	}

}
