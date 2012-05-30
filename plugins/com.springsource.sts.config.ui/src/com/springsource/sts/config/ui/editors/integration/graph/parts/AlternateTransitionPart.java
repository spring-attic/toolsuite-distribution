/******************************************************************************************
 * Copyright (c) 2010 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.ui.editors.integration.graph.parts;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PolylineConnection;

import com.springsource.sts.config.flow.model.Transition;
import com.springsource.sts.config.flow.parts.TransitionPart;

/**
 * @author Leo Dos Santos
 */
public class AlternateTransitionPart extends TransitionPart {

	public AlternateTransitionPart(Transition model) {
		super(model);
	}

	@Override
	protected IFigure createFigure() {
		PolylineConnection conn = (PolylineConnection) super.createFigure();
		// conn.setForegroundColor(ColorConstants.blue);
		return conn;
	}

}
