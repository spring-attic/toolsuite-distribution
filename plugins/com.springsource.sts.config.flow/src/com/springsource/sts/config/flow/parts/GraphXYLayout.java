/******************************************************************************************
 * Copyright (c) 2009 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.flow.parts;

import org.eclipse.draw2d.FreeformLayout;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Rectangle;

/**
 * @author Leo Dos Santos
 * @author Christian Dupuis
 */
public class GraphXYLayout extends FreeformLayout {

	private final ActivityDiagramPart diagram;

	private final GraphLayoutManager autoLayout;

	GraphXYLayout(ActivityDiagramPart diagram, int direction) {
		this.diagram = diagram;
		autoLayout = new GraphLayoutManager(diagram, direction);
	}

	@Override
	public Object getConstraint(IFigure figure) {
		Object constraint = constraints.get(figure);
		if (constraint instanceof Rectangle) {
			Rectangle constraintRect = (Rectangle) constraint;
			Rectangle bounds = figure.getBounds();
			return new Rectangle(constraintRect.x, constraintRect.y, bounds.width, bounds.height);
		}
		else {
			return figure.getBounds();
		}
	}

	@Override
	public void layout(IFigure parent) {
		autoLayout.layout(parent);
		super.layout(parent);
		diagram.setBoundsOnModel();
	}
}
