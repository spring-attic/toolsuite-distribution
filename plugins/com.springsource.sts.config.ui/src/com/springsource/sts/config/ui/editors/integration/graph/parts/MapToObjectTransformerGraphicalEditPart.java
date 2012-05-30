/******************************************************************************************
 * Copyright (c) 2010 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.ui.editors.integration.graph.parts;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;

import com.springsource.sts.config.ui.editors.integration.graph.IntegrationImages;
import com.springsource.sts.config.ui.editors.integration.graph.model.MapToObjectTransformerModelElement;

/**
 * @author Leo Dos Santos
 */
public class MapToObjectTransformerGraphicalEditPart extends BorderedIntegrationPart {

	public MapToObjectTransformerGraphicalEditPart(MapToObjectTransformerModelElement transformer) {
		super(transformer);
	}

	@Override
	protected IFigure createFigure() {
		Label l = (Label) super.createFigure();
		l.setIcon(IntegrationImages.getImageWithBadge(IntegrationImages.TRANSFORMER, IntegrationImages.BADGE_SI));
		return l;
	}

	@Override
	public MapToObjectTransformerModelElement getModelElement() {
		return (MapToObjectTransformerModelElement) getModel();
	}

}
