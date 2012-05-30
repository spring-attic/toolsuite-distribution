/******************************************************************************************
 * Copyright (c) 2010 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.ui.editors.integration.xmpp.graph.parts;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;

import com.springsource.sts.config.ui.editors.integration.xmpp.graph.model.HeaderEnricherModelElement;
import com.springsource.sts.config.ui.editors.integration.xmpp.graph.model.InboundChannelAdapterModelElement;
import com.springsource.sts.config.ui.editors.integration.xmpp.graph.model.OutboundChannelAdapterModelElement;
import com.springsource.sts.config.ui.editors.integration.xmpp.graph.model.PresenceInboundChannelAdapterModelElement;
import com.springsource.sts.config.ui.editors.integration.xmpp.graph.model.PresenceOutboundChannelAdapterModelElement;

/**
 * @author Leo Dos Santos
 */
public class IntXmppEditPartFactory implements EditPartFactory {

	public EditPart createEditPart(EditPart context, Object model) {
		EditPart part = null;
		if (model instanceof HeaderEnricherModelElement) {
			part = new HeaderEnricherGraphicalEditPart((HeaderEnricherModelElement) model);
		}
		else if (model instanceof InboundChannelAdapterModelElement) {
			part = new InboundChannelAdapterGraphicalEditPart((InboundChannelAdapterModelElement) model);
		}
		else if (model instanceof OutboundChannelAdapterModelElement) {
			part = new OutboundChannelAdapterGraphicalEditPart((OutboundChannelAdapterModelElement) model);
		}
		else if (model instanceof PresenceInboundChannelAdapterModelElement) {
			part = new PresenceInboundChannelAdapterGraphicalEditPart(
					(PresenceInboundChannelAdapterModelElement) model);
		}
		else if (model instanceof PresenceOutboundChannelAdapterModelElement) {
			part = new PresenceOutboundChannelAdapterGraphicalEditPart(
					(PresenceOutboundChannelAdapterModelElement) model);
		}
		return part;
	}

}
