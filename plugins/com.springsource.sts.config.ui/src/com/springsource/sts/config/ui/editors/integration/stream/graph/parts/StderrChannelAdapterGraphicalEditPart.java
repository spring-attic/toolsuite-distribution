/******************************************************************************************
 * Copyright (c) 2010 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.ui.editors.integration.stream.graph.parts;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;

import com.springsource.sts.config.ui.editors.integration.graph.IntegrationImages;
import com.springsource.sts.config.ui.editors.integration.graph.parts.BorderedIntegrationPart;
import com.springsource.sts.config.ui.editors.integration.stream.graph.model.StderrChannelAdapterModelElement;

/**
 * @author Leo Dos Santos
 */
public class StderrChannelAdapterGraphicalEditPart extends BorderedIntegrationPart {

	public StderrChannelAdapterGraphicalEditPart(StderrChannelAdapterModelElement adapter) {
		super(adapter);
	}

	@Override
	protected IFigure createFigure() {
		Label l = (Label) super.createFigure();
		l.setIcon(IntegrationImages.getImageWithBadge(IntegrationImages.OUTBOUND_ADAPTER,
				IntegrationImages.BADGE_SI_STREAM));
		return l;
	}

	@Override
	public StderrChannelAdapterModelElement getModelElement() {
		return (StderrChannelAdapterModelElement) getModel();
	}

}
