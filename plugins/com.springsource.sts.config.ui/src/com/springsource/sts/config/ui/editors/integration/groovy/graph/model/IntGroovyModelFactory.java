/******************************************************************************************
 * Copyright (c) 2010 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.ui.editors.integration.groovy.graph.model;

import java.util.List;

import org.eclipse.wst.xml.core.internal.provisional.document.IDOMElement;

import com.springsource.sts.config.core.schemas.IntGroovySchemaConstants;
import com.springsource.sts.config.flow.model.Activity;
import com.springsource.sts.config.ui.editors.integration.graph.model.AbstractIntegrationModelFactory;

/**
 * @author Leo Dos Santos
 */
@SuppressWarnings("restriction")
public class IntGroovyModelFactory extends AbstractIntegrationModelFactory {

	public void getChildrenFromXml(List<Activity> list, IDOMElement input, Activity parent) {
		if (input.getLocalName().equals(IntGroovySchemaConstants.ELEM_CONTROL_BUS)) {
			ControlBusModelElement bus = new ControlBusModelElement(input, parent.getDiagram());
			list.add(bus);
		}
	}

}
