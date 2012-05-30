/******************************************************************************************
 * Copyright (c) 2009 - 2011 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.flow.parts;

import java.util.List;
import java.util.Map;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.graph.CompoundDirectedGraph;
import org.eclipse.draw2d.graph.Edge;
import org.eclipse.draw2d.graph.Node;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;

import com.springsource.sts.config.flow.figures.ParallelActivityFigure;
import com.springsource.sts.config.flow.figures.SubgraphFigure;
import com.springsource.sts.config.flow.model.ParallelActivity;

/**
 * @author Leo Dos Santos
 * @author Christian Dupuis
 */
public abstract class ParallelActivityPart extends StructuredActivityPart {

	public ParallelActivityPart(ParallelActivity activity) {
		super(activity);
	}

	@Override
	protected void contributeEdgesToGraph(CompoundDirectedGraph graph, Map<AbstractGraphicalEditPart, Object> map) {
		super.contributeEdgesToGraph(graph, map);
		Node node, prev = null;
		EditPart a;
		List members = getChildren();
		for (int n = 0; n < members.size(); n++) {
			a = (EditPart) members.get(n);
			node = (Node) map.get(a);
			if (!(a instanceof StructuredActivityPart)) {
				if (prev != null) {
					Edge e = new Edge(prev, node);
					graph.edges.add(e);
				}
				prev = node;
			}
		}
	}

	@Override
	protected IFigure createFigure() {
		int direction = PositionConstants.SOUTH;
		EditPart part = getViewer().getContents();
		if (part instanceof ActivityDiagramPart) {
			ActivityDiagramPart diagramPart = (ActivityDiagramPart) part;
			direction = diagramPart.getDirection();
		}
		return new ParallelActivityFigure(direction);
	}

	/**
	 * @see org.eclipse.gef.EditPart#setSelected(int)
	 */
	@Override
	public void setSelected(int value) {
		super.setSelected(value);
		SubgraphFigure sf = (SubgraphFigure) getFigure();
		sf.setSelected(value != SELECTED_NONE);
	}

}
