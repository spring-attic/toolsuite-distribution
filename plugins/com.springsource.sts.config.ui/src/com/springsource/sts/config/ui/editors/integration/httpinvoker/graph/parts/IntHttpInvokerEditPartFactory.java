/******************************************************************************************
 * Copyright (c) 2010 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.ui.editors.integration.httpinvoker.graph.parts;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;

import com.springsource.sts.config.ui.editors.integration.httpinvoker.graph.model.InboundGatewayModelElement;
import com.springsource.sts.config.ui.editors.integration.httpinvoker.graph.model.OutboundGatewayModelElement;

/**
 * @author Leo Dos Santos
 */
public class IntHttpInvokerEditPartFactory implements EditPartFactory {

	public EditPart createEditPart(EditPart context, Object model) {
		EditPart part = null;
		if (model instanceof InboundGatewayModelElement) {
			part = new InboundGatewayGraphicalEditPart((InboundGatewayModelElement) model);
		}
		else if (model instanceof OutboundGatewayModelElement) {
			part = new OutboundGatewayGraphicalEditPart((OutboundGatewayModelElement) model);
		}
		return part;
	}

}
