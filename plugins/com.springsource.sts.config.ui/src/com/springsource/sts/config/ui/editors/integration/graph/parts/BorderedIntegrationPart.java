/******************************************************************************************
 * Copyright (c) 2010 - 2011 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.ui.editors.integration.graph.parts;

import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.gef.EditPolicy;

import com.springsource.sts.config.flow.model.Activity;
import com.springsource.sts.config.flow.parts.BorderedActivityPart;
import com.springsource.sts.config.flow.policies.ActivityNodeEditPolicy;
import com.springsource.sts.config.flow.policies.FixedConnectionNodeEditPolicy;
import com.springsource.sts.config.ui.editors.integration.graph.ChannelDirectEditPolicy;
import com.springsource.sts.config.ui.editors.integration.graph.FixedConnectionChannelCreatePolicy;
import com.springsource.sts.config.ui.editors.integration.graph.LabelHighlightEditPolicy;

/**
 * @author Leo Dos Santos
 */
public abstract class BorderedIntegrationPart extends BorderedActivityPart {

	public BorderedIntegrationPart(Activity activity) {
		super(activity);
	}

	@Override
	protected void createEditPolicies() {
		super.createEditPolicies();
		installEditPolicy(EditPolicy.SELECTION_FEEDBACK_ROLE, new LabelHighlightEditPolicy());
		installEditPolicy(EditPolicy.DIRECT_EDIT_ROLE, new ChannelDirectEditPolicy());
	}

	@Override
	protected Dimension getFigureHint() {
		return getFigure().getPreferredSize(figure.getIcon().getBounds().width, figure.getIcon().getBounds().height);
	}

	@Override
	protected FixedConnectionNodeEditPolicy getFixedConnectionNodeEditPolicy() {
		return new FixedConnectionChannelCreatePolicy();
	}

	@Override
	protected ActivityNodeEditPolicy getStandardConnectionNodeEditPolicy() {
		return null;
	}

	@Override
	protected void performDirectEdit() {
		super.performDirectEdit();
		manager.getCellEditor().setValue(getModelElement().getName());
	}

	@Override
	protected void refreshFigureVisuals() {
		((Label) getFigure()).setText(getModelElement().getDisplayLabel());
	}

}
