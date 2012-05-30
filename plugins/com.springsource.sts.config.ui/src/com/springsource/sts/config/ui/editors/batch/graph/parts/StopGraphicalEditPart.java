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
import com.springsource.sts.config.ui.editors.batch.graph.StopNodeEditPolicy;
import com.springsource.sts.config.ui.editors.batch.graph.model.StopModelElement;

/**
 * @author Leo Dos Santos
 * @author Christian Dupuis
 */
public class StopGraphicalEditPart extends SimpleActivityPart {

	public StopGraphicalEditPart(StopModelElement stop) {
		super(stop);
	}

	@Override
	protected void createEditPolicies() {
		super.createEditPolicies();
		installEditPolicy(EditPolicy.GRAPHICAL_NODE_ROLE, new StopNodeEditPolicy());
		installEditPolicy(EditPolicy.DIRECT_EDIT_ROLE, null);
	}

	@Override
	protected IFigure createFigure() {
		Label l = (Label) super.createFigure();
		l.setIcon(CommonImages.getImage(BatchImages.STOP));
		return l;
	}

	@Override
	public StopModelElement getModelElement() {
		return (StopModelElement) getModel();
	}

}
