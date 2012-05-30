/******************************************************************************************
 * Copyright (c) 2010 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.ui.editors.integration.graph;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.editpolicies.GraphicalEditPolicy;

import com.springsource.sts.config.flow.model.Activity;
import com.springsource.sts.config.ui.editors.integration.graph.parts.BorderedIntegrationPart;

/**
 * @author Leo Dos Santos
 */
public class LabelHighlightEditPolicy extends GraphicalEditPolicy {

	@Override
	public void eraseTargetFeedback(Request request) {
		EditPart part = getHost();
		if (part instanceof BorderedIntegrationPart) {
			BorderedIntegrationPart activityPart = (BorderedIntegrationPart) part;
			Activity activity = activityPart.getModelElement();
			activity.setDisplayLabel(activity.getShortName());
			activityPart.refresh();
		}
	}

	@Override
	public void showTargetFeedback(Request request) {
		EditPart part = getHost();
		if (part instanceof BorderedIntegrationPart) {
			BorderedIntegrationPart activityPart = (BorderedIntegrationPart) part;
			Activity activity = activityPart.getModelElement();
			activity.setDisplayLabel(activity.getInputName());
			activityPart.refresh();
		}
	}

}
