/******************************************************************************************
 * Copyright (c) 2011 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.ui.editors.integration.amqp.graph.parts;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.gef.palette.CombinedTemplateCreationEntry;
import org.eclipse.gef.palette.PaletteDrawer;
import org.eclipse.gef.palette.PaletteEntry;

import com.springsource.sts.config.core.schemas.IntAmqpSchemaConstants;
import com.springsource.sts.config.flow.model.AbstractConfigFlowDiagram;
import com.springsource.sts.config.flow.model.ModelElementCreationFactory;
import com.springsource.sts.config.flow.parts.IPaletteFactory;
import com.springsource.sts.config.ui.editors.integration.amqp.graph.model.ChannelModelElement;
import com.springsource.sts.config.ui.editors.integration.amqp.graph.model.InboundChannelAdapterModelElement;
import com.springsource.sts.config.ui.editors.integration.amqp.graph.model.InboundGatewayModelElement;
import com.springsource.sts.config.ui.editors.integration.amqp.graph.model.OutboundChannelAdapterModelElement;
import com.springsource.sts.config.ui.editors.integration.amqp.graph.model.OutboundGatewayModelElement;
import com.springsource.sts.config.ui.editors.integration.amqp.graph.model.PublishSubscribeChannelModelElement;
import com.springsource.sts.config.ui.editors.integration.graph.IntegrationImages;

/**
 * @author Leo Dos Santos
 */
public class IntAmqpPaletteFactory implements IPaletteFactory {

	public PaletteDrawer createPaletteDrawer(AbstractConfigFlowDiagram diagram, String namespaceUri) {
		PaletteDrawer drawer = new PaletteDrawer("", IntegrationImages.BADGE_SI_AMQP); //$NON-NLS-1$
		List<PaletteEntry> entries = new ArrayList<PaletteEntry>();

		CombinedTemplateCreationEntry entry = new CombinedTemplateCreationEntry(IntAmqpSchemaConstants.ELEM_CHANNEL,
				Messages.IntAmqpPaletteFactory_CHANNEL_COMPONENT_DESCRIPTION, new ModelElementCreationFactory(ChannelModelElement.class, diagram,
						namespaceUri), IntegrationImages.CHANNEL_SMALL, IntegrationImages.CHANNEL);
		entries.add(entry);

		entry = new CombinedTemplateCreationEntry(IntAmqpSchemaConstants.ELEM_INBOUND_CHANNEL_ADAPTER,
				Messages.IntAmqpPaletteFactory_INBOUND_CHANNEL_ADAPTER_COMPONENT_DESCRIPTION,
				new ModelElementCreationFactory(InboundChannelAdapterModelElement.class, diagram, namespaceUri),
				IntegrationImages.INBOUND_ADAPTER_SMALL, IntegrationImages.INBOUND_ADAPTER);
		entries.add(entry);

		entry = new CombinedTemplateCreationEntry(IntAmqpSchemaConstants.ELEM_INBOUND_GATEWAY,
				Messages.IntAmqpPaletteFactory_INBOUND_GATEWAY_COMPONENT_DESCRIPTION, new ModelElementCreationFactory(
						InboundGatewayModelElement.class, diagram, namespaceUri),
				IntegrationImages.INBOUND_GATEWAY_SMALL, IntegrationImages.INBOUND_GATEWAY);
		entries.add(entry);

		entry = new CombinedTemplateCreationEntry(IntAmqpSchemaConstants.ELEM_OUTBOUND_CHANNEL_ADAPTER,
				Messages.IntAmqpPaletteFactory_OUTBOUND_CHANNEL_ADAPTER_COMPONENT_DESCRIPTION,
				new ModelElementCreationFactory(OutboundChannelAdapterModelElement.class, diagram, namespaceUri),
				IntegrationImages.OUTBOUND_ADAPTER_SMALL, IntegrationImages.OUTBOUND_ADAPTER);
		entries.add(entry);

		entry = new CombinedTemplateCreationEntry(IntAmqpSchemaConstants.ELEM_OUTBOUND_GATEWAY,
				Messages.IntAmqpPaletteFactory_OUTBOUND_GATEWAY_COMPONENT_DESCRIPTION, new ModelElementCreationFactory(
						OutboundGatewayModelElement.class, diagram, namespaceUri),
				IntegrationImages.OUTBOUND_GATEWAY_SMALL, IntegrationImages.OUTBOUND_GATEWAY);
		entries.add(entry);

		entry = new CombinedTemplateCreationEntry(IntAmqpSchemaConstants.ELEM_PUBLISH_SUBSCRIBE_CHANNEL,
				Messages.IntAmqpPaletteFactory_PUBLISH_SUBSCRIBE_CHANNEL_COMPONENT_DESCRIPTION, new ModelElementCreationFactory(
						PublishSubscribeChannelModelElement.class, diagram, namespaceUri),
				IntegrationImages.PUBSUB_CHANNEL_SMALL, IntegrationImages.PUBSUB_CHANNEL);

		drawer.addAll(entries);
		return drawer;
	}

}
