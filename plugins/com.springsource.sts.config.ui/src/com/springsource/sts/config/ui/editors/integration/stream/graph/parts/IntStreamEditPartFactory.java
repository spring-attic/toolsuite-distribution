/******************************************************************************************
 * Copyright (c) 2010 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.ui.editors.integration.stream.graph.parts;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;

import com.springsource.sts.config.ui.editors.integration.stream.graph.model.StderrChannelAdapterModelElement;
import com.springsource.sts.config.ui.editors.integration.stream.graph.model.StdinChannelAdapterModelElement;
import com.springsource.sts.config.ui.editors.integration.stream.graph.model.StdoutChannelAdapterModelElement;

/**
 * @author Leo Dos Santos
 */
public class IntStreamEditPartFactory implements EditPartFactory {

	public EditPart createEditPart(EditPart context, Object model) {
		EditPart part = null;
		if (model instanceof StderrChannelAdapterModelElement) {
			part = new StderrChannelAdapterGraphicalEditPart((StderrChannelAdapterModelElement) model);
		}
		else if (model instanceof StdinChannelAdapterModelElement) {
			part = new StdinChannelAdapterGraphicalEditPart((StdinChannelAdapterModelElement) model);
		}
		else if (model instanceof StdoutChannelAdapterModelElement) {
			part = new StdoutChannelAdapterGraphicalEditPart((StdoutChannelAdapterModelElement) model);
		}
		return part;
	}

}
