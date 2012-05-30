/******************************************************************************************
 * Copyright (c) 2010 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.ui.editors.integration.graph.parts;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.gef.EditPolicy;
import org.eclipse.mylyn.commons.ui.CommonImages;

import com.springsource.sts.config.ui.editors.integration.graph.ImplicitChannelEditPolicy;
import com.springsource.sts.config.ui.editors.integration.graph.IntegrationImages;
import com.springsource.sts.config.ui.editors.integration.graph.model.ImplicitChannelModelElement;

/**
 * @author Leo Dos Santos
 */
public class ImplicitChannelGraphicalEditPart extends BorderedIntegrationPart {

	public ImplicitChannelGraphicalEditPart(ImplicitChannelModelElement channel) {
		super(channel);
	}

	@Override
	protected void createEditPolicies() {
		super.createEditPolicies();
		installEditPolicy(EditPolicy.COMPONENT_ROLE, new ImplicitChannelEditPolicy());
		installEditPolicy(EditPolicy.DIRECT_EDIT_ROLE, null);
		installEditPolicy(EditPolicy.GRAPHICAL_NODE_ROLE, null);
	}

	@Override
	protected IFigure createFigure() {
		Label l = (Label) super.createFigure();
		l.setIcon(CommonImages.getImage(IntegrationImages.CHANNEL_GRAY));
		l.setEnabled(false);
		return l;
	}

	@Override
	public ImplicitChannelModelElement getModelElement() {
		return (ImplicitChannelModelElement) getModel();
	}

}
