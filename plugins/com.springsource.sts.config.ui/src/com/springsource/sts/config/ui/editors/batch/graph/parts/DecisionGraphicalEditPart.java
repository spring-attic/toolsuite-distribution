/******************************************************************************************
 * Copyright (c) 2009 - 2010 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.ui.editors.batch.graph.parts;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.mylyn.commons.ui.CommonImages;

import com.springsource.sts.config.flow.parts.SimpleActivityWithContainerPart;
import com.springsource.sts.config.ui.editors.batch.graph.BatchImages;
import com.springsource.sts.config.ui.editors.batch.graph.model.DecisionModelElement;

/**
 * @author Leo Dos Santos
 * @author Christian Dupuis
 */
public class DecisionGraphicalEditPart extends SimpleActivityWithContainerPart {

	public DecisionGraphicalEditPart(DecisionModelElement decision) {
		super(decision);
	}

	@Override
	protected IFigure createFigure() {
		Label l = (Label) super.createFigure();
		l.setIcon(CommonImages.getImage(BatchImages.DECISION));
		return l;
	}

	@Override
	public DecisionModelElement getModelElement() {
		return (DecisionModelElement) getModel();
	}

}
