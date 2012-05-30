/******************************************************************************************
 * Copyright (c) 2010 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.ui.editors.integration.xml.graph.parts;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;

import com.springsource.sts.config.ui.editors.integration.graph.IntegrationImages;
import com.springsource.sts.config.ui.editors.integration.graph.parts.BorderedIntegrationPart;
import com.springsource.sts.config.ui.editors.integration.xml.graph.model.XpathHeaderEnricherModelElement;

/**
 * @author Leo Dos Santos
 */
public class XpathHeaderEnricherGraphicalEditPart extends BorderedIntegrationPart {

	public XpathHeaderEnricherGraphicalEditPart(XpathHeaderEnricherModelElement enricher) {
		super(enricher);
	}

	@Override
	protected IFigure createFigure() {
		Label l = (Label) super.createFigure();
		l.setIcon(IntegrationImages.getImageWithBadge(IntegrationImages.ENRICHER, IntegrationImages.BADGE_SI_XML));
		return l;
	}

	@Override
	public XpathHeaderEnricherModelElement getModelElement() {
		return (XpathHeaderEnricherModelElement) getModel();
	}

}
