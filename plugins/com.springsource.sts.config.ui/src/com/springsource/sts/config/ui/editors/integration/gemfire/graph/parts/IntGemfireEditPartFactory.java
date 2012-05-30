/******************************************************************************************
 * Copyright (c) 2011 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.ui.editors.integration.gemfire.graph.parts;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;

import com.springsource.sts.config.ui.editors.integration.gemfire.graph.model.CqInboundChannelAdapterModelElement;
import com.springsource.sts.config.ui.editors.integration.gemfire.graph.model.InboundChannelAdapterModelElement;
import com.springsource.sts.config.ui.editors.integration.gemfire.graph.model.OutboundChannelAdapterModelElement;

/**
 * @author Leo Dos Santos
 */
public class IntGemfireEditPartFactory implements EditPartFactory {

	public EditPart createEditPart(EditPart context, Object model) {
		EditPart part = null;
		if (model instanceof CqInboundChannelAdapterModelElement) {
			part = new CqInboundChannelAdapterGraphicalEditPart((CqInboundChannelAdapterModelElement) model);
		}
		else if (model instanceof InboundChannelAdapterModelElement) {
			part = new InboundChannelAdapterGraphicalEditPart((InboundChannelAdapterModelElement) model);
		}
		else if (model instanceof OutboundChannelAdapterModelElement) {
			part = new OutboundChannelAdapterGraphicalEditPart((OutboundChannelAdapterModelElement) model);
		}
		return part;
	}

}
