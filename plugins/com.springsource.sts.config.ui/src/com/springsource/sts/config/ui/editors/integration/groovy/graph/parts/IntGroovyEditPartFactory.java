/******************************************************************************************
 * Copyright (c) 2010 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.ui.editors.integration.groovy.graph.parts;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;

import com.springsource.sts.config.ui.editors.integration.groovy.graph.model.ControlBusModelElement;

/**
 * @author Leo Dos Santos
 */
public class IntGroovyEditPartFactory implements EditPartFactory {

	public EditPart createEditPart(EditPart context, Object model) {
		EditPart part = null;
		if (model instanceof ControlBusModelElement) {
			part = new ControlBusGraphicalEditPart((ControlBusModelElement) model);
		}
		return part;
	}

}
