/******************************************************************************************
 * Copyright (c) 2010 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.ui.editors.integration.graph.parts;

import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.requests.CreateConnectionRequest;

import com.springsource.sts.config.flow.parts.ActivityDiagramPart;
import com.springsource.sts.config.flow.parts.BottomAnchor;
import com.springsource.sts.config.flow.parts.RightAnchor;
import com.springsource.sts.config.flow.policies.FixedConnectionNodeEditPolicy;
import com.springsource.sts.config.ui.editors.integration.graph.ChannelNodeEditPolicy;
import com.springsource.sts.config.ui.editors.integration.graph.model.AbstractChannelModelElement;
import com.springsource.sts.config.ui.editors.integration.graph.model.AlternateTransition;

/**
 * @author Leo Dos Santos
 */
public abstract class AbstractChannelGraphicalEditPart extends BorderedIntegrationPart {

	public AbstractChannelGraphicalEditPart(AbstractChannelModelElement channel) {
		super(channel);
	}

	@Override
	protected FixedConnectionNodeEditPolicy getFixedConnectionNodeEditPolicy() {
		return new ChannelNodeEditPolicy();
	}

	@Override
	public ConnectionAnchor getSourceConnectionAnchor(ConnectionEditPart connection) {
		if (connection instanceof AlternateTransitionPart) {
			return getWireTapConnectionAnchor();
		}
		return super.getSourceConnectionAnchor(connection);
	}

	@Override
	public ConnectionAnchor getSourceConnectionAnchor(Request request) {
		if (request instanceof CreateConnectionRequest) {
			CreateConnectionRequest create = (CreateConnectionRequest) request;
			if (AlternateTransition.class == create.getNewObjectType()) {
				return getWireTapConnectionAnchor();
			}
		}
		return super.getSourceConnectionAnchor(request);
	}

	private ConnectionAnchor getWireTapConnectionAnchor() {
		EditPart part = getViewer().getContents();
		if (part instanceof ActivityDiagramPart) {
			ActivityDiagramPart diagram = (ActivityDiagramPart) part;
			if (diagram.getDirection() == PositionConstants.EAST) {
				return new BottomAnchor(getFigure(), getAnchorOffset());
			}
		}
		return new RightAnchor(getFigure(), getAnchorOffset());
	}

}
