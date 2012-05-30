/******************************************************************************************
 * Copyright (c) 2009 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.flow.parts;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.draw2d.AbstractLayout;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.draw2d.graph.CompoundDirectedGraph;
import org.eclipse.draw2d.graph.CompoundDirectedGraphLayout;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;

/**
 * @author Leo Dos Santos
 * @author Christian Dupuis
 */
class GraphLayoutManager extends AbstractLayout {

	private final ActivityDiagramPart diagram;

	private final int direction;

	GraphLayoutManager(ActivityDiagramPart diagram, int direction) {
		this.diagram = diagram;
		this.direction = direction;
	}

	@Override
	protected Dimension calculatePreferredSize(IFigure container, int wHint, int hHint) {
		container.validate();
		List children = container.getChildren();
		Rectangle result = new Rectangle().setLocation(container.getClientArea().getLocation());
		for (int i = 0; i < children.size(); i++) {
			result.union(((IFigure) children.get(i)).getBounds());
		}
		result.resize(container.getInsets().getWidth(), container.getInsets().getHeight());
		return result.getSize();
	}

	public void layout(IFigure container) {
		GraphAnimation.recordInitialState(container);
		if (GraphAnimation.playbackState(container)) {
			return;
		}

		CompoundDirectedGraph graph = new CompoundDirectedGraph();
		if (direction == PositionConstants.EAST) {
			graph.setDirection(direction);
		}
		Map<AbstractGraphicalEditPart, Object> partsToNodes = new HashMap<AbstractGraphicalEditPart, Object>();
		diagram.contributeNodesToGraph(graph, null, partsToNodes);
		diagram.contributeEdgesToGraph(graph, partsToNodes);
		new CompoundDirectedGraphLayout().visit(graph);
		diagram.applyGraphResults(graph, partsToNodes);
		diagram.setBoundsOnModel();
	}

}
