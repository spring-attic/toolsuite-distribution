/******************************************************************************************
 * Copyright (c) 2009 - 2011 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.ui.editors.batch.graph.parts;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.gef.EditPolicy;
import org.eclipse.mylyn.commons.ui.CommonImages;

import com.springsource.sts.config.flow.parts.SimpleActivityPart;
import com.springsource.sts.config.ui.editors.batch.graph.BatchImages;
import com.springsource.sts.config.ui.editors.batch.graph.model.FailModelElement;

/**
 * @author Leo Dos Santos
 * @author Christian Dupuis
 */
public class FailGraphicalEditPart extends SimpleActivityPart {

	public FailGraphicalEditPart(FailModelElement fail) {
		super(fail);
	}

	@Override
	protected void createEditPolicies() {
		super.createEditPolicies();
		installEditPolicy(EditPolicy.DIRECT_EDIT_ROLE, null);
	}

	@Override
	protected IFigure createFigure() {
		Label l = (Label) super.createFigure();
		l.setIcon(CommonImages.getImage(BatchImages.FAIL));
		return l;
	}

	@Override
	public FailModelElement getModelElement() {
		return (FailModelElement) getModel();
	}

}
