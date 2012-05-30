/******************************************************************************************
 * Copyright (c) 2011 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.ui.editors.integration.redis.graph.parts;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;

import com.springsource.sts.config.ui.editors.integration.redis.graph.model.InboundChannelAdapterModelElement;
import com.springsource.sts.config.ui.editors.integration.redis.graph.model.OutboundChannelAdaperModelElement;
import com.springsource.sts.config.ui.editors.integration.redis.graph.model.PublishSubscribeChannelModelElement;

/**
 * @author Leo Dos Santos
 */
public class IntRedisEditPartFactory implements EditPartFactory {

	public EditPart createEditPart(EditPart context, Object model) {
		EditPart part = null;
		if (model instanceof InboundChannelAdapterModelElement) {
			part = new InboundChannelAdapterGraphicalEditPart((InboundChannelAdapterModelElement) model);
		}
		else if (model instanceof OutboundChannelAdaperModelElement) {
			part = new OutboundChannelAdapterGraphicalEditPart((OutboundChannelAdaperModelElement) model);
		}
		else if (model instanceof PublishSubscribeChannelModelElement) {
			part = new PublishSubscribeChannelGraphicalEditPart((PublishSubscribeChannelModelElement) model);
		}
		return part;
	}

}
