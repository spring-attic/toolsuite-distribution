/******************************************************************************************
 * Copyright (c) 2010 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.ui.editors.integration.jms.graph.parts;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.gef.palette.CombinedTemplateCreationEntry;
import org.eclipse.gef.palette.PaletteDrawer;
import org.eclipse.gef.palette.PaletteEntry;

import com.springsource.sts.config.core.schemas.IntJmsSchemaConstants;
import com.springsource.sts.config.flow.model.AbstractConfigFlowDiagram;
import com.springsource.sts.config.flow.model.ModelElementCreationFactory;
import com.springsource.sts.config.flow.parts.IPaletteFactory;
import com.springsource.sts.config.ui.editors.integration.graph.IntegrationImages;
import com.springsource.sts.config.ui.editors.integration.jms.graph.model.ChannelModelElement;
import com.springsource.sts.config.ui.editors.integration.jms.graph.model.HeaderEnricherModelElement;
import com.springsource.sts.config.ui.editors.integration.jms.graph.model.InboundChannelAdapterModelElement;
import com.springsource.sts.config.ui.editors.integration.jms.graph.model.InboundGatewayModelElement;
import com.springsource.sts.config.ui.editors.integration.jms.graph.model.MessageDrivenChannelAdapterModelElement;
import com.springsource.sts.config.ui.editors.integration.jms.graph.model.OutboundChannelAdapterModelElement;
import com.springsource.sts.config.ui.editors.integration.jms.graph.model.OutboundGatewayModelElement;
import com.springsource.sts.config.ui.editors.integration.jms.graph.model.PublishSubscribeChannelModelElement;

/**
 * @author Leo Dos Santos
 */
public class IntJmsPaletteFactory implements IPaletteFactory {

	public PaletteDrawer createPaletteDrawer(AbstractConfigFlowDiagram diagram, String namespaceUri) {
		PaletteDrawer drawer = new PaletteDrawer("", IntegrationImages.BADGE_SI_JMS); //$NON-NLS-1$
		List<PaletteEntry> entries = new ArrayList<PaletteEntry>();

		CombinedTemplateCreationEntry entry = new CombinedTemplateCreationEntry(IntJmsSchemaConstants.ELEM_CHANNEL,
				Messages.IntJmsPaletteFactory_CHANNEL_COMPONENT_DESCRIPTION, new ModelElementCreationFactory(ChannelModelElement.class, diagram,
						namespaceUri), IntegrationImages.CHANNEL_SMALL, IntegrationImages.CHANNEL);
		entries.add(entry);

		entry = new CombinedTemplateCreationEntry(IntJmsSchemaConstants.ELEM_HEADER_ENRICHER,
				Messages.IntJmsPaletteFactory_HEADER_ENRICHER_COMPONENT_DESCRIPTION, new ModelElementCreationFactory(HeaderEnricherModelElement.class,
						diagram, namespaceUri), IntegrationImages.ENRICHER_SMALL, IntegrationImages.ENRICHER);
		entries.add(entry);

		entry = new CombinedTemplateCreationEntry(IntJmsSchemaConstants.ELEM_INBOUND_CHANNEL_ADAPTER,
				Messages.IntJmsPaletteFactory_INBOUND_CHANNEL_ADAPTER_COMPONENT_DESCRIPTION, new ModelElementCreationFactory(
						InboundChannelAdapterModelElement.class, diagram, namespaceUri),
				IntegrationImages.INBOUND_ADAPTER_SMALL, IntegrationImages.INBOUND_ADAPTER);
		entries.add(entry);

		entry = new CombinedTemplateCreationEntry(IntJmsSchemaConstants.ELEM_INBOUND_GATEWAY,
				Messages.IntJmsPaletteFactory_INBOUND_GATEWAY_ADAPTER_COMPONENT_DESCRIPTION, new ModelElementCreationFactory(InboundGatewayModelElement.class,
						diagram, namespaceUri), IntegrationImages.INBOUND_GATEWAY_SMALL,
				IntegrationImages.INBOUND_GATEWAY);
		entries.add(entry);

		entry = new CombinedTemplateCreationEntry(IntJmsSchemaConstants.ELEM_MESSAGE_DRIVEN_CHANNEL_ADAPTER,
				Messages.IntJmsPaletteFactory_MESSAGE_DRIVEN_CHANNEL_ADAPTER_COMPONENT_DESCRIPTION, new ModelElementCreationFactory(
						MessageDrivenChannelAdapterModelElement.class, diagram, namespaceUri),
				IntegrationImages.INBOUND_ADAPTER_SMALL, IntegrationImages.INBOUND_ADAPTER);
		entries.add(entry);

		entry = new CombinedTemplateCreationEntry(IntJmsSchemaConstants.ELEM_OUTBOUND_CHANNEL_ADAPTER,
				Messages.IntJmsPaletteFactory_OUTBOUND_CHANNEL_ADAPTER_COMPONENT_DESCRIPTION, new ModelElementCreationFactory(
						OutboundChannelAdapterModelElement.class, diagram, namespaceUri),
				IntegrationImages.OUTBOUND_ADAPTER_SMALL, IntegrationImages.OUTBOUND_ADAPTER);
		entries.add(entry);

		entry = new CombinedTemplateCreationEntry(IntJmsSchemaConstants.ELEM_OUTBOUND_GATEWAY,
				Messages.IntJmsPaletteFactory_OUTBOUND_GATEWAY_COMPONENT_DESCRIPTION, new ModelElementCreationFactory(
						OutboundGatewayModelElement.class, diagram, namespaceUri),
				IntegrationImages.OUTBOUND_GATEWAY_SMALL, IntegrationImages.OUTBOUND_GATEWAY);
		entries.add(entry);

		entry = new CombinedTemplateCreationEntry(IntJmsSchemaConstants.ELEM_PUBLISH_SUBSCRIBE_CHANNEL,
				Messages.IntJmsPaletteFactory_PUBLISH_SUBSCRIBE_CHANNEL_COMPONENT_DESCRIPTION, new ModelElementCreationFactory(
						PublishSubscribeChannelModelElement.class, diagram, namespaceUri),
				IntegrationImages.PUBSUB_CHANNEL_SMALL, IntegrationImages.PUBSUB_CHANNEL);
		entries.add(entry);

		drawer.addAll(entries);
		return drawer;
	}

}
