/******************************************************************************************
 * Copyright (c) 2010 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.ui.editors.integration.jmx.graph.parts;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;

import com.springsource.sts.config.ui.editors.integration.graph.IntegrationImages;
import com.springsource.sts.config.ui.editors.integration.graph.parts.BorderedIntegrationPart;
import com.springsource.sts.config.ui.editors.integration.jmx.graph.model.NotificationListeningChannelAdapterModelElement;

/**
 * @author Leo Dos Santos
 */
public class NotificationListeningChannelAdapterGraphicalEditPart extends BorderedIntegrationPart {

	public NotificationListeningChannelAdapterGraphicalEditPart(NotificationListeningChannelAdapterModelElement adapter) {
		super(adapter);
	}

	@Override
	protected IFigure createFigure() {
		Label l = (Label) super.createFigure();
		l.setIcon(IntegrationImages
				.getImageWithBadge(IntegrationImages.INBOUND_ADAPTER, IntegrationImages.BADGE_SI_JMX));
		return l;
	}

	@Override
	public NotificationListeningChannelAdapterModelElement getModelElement() {
		return (NotificationListeningChannelAdapterModelElement) getModel();
	}

}
