/******************************************************************************************
 * Copyright (c) 2010 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.ui.editors.integration.ws.graph.parts;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;

import com.springsource.sts.config.ui.editors.integration.ws.graph.model.HeaderEnricherModelElement;
import com.springsource.sts.config.ui.editors.integration.ws.graph.model.InboundGatewayModelElement;
import com.springsource.sts.config.ui.editors.integration.ws.graph.model.OutboundGatewayModelElement;

/**
 * @author Leo Dos Santos
 */
public class IntWsEditPartFactory implements EditPartFactory {

	public EditPart createEditPart(EditPart context, Object model) {
		EditPart part = null;
		if (model instanceof HeaderEnricherModelElement) {
			part = new HeaderEnricherGraphicalEditPart((HeaderEnricherModelElement) model);
		}
		else if (model instanceof InboundGatewayModelElement) {
			part = new InboundGatewayGraphicalEditPart((InboundGatewayModelElement) model);
		}
		else if (model instanceof OutboundGatewayModelElement) {
			part = new OutboundGatewayGraphicalEditPart((OutboundGatewayModelElement) model);
		}
		return part;
	}

}
