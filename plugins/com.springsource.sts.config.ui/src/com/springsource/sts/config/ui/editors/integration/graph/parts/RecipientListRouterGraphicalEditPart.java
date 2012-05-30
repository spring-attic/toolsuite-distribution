/******************************************************************************************
 * Copyright (c) 2009 - 2010 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.ui.editors.integration.graph.parts;

import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.requests.CreateConnectionRequest;

import com.springsource.sts.config.flow.parts.ActivityDiagramPart;
import com.springsource.sts.config.flow.parts.BottomAnchor;
import com.springsource.sts.config.flow.parts.RightAnchor;
import com.springsource.sts.config.flow.policies.FixedConnectionNodeEditPolicy;
import com.springsource.sts.config.ui.editors.integration.graph.IntegrationImages;
import com.springsource.sts.config.ui.editors.integration.graph.RecipientListRouterNodeEditPolicy;
import com.springsource.sts.config.ui.editors.integration.graph.model.RecipientListRouterModelElement;
import com.springsource.sts.config.ui.editors.integration.graph.model.AlternateTransition;

/**
 * @author Leo Dos Santos
 */
public class RecipientListRouterGraphicalEditPart extends BorderedIntegrationPart {

	public RecipientListRouterGraphicalEditPart(RecipientListRouterModelElement router) {
		super(router);
	}

	@Override
	protected IFigure createFigure() {
		Label l = (Label) super.createFigure();
		l.setIcon(IntegrationImages.getImageWithBadge(IntegrationImages.RECIPIENT_LIST, IntegrationImages.BADGE_SI));
		return l;
	}

	@Override
	protected FixedConnectionNodeEditPolicy getFixedConnectionNodeEditPolicy() {
		return new RecipientListRouterNodeEditPolicy();
	}

	@Override
	public RecipientListRouterModelElement getModelElement() {
		return (RecipientListRouterModelElement) getModel();
	}

	@Override
	public ConnectionAnchor getSourceConnectionAnchor(ConnectionEditPart connection) {
		if (connection instanceof AlternateTransitionPart) {
			return getRecipientConnectionAnchor();
		}
		return super.getSourceConnectionAnchor(connection);
	}

	@Override
	public ConnectionAnchor getSourceConnectionAnchor(Request request) {
		if (request instanceof CreateConnectionRequest) {
			CreateConnectionRequest create = (CreateConnectionRequest) request;
			if (AlternateTransition.class == create.getNewObjectType()) {
				return getRecipientConnectionAnchor();
			}
		}
		return super.getSourceConnectionAnchor(request);
	}

	private ConnectionAnchor getRecipientConnectionAnchor() {
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
