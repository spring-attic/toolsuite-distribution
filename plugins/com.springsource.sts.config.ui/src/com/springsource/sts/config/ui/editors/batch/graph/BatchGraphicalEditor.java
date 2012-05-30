/******************************************************************************************
 * Copyright (c) 2009 - 2010 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.ui.editors.batch.graph;

import com.springsource.sts.config.flow.AbstractConfigGraphicalEditor;
import com.springsource.sts.config.flow.model.AbstractConfigFlowDiagram;
import com.springsource.sts.config.flow.parts.AbstractConfigEditPartFactory;
import com.springsource.sts.config.flow.parts.AbstractConfigPaletteFactory;
import com.springsource.sts.config.ui.editors.batch.graph.model.BatchDiagram;
import com.springsource.sts.config.ui.editors.batch.graph.parts.BatchEditPartFactory;

/**
 * @author Leo Dos Santos
 * @author Christian Dupuis
 */
public class BatchGraphicalEditor extends AbstractConfigGraphicalEditor {

	@Override
	protected AbstractConfigEditPartFactory createEditPartFactory() {
		return new BatchEditPartFactory(this);
	}

	@Override
	protected AbstractConfigFlowDiagram createFlowDiagram() {
		return new BatchDiagram(this);
	}

	@Override
	protected AbstractConfigPaletteFactory createPaletteFactory() {
		return new BatchEditorPaletteFactory(this);
	}

}
