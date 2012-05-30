/******************************************************************************************
 * Copyright (c) 2009 - 2010 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.ui.editors.batch.graph.model;

import com.springsource.sts.config.flow.AbstractConfigGraphicalEditor;
import com.springsource.sts.config.flow.model.AbstractConfigFlowDiagram;
import com.springsource.sts.config.flow.model.IDiagramModelFactory;

/**
 * @author Leo Dos Santos
 * @author Christian Dupuis
 */
public class BatchDiagram extends AbstractConfigFlowDiagram {

	private int stepCount;

	public BatchDiagram(AbstractConfigGraphicalEditor editor) {
		super(editor);
	}

	@Override
	protected IDiagramModelFactory getModelFactory() {
		return new BatchModelFactory();
	}

	public String getNewStepId() {
		return Integer.toString(++stepCount);
	}

}
