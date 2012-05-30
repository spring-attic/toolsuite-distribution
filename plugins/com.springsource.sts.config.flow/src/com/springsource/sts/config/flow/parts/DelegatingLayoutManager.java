/******************************************************************************************
 * Copyright (c) 2009 - 2010 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.flow.parts;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.LayoutManager;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPolicy;

import com.springsource.sts.config.flow.policies.GraphXYLayoutPolicy;
import com.springsource.sts.config.flow.policies.StructuredActivityLayoutEditPolicy;

/**
 * @author Leo Dos Santos
 * @author Christian Dupuis
 */
public class DelegatingLayoutManager implements LayoutManager {

	private final ActivityDiagramPart diagram;

	private LayoutManager activeLayoutManager;

	private final GraphLayoutManager graphLayoutManager;

	private final GraphXYLayout xyLayoutManager;

	DelegatingLayoutManager(ActivityDiagramPart diagram) {
		this.diagram = diagram;
		graphLayoutManager = new GraphLayoutManager(diagram, diagram.getDirection());
		xyLayoutManager = new GraphXYLayout(diagram, diagram.getDirection());
		activeLayoutManager = graphLayoutManager;
	}

	public LayoutManager getActiveLayoutManager() {
		return activeLayoutManager;
	}

	public Object getConstraint(IFigure figure) {
		return activeLayoutManager.getConstraint(figure);
	}

	public Dimension getMinimumSize(IFigure figure, int wHint, int hHint) {
		return activeLayoutManager.getMinimumSize(figure, wHint, hHint);
	}

	public Dimension getPreferredSize(IFigure figure, int wHint, int hHint) {
		return activeLayoutManager.getPreferredSize(figure, wHint, hHint);
	}

	public void invalidate() {
		activeLayoutManager.invalidate();
	}

	public void layout(IFigure figure) {
		if (diagram.isManualLayout()) {
			if (diagram.isFirstManualLayout()) {
				setLayoutManager(figure, graphLayoutManager);
				activeLayoutManager.layout(figure);
			}
			diagram.setBoundsOnFigure(true);
			setLayoutManager(figure, xyLayoutManager);
			activeLayoutManager.layout(figure);
		}
		else {
			setLayoutManager(figure, graphLayoutManager);
			activeLayoutManager.layout(figure);
		}
	}

	public void remove(IFigure figure) {
		activeLayoutManager.remove(figure);
	}

	public void setConstraint(IFigure figure, Object constraint) {
		activeLayoutManager.setConstraint(figure, constraint);
	}

	public void setLayoutManager(IFigure figure, LayoutManager layoutManager) {
		figure.setLayoutManager(layoutManager);
		this.activeLayoutManager = layoutManager;
		if (layoutManager == xyLayoutManager) {
			diagram.installEditPolicy(EditPolicy.LAYOUT_ROLE, new GraphXYLayoutPolicy());
		}
		else {
			diagram.installEditPolicy(EditPolicy.LAYOUT_ROLE, new StructuredActivityLayoutEditPolicy());
		}
	}

	public void setXYLayoutConstraint(IFigure figure, Rectangle constraint) {
		xyLayoutManager.setConstraint(figure, constraint);
	}

}
