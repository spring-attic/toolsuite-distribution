/******************************************************************************************
 * Copyright (c) 2011 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.ui.editors.webflow.graph.parts;

import java.util.Map;

import org.eclipse.draw2d.graph.CompoundDirectedGraph;
import org.eclipse.draw2d.graph.Edge;
import org.eclipse.draw2d.graph.Node;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;

import com.springsource.sts.config.flow.model.LabelledTransition;
import com.springsource.sts.config.flow.parts.ActivityPart;
import com.springsource.sts.config.flow.parts.LabelledTransitionPart;

/**
 * @author Leo Dos Santos
 */
public class WebFlowTransitionPart extends LabelledTransitionPart {

	public WebFlowTransitionPart(LabelledTransition model) {
		super(model);
	}

	@Override
	public void contributeToGraph(CompoundDirectedGraph graph, Map<AbstractGraphicalEditPart, Object> map) {
		Node source = (Node) map.get(getSource());
		Node target = (Node) map.get(getTarget());
		int sourceIndex = ((ActivityPart) getSource()).getModelElement().getSortIndex();
		int targetIndex = ((ActivityPart) getTarget()).getModelElement().getSortIndex();
		Node n = (Node) map.get(this);
		if (targetIndex < sourceIndex) {
			graph.edges.add(new Edge(target, n));
			graph.edges.add(new Edge(n, source));
		}
		else {
			graph.edges.add(new Edge(source, n));
			graph.edges.add(new Edge(n, target));
		}
	}

}
