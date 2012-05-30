/******************************************************************************************
 * Copyright (c) 2010 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.ui.editors.integration.graph.parts;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.gef.EditPolicy;
import org.eclipse.mylyn.commons.ui.CommonImages;

import com.springsource.sts.config.ui.editors.integration.graph.IntegrationImages;
import com.springsource.sts.config.ui.editors.integration.graph.model.PlaceholderModelElement;

/**
 * @author Leo Dos Santos
 */
public class PlaceholderGraphicalEditPart extends BorderedIntegrationPart {

	public PlaceholderGraphicalEditPart(PlaceholderModelElement placeholder) {
		super(placeholder);
	}

	@Override
	protected void createEditPolicies() {
		super.createEditPolicies();
		installEditPolicy(EditPolicy.COMPONENT_ROLE, null);
		installEditPolicy(EditPolicy.DIRECT_EDIT_ROLE, null);
		installEditPolicy(EditPolicy.GRAPHICAL_NODE_ROLE, null);
	}

	@Override
	protected IFigure createFigure() {
		Label l = (Label) super.createFigure();
		l.setIcon(CommonImages.getImage(IntegrationImages.PLACEHOLDER));
		l.setEnabled(false);
		return l;
	}

	@Override
	public PlaceholderModelElement getModelElement() {
		return (PlaceholderModelElement) getModel();
	}

}
