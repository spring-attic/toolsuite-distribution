/******************************************************************************************
 * Copyright (c) 2010 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.flow.parts;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.ActionEvent;
import org.eclipse.draw2d.ActionListener;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.gef.EditPart;

import com.springsource.sts.config.flow.figures.CollapsibleContainerFigure;
import com.springsource.sts.config.flow.model.Activity;
import com.springsource.sts.config.flow.model.ParallelActivity;

/**
 * @author Leo Dos Santos
 */
public abstract class CollapsibleContainerPart extends ParallelActivityPart implements ActionListener {

	public CollapsibleContainerPart(ParallelActivity activity) {
		super(activity);
	}

	public void actionPerformed(ActionEvent event) {
		refreshAll();
	}

	@Override
	protected IFigure createFigure() {
		int direction = PositionConstants.SOUTH;
		EditPart part = getViewer().getContents();
		if (part instanceof ActivityDiagramPart) {
			ActivityDiagramPart diagramPart = (ActivityDiagramPart) part;
			direction = diagramPart.getDirection();
		}
		CollapsibleContainerFigure figure = new CollapsibleContainerFigure(direction);
		figure.addActionListener(this);
		return figure;
	}

	@Override
	protected List<Activity> getModelChildren() {
		CollapsibleContainerFigure figure = (CollapsibleContainerFigure) getFigure();
		if (figure.isExpanded()) {
			return super.getModelChildren();
		}
		else {
			return new ArrayList<Activity>();
		}
	}

}
