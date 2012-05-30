/******************************************************************************************
 * Copyright (c) 2011 - 2012 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.flow.parts;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.draw2d.AbsoluteBendpoint;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.ConnectionLocator;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.PolylineConnection;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.draw2d.graph.CompoundDirectedGraph;
import org.eclipse.draw2d.graph.Edge;
import org.eclipse.draw2d.graph.EdgeList;
import org.eclipse.draw2d.graph.Node;
import org.eclipse.draw2d.graph.NodeList;
import org.eclipse.draw2d.graph.Subgraph;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;

import com.springsource.sts.config.flow.figures.TransitionLabel;
import com.springsource.sts.config.flow.model.LabelledTransition;

/**
 * @author Leo Dos Santos
 */
public class LabelledTransitionPart extends TransitionPart {

	private Label l;

	public LabelledTransitionPart(LabelledTransition model) {
		super(model);
	}

	@Override
	protected void applyGraphResults(CompoundDirectedGraph graph, Map<AbstractGraphicalEditPart, Object> map) {
		if (!isManualLayout()) {
			Node n = (Node) map.get(this);
			List bends = new ArrayList();
			applyBends(bends, n.incoming);
			applyBends(bends, n.outgoing);
			getConnectionFigure().setRoutingConstraint(bends);
		}
	}

	protected void applyBends(List bends, EdgeList edges) {
		for (int i = 0; i < edges.size(); i++) {
			Edge e = (Edge) edges.get(i);
			NodeList nodes = e.vNodes;
			if (nodes != null) {
				for (int j = 0; j < nodes.size(); j++) {
					Node vn = nodes.getNode(i);
					int x = vn.x;
					int y = vn.y;
					if (e.isFeedback()) {
						bends.add(new AbsoluteBendpoint(x, y + vn.height));
						bends.add(new AbsoluteBendpoint(x, y));
					}
					else {
						bends.add(new AbsoluteBendpoint(x, y));
						bends.add(new AbsoluteBendpoint(x, y + vn.height));
					}
				}
			}
		}
	}

	@Override
	public void contributeNodesToGraph(CompoundDirectedGraph graph, Subgraph s,
			Map<AbstractGraphicalEditPart, Object> map) {
		Node n = new Node(this, s);
		Dimension hint = getConnectionFigure().getPreferredSize();
		n.width = hint.width;
		n.height = hint.height;
		if (graph.getDirection() == PositionConstants.EAST) {
			n.setPadding(new Insets(8, 10, 12, 10));
		}
		else {
			n.setPadding(new Insets(10, 8, 10, 12));
		}
		map.put(this, n);
		graph.nodes.add(n);
	}

	@Override
	public void contributeToGraph(CompoundDirectedGraph graph, Map<AbstractGraphicalEditPart, Object> map) {
		Node source = (Node) map.get(getSource());
		Node target = (Node) map.get(getTarget());
		Node n = (Node) map.get(this);
		graph.edges.add(new Edge(source, n));
		graph.edges.add(new Edge(n, target));
	}

	@Override
	protected IFigure createFigure() {
		PolylineConnection conn = (PolylineConnection) super.createFigure();
		l = new TransitionLabel(75);
		l.setForegroundColor(ColorConstants.black);
		l.setOpaque(true);
		conn.add(l, new ConnectionLocator(conn));
		return conn;
	}

	protected void refreshFigureVisuals() {
		if (l != null) {
			l.setText(((LabelledTransition) getModel()).getLabel());
		}
	}

	protected void refreshTooltipVisuals() {
		if (l != null) {
			Label tooltip = (Label) l.getToolTip();
			if (tooltip == null) {
				tooltip = new Label();
				l.setToolTip(tooltip);
			}
			tooltip.setText(((LabelledTransition) getModel()).getLabel());
		}
	}

	@Override
	protected void refreshVisuals() {
		refreshFigureVisuals();
		refreshTooltipVisuals();
	}

}
