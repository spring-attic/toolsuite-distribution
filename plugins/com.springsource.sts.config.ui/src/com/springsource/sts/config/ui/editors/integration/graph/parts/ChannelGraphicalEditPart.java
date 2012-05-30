/******************************************************************************************
 * Copyright (c) 2009 - 2010 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.ui.editors.integration.graph.parts;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;

import com.springsource.sts.config.ui.editors.integration.graph.IntegrationImages;
import com.springsource.sts.config.ui.editors.integration.graph.model.ChannelModelElement;

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
		l.setIcon(IntegrationImages.getImageWithBadge(IntegrationImages.CHANNEL, IntegrationImages.BADGE_SI));
		return l;
	}

	@Override
	public ChannelModelElement getModelElement() {
		return (ChannelModelElement) getModel();
	}

}
