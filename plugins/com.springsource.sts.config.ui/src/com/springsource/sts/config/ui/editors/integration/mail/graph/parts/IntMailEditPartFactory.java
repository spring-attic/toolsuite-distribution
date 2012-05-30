/******************************************************************************************
 * Copyright (c) 2010 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.ui.editors.integration.mail.graph.parts;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;

import com.springsource.sts.config.ui.editors.integration.mail.graph.model.HeaderEnricherModelElement;
import com.springsource.sts.config.ui.editors.integration.mail.graph.model.ImapIdleChannelAdapterModelElement;
import com.springsource.sts.config.ui.editors.integration.mail.graph.model.InboundChannelAdapterModelElement;
import com.springsource.sts.config.ui.editors.integration.mail.graph.model.MailToStringTransformerModelElement;
import com.springsource.sts.config.ui.editors.integration.mail.graph.model.OutboundChannelAdapterModelElement;

/**
 * @author Leo Dos Santos
 */
public class IntMailEditPartFactory implements EditPartFactory {

	public EditPart createEditPart(EditPart context, Object model) {
		EditPart part = null;
		if (model instanceof HeaderEnricherModelElement) {
			part = new HeaderEnricherGraphicalEditPart((HeaderEnricherModelElement) model);
		}
		else if (model instanceof ImapIdleChannelAdapterModelElement) {
			part = new ImapIdleChannelAdapterGraphicalEditPart((ImapIdleChannelAdapterModelElement) model);
		}
		else if (model instanceof InboundChannelAdapterModelElement) {
			part = new InboundChannelAdapterGraphicalEditPart((InboundChannelAdapterModelElement) model);
		}
		else if (model instanceof MailToStringTransformerModelElement) {
			part = new MailToStringTransformerGraphicalEditPart((MailToStringTransformerModelElement) model);
		}
		else if (model instanceof OutboundChannelAdapterModelElement) {
			part = new OutboundChannelAdapterGraphicalEditPart((OutboundChannelAdapterModelElement) model);
		}
		return part;
	}

}
