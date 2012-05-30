/******************************************************************************************
 * Copyright (c) 2011 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.ui.editors.integration.amqp.graph.parts;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;

import com.springsource.sts.config.ui.editors.integration.amqp.graph.model.ChannelModelElement;
import com.springsource.sts.config.ui.editors.integration.graph.IntegrationImages;
import com.springsource.sts.config.ui.editors.integration.graph.parts.AbstractChannelGraphicalEditPart;

/**
 * @author Leo Dos Santos
 */
public class ChannelGraphicalEditPart extends AbstractChannelGraphicalEditPart {

	public ChannelGraphicalEditPart(ChannelModelElement channel) {
		super(channel);
	}

	@Override
	protected IFigure createFigure() {
		Label l = (Label) super.createFigure();
		l.setIcon(IntegrationImages.getImageWithBadge(IntegrationImages.CHANNEL, IntegrationImages.BADGE_SI_AMQP));
		return l;
	}

	@Override
	public ChannelModelElement getModelElement() {
		return (ChannelModelElement) getModel();
	}

}
