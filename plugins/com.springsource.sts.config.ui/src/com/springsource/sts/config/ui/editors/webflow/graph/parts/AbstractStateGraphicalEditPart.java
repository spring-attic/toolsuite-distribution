/******************************************************************************************
 * Copyright (c) 2010 - 2011 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.ui.editors.webflow.graph.parts;

import org.eclipse.draw2d.ChopboxAnchor;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;

import com.springsource.sts.config.flow.parts.SimpleActivityPart;
import com.springsource.sts.config.flow.policies.StructuredActivityLayoutEditPolicy;
import com.springsource.sts.config.ui.editors.webflow.graph.StateDirectEditPolicy;
import com.springsource.sts.config.ui.editors.webflow.graph.StateNodeEditPolicy;
import com.springsource.sts.config.ui.editors.webflow.graph.model.AbstractStateModelElement;

/**
 * @author Leo Dos Santos
 */
public abstract class AbstractStateGraphicalEditPart extends SimpleActivityPart {

	public AbstractStateGraphicalEditPart(AbstractStateModelElement state) {
		super(state);
	}

	@Override
	protected IFigure createFigure() {
		Label l = (Label) super.createFigure();
		l.setTextPlacement(PositionConstants.SOUTH);
		return l;
	}

	@Override
	protected void createEditPolicies() {
		super.createEditPolicies();
		installEditPolicy(EditPolicy.DIRECT_EDIT_ROLE, new StateDirectEditPolicy());
		installEditPolicy(EditPolicy.GRAPHICAL_NODE_ROLE, new StateNodeEditPolicy());
		installEditPolicy(EditPolicy.LAYOUT_ROLE, new StructuredActivityLayoutEditPolicy());
	}

	@Override
	protected Dimension getFigureHint() {
		return getFigure().getPreferredSize(((Label) figure).getIcon().getBounds().width,
				((Label) figure).getIcon().getBounds().height);
	}

	@Override
	public ConnectionAnchor getSourceConnectionAnchor(ConnectionEditPart connection) {
		return new ChopboxAnchor(getFigure());
	}

	@Override
	public ConnectionAnchor getSourceConnectionAnchor(Request request) {
		return new ChopboxAnchor(getFigure());
	}

	@Override
	public ConnectionAnchor getTargetConnectionAnchor(ConnectionEditPart connection) {
		return new ChopboxAnchor(getFigure());
	}

	@Override
	public ConnectionAnchor getTargetConnectionAnchor(Request request) {
		return new ChopboxAnchor(getFigure());
	}

}
