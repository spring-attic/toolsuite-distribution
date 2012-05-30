/******************************************************************************************
 * Copyright (c) 2010 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.ui.editors.integration.twitter.graph.parts;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;

import com.springsource.sts.config.ui.editors.integration.twitter.graph.model.DmInboundChannelAdapterModelElement;
import com.springsource.sts.config.ui.editors.integration.twitter.graph.model.DmOutboundChannelAdapterModelElement;
import com.springsource.sts.config.ui.editors.integration.twitter.graph.model.InboundChannelAdapterModelElement;
import com.springsource.sts.config.ui.editors.integration.twitter.graph.model.MentionsInboundChannelAdapterModelElement;
import com.springsource.sts.config.ui.editors.integration.twitter.graph.model.OutboundChannelAdapterModelElement;
import com.springsource.sts.config.ui.editors.integration.twitter.graph.model.SearchInboundChannelAdapterModelElement;

/**
 * @author Leo Dos Santos
 */
public class IntTwitterEditPartFactory implements EditPartFactory {

	public EditPart createEditPart(EditPart context, Object model) {
		EditPart part = null;
		if (model instanceof DmInboundChannelAdapterModelElement) {
			part = new DmInboundChannelAdapterGraphicalEditPart((DmInboundChannelAdapterModelElement) model);
		}
		else if (model instanceof DmOutboundChannelAdapterModelElement) {
			part = new DmOutboundChannelAdapterGraphicalEditPart((DmOutboundChannelAdapterModelElement) model);
		}
		else if (model instanceof InboundChannelAdapterModelElement) {
			part = new InboundChannelAdapterGraphicalEditPart((InboundChannelAdapterModelElement) model);
		}
		else if (model instanceof MentionsInboundChannelAdapterModelElement) {
			part = new MentionsInboundChannelAdapterGraphicalEditPart((MentionsInboundChannelAdapterModelElement) model);
		}
		else if (model instanceof OutboundChannelAdapterModelElement) {
			part = new OutboundChannelAdapterGraphicalEditPart((OutboundChannelAdapterModelElement) model);
		}
		else if (model instanceof SearchInboundChannelAdapterModelElement) {
			part = new SearchInboundChannelAdapterGraphicalEditPart((SearchInboundChannelAdapterModelElement) model);
		}
		return part;
	}

}
