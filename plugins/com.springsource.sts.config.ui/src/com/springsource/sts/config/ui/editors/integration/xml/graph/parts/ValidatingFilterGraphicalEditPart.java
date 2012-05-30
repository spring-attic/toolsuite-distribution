/******************************************************************************************
 * Copyright (c) 2010 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.ui.editors.integration.xml.graph.parts;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;

import com.springsource.sts.config.ui.editors.integration.graph.IntegrationImages;
import com.springsource.sts.config.ui.editors.integration.graph.parts.BorderedIntegrationPart;
import com.springsource.sts.config.ui.editors.integration.xml.graph.model.ValidatingFilterModelElement;

/**
 * @author Leo Dos Santos
 */
public class ValidatingFilterGraphicalEditPart extends BorderedIntegrationPart {

	public ValidatingFilterGraphicalEditPart(ValidatingFilterModelElement router) {
		super(router);
	}

	@Override
	protected IFigure createFigure() {
		Label l = (Label) super.createFigure();
		l.setIcon(IntegrationImages.getImageWithBadge(IntegrationImages.FILTER, IntegrationImages.BADGE_SI_XML));
		return l;
	}

	@Override
	public ValidatingFilterModelElement getModelElement() {
		return (ValidatingFilterModelElement) getModel();
	}

}
