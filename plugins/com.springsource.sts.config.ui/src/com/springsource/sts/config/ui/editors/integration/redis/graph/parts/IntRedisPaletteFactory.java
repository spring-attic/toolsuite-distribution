/******************************************************************************************
 * Copyright (c) 2011 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.ui.editors.integration.redis.graph.parts;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.gef.palette.CombinedTemplateCreationEntry;
import org.eclipse.gef.palette.PaletteDrawer;
import org.eclipse.gef.palette.PaletteEntry;

import com.springsource.sts.config.core.schemas.IntRedisSchemaConstants;
import com.springsource.sts.config.flow.model.AbstractConfigFlowDiagram;
import com.springsource.sts.config.flow.model.ModelElementCreationFactory;
import com.springsource.sts.config.flow.parts.IPaletteFactory;
import com.springsource.sts.config.ui.editors.integration.graph.IntegrationImages;
import com.springsource.sts.config.ui.editors.integration.redis.graph.model.InboundChannelAdapterModelElement;
import com.springsource.sts.config.ui.editors.integration.redis.graph.model.OutboundChannelAdaperModelElement;
import com.springsource.sts.config.ui.editors.integration.redis.graph.model.PublishSubscribeChannelModelElement;

/**
 * @author Leo Dos Santos
 */
public class IntRedisPaletteFactory implements IPaletteFactory {

	public PaletteDrawer createPaletteDrawer(AbstractConfigFlowDiagram diagram, String namespaceUri) {
		PaletteDrawer drawer = new PaletteDrawer("", IntegrationImages.BADGE_SI_REDIS); //$NON-NLS-1$
		List<PaletteEntry> entries = new ArrayList<PaletteEntry>();

		CombinedTemplateCreationEntry entry = new CombinedTemplateCreationEntry(
				IntRedisSchemaConstants.ELEM_INBOUND_CHANNEL_ADAPTER, Messages.IntRedisPaletteFactory_INBOUND_CHANNEL_ADAPTER_COMPONENT_DESCRIPTION,
				new ModelElementCreationFactory(InboundChannelAdapterModelElement.class, diagram, namespaceUri),
				IntegrationImages.INBOUND_ADAPTER_SMALL, IntegrationImages.INBOUND_ADAPTER);
		entries.add(entry);

		entry = new CombinedTemplateCreationEntry(IntRedisSchemaConstants.ELEM_OUTBOUND_CHANNEL_ADAPTER,
				Messages.IntRedisPaletteFactory_OUTBOUND_CHANNEL_ADAPTER_COMPONENT_DESCRIPTION, new ModelElementCreationFactory(
						OutboundChannelAdaperModelElement.class, diagram, namespaceUri),
				IntegrationImages.OUTBOUND_ADAPTER_SMALL, IntegrationImages.OUTBOUND_ADAPTER);
		entries.add(entry);

		entry = new CombinedTemplateCreationEntry(IntRedisSchemaConstants.ELEM_PUBLISH_SUBSCRIBE_CHANNEL,
				Messages.IntRedisPaletteFactory_PUBLISH_SUBSCRIBE_CHANNEL_COMPONENT_DESCRIPTION, new ModelElementCreationFactory(
						PublishSubscribeChannelModelElement.class, diagram, namespaceUri),
				IntegrationImages.PUBSUB_CHANNEL_SMALL, IntegrationImages.PUBSUB_CHANNEL);
		entries.add(entry);

		drawer.addAll(entries);
		return drawer;
	}

}
