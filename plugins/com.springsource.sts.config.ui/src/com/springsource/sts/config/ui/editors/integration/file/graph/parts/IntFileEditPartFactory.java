/******************************************************************************************
 * Copyright (c) 2010 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.ui.editors.integration.file.graph.parts;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;

import com.springsource.sts.config.ui.editors.integration.file.graph.model.FileToBytesTransformerModelElement;
import com.springsource.sts.config.ui.editors.integration.file.graph.model.FileToStringTransformerModelElement;
import com.springsource.sts.config.ui.editors.integration.file.graph.model.InboundChannelAdapterModelElement;
import com.springsource.sts.config.ui.editors.integration.file.graph.model.OutboundChannelAdapterModelElement;
import com.springsource.sts.config.ui.editors.integration.file.graph.model.OutboundGatewayModelElement;

/**
 * @author Leo Dos Santos
 */
public class IntFileEditPartFactory implements EditPartFactory {

	public EditPart createEditPart(EditPart context, Object model) {
		EditPart part = null;
		if (model instanceof FileToBytesTransformerModelElement) {
			part = new FileToBytesTransformerGraphicalEditPart((FileToBytesTransformerModelElement) model);
		}
		else if (model instanceof FileToStringTransformerModelElement) {
			part = new FileToStringTransformerGraphicalEditPart((FileToStringTransformerModelElement) model);
		}
		else if (model instanceof InboundChannelAdapterModelElement) {
			part = new InboundChannelAdapterGraphicalEditPart((InboundChannelAdapterModelElement) model);
		}
		else if (model instanceof OutboundChannelAdapterModelElement) {
			part = new OutboundChannelAdapterGraphicalEditPart((OutboundChannelAdapterModelElement) model);
		}
		else if (model instanceof OutboundGatewayModelElement) {
			part = new OutboundGatewayGraphicalEditPart((OutboundGatewayModelElement) model);
		}
		return part;
	}

}
