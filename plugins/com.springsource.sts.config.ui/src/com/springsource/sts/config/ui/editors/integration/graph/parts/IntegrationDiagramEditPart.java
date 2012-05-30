/******************************************************************************************
 * Copyright (c) 2009 - 2010 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.ui.editors.integration.graph.parts;

import org.eclipse.draw2d.PositionConstants;

import com.springsource.sts.config.flow.parts.ActivityDiagramPart;
import com.springsource.sts.config.ui.editors.integration.graph.model.IntegrationDiagram;

/**
 * @author Leo Dos Santos
 */
public class IntegrationDiagramEditPart extends ActivityDiagramPart {

	public IntegrationDiagramEditPart(IntegrationDiagram diagram) {
		super(diagram, PositionConstants.EAST);
	}

	@Override
	public IntegrationDiagram getModelElement() {
		return (IntegrationDiagram) getModel();
	}

}
