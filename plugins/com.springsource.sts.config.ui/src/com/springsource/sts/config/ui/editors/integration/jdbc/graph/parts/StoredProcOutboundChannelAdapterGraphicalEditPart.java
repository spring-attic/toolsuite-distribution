/******************************************************************************************
 * Copyright (c) 2011 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.ui.editors.integration.jdbc.graph.parts;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;

import com.springsource.sts.config.ui.editors.integration.graph.IntegrationImages;
import com.springsource.sts.config.ui.editors.integration.graph.parts.BorderedIntegrationPart;
import com.springsource.sts.config.ui.editors.integration.jdbc.graph.model.StoredProcOutboundChannelAdapterModelElement;

/**
 * @author Leo Dos Santos
 */
public class StoredProcOutboundChannelAdapterGraphicalEditPart extends BorderedIntegrationPart {

	public StoredProcOutboundChannelAdapterGraphicalEditPart(StoredProcOutboundChannelAdapterModelElement adapter) {
		super(adapter);
	}

	@Override
	protected IFigure createFigure() {
		Label l = (Label) super.createFigure();
		l.setIcon(IntegrationImages.getImageWithBadge(IntegrationImages.OUTBOUND_ADAPTER,
				IntegrationImages.BADGE_SI_JDBC));
		return l;
	}

	@Override
	public StoredProcOutboundChannelAdapterModelElement getModelElement() {
		return (StoredProcOutboundChannelAdapterModelElement) getModel();
	}

}
