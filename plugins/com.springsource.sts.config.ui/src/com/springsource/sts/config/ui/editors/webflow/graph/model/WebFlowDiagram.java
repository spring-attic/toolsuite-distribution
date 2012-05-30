/******************************************************************************************
 * Copyright (c) 2010 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.ui.editors.webflow.graph.model;

import com.springsource.sts.config.flow.AbstractConfigGraphicalEditor;
import com.springsource.sts.config.flow.model.AbstractConfigFlowDiagram;
import com.springsource.sts.config.flow.model.IDiagramModelFactory;

/**
 * @author Leo Dos Santos
 */
public class WebFlowDiagram extends AbstractConfigFlowDiagram {

	public WebFlowDiagram(AbstractConfigGraphicalEditor editor) {
		super(editor);
	}

	@Override
	protected IDiagramModelFactory getModelFactory() {
		return new WebFlowModelFactory();
	}

}
