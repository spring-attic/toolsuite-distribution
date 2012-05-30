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
import com.springsource.sts.config.ui.editors.batch.graph.NextNodeEditPolicy;
import com.springsource.sts.config.ui.editors.batch.graph.model.NextModelElement;

/**
 * @author Leo Dos Santos
 * @author Christian Dupuis
 */
public class NextGraphicalEditPart extends SimpleActivityPart {

	public NextGraphicalEditPart(NextModelElement next) {
		super(next);
	}

	@Override
	protected void createEditPolicies() {
		super.createEditPolicies();
		installEditPolicy(EditPolicy.GRAPHICAL_NODE_ROLE, new NextNodeEditPolicy());
		installEditPolicy(EditPolicy.DIRECT_EDIT_ROLE, null);
	}

	@Override
	protected IFigure createFigure() {
		Label l = (Label) super.createFigure();
		l.setIcon(CommonImages.getImage(BatchImages.NEXT));
		return l;
	}

	@Override
	public NextModelElement getModelElement() {
		return (NextModelElement) getModel();
	}

}
