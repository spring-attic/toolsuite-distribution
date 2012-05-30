/******************************************************************************************
 * Copyright (c) 2009 - 2010 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.flow.model;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.gef.requests.CreationFactory;
import org.springsource.ide.eclipse.commons.core.StatusHandler;

import com.springsource.sts.config.flow.ConfigFlowPlugin;

/**
 * @author Leo Dos Santos
 */
public class ModelElementCreationFactory implements CreationFactory {

	private final Class<? extends AbstractConfigFlowModelElement> type;

	private final AbstractConfigFlowDiagram diagram;

	private final String uri;

	public ModelElementCreationFactory(Class<? extends AbstractConfigFlowModelElement> type,
			AbstractConfigFlowDiagram diagram) {
		this(type, diagram, null);
	}

	public ModelElementCreationFactory(Class<? extends AbstractConfigFlowModelElement> type,
			AbstractConfigFlowDiagram diagram, String namespaceUri) {
		this.type = type;
		this.diagram = diagram;
		this.uri = namespaceUri;
	}

	public AbstractConfigFlowModelElement getNewObject() {
		try {
			AbstractConfigFlowModelElement model = type.newInstance();
			model.setDiagram(diagram);
			model.createInput(uri);
			return model;
		}
		catch (Exception e) {
			StatusHandler.log(new Status(IStatus.ERROR, ConfigFlowPlugin.PLUGIN_ID,
					Messages.ModelElementCreationFactory_ERROR_CREATING_ELEMENT_MODEL, e));
			return null;
		}
	}

	public Class<? extends AbstractConfigFlowModelElement> getObjectType() {
		return type;
	}

}
