/******************************************************************************************
 * Copyright (c) 2009 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.ui.editors.batch.graph.parts;

import org.eclipse.draw2d.PositionConstants;

import com.springsource.sts.config.flow.parts.ActivityDiagramPart;
import com.springsource.sts.config.ui.editors.batch.graph.model.BatchDiagram;

/**
 * @author Leo Dos Santos
 * @author Christian Dupuis
 */
public class BatchDiagramEditPart extends ActivityDiagramPart {

	public BatchDiagramEditPart(BatchDiagram diagram) {
		super(diagram, PositionConstants.SOUTH);

	}

	@Override
	public BatchDiagram getModelElement() {
		return (BatchDiagram) getModel();
	}

}
