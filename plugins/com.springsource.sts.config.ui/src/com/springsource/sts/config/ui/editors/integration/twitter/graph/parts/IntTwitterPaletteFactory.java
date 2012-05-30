/******************************************************************************************
 * Copyright (c) 2010 - 2011 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.ui.editors.integration.twitter.graph.parts;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.gef.palette.CombinedTemplateCreationEntry;
import org.eclipse.gef.palette.PaletteDrawer;
import org.eclipse.gef.palette.PaletteEntry;

import com.springsource.sts.config.core.schemas.IntTwitterSchemaConstants;
import com.springsource.sts.config.flow.model.AbstractConfigFlowDiagram;
import com.springsource.sts.config.flow.model.ModelElementCreationFactory;
import com.springsource.sts.config.flow.parts.IPaletteFactory;
import com.springsource.sts.config.ui.editors.integration.graph.IntegrationImages;
import com.springsource.sts.config.ui.editors.integration.twitter.graph.model.DmInboundChannelAdapterModelElement;
import com.springsource.sts.config.ui.editors.integration.twitter.graph.model.DmOutboundChannelAdapterModelElement;
import com.springsource.sts.config.ui.editors.integration.twitter.graph.model.InboundChannelAdapterModelElement;
import com.springsource.sts.config.ui.editors.integration.twitter.graph.model.MentionsInboundChannelAdapterModelElement;
import com.springsource.sts.config.ui.editors.integration.twitter.graph.model.OutboundChannelAdapterModelElement;
import com.springsource.sts.config.ui.editors.integration.twitter.graph.model.SearchInboundChannelAdapterModelElement;

/**
 * @author Leo Dos Santos
 */
public class IntTwitterPaletteFactory implements IPaletteFactory {

	public PaletteDrawer createPaletteDrawer(AbstractConfigFlowDiagram diagram, String namespaceUri) {
		PaletteDrawer drawer = new PaletteDrawer("", IntegrationImages.BADGE_SI_TWITTER); //$NON-NLS-1$
		List<PaletteEntry> entries = new ArrayList<PaletteEntry>();

		CombinedTemplateCreationEntry entry = new CombinedTemplateCreationEntry(
				IntTwitterSchemaConstants.ELEM_DM_INBOUND_CHANNEL_ADAPTER,
				Messages.IntTwitterPaletteFactory_DM_INBOUND_CHANNEL_ADAPTER_COMPONENT_DESCRIPTION,
				new ModelElementCreationFactory(DmInboundChannelAdapterModelElement.class, diagram, namespaceUri),
				IntegrationImages.INBOUND_ADAPTER_SMALL, IntegrationImages.INBOUND_ADAPTER);
		entries.add(entry);

		entry = new CombinedTemplateCreationEntry(IntTwitterSchemaConstants.ELEM_DM_OUTBOUND_CHANNEL_ADAPTER,
				Messages.IntTwitterPaletteFactory_DM_OUTBOUND_CHANNEL_ADAPTER_COMPONENT_DESCRIPTION,
				new ModelElementCreationFactory(DmOutboundChannelAdapterModelElement.class, diagram, namespaceUri),
				IntegrationImages.OUTBOUND_ADAPTER_SMALL, IntegrationImages.OUTBOUND_ADAPTER);
		entries.add(entry);

		entry = new CombinedTemplateCreationEntry(IntTwitterSchemaConstants.ELEM_INBOUND_CHANNEL_ADAPTER,
				Messages.IntTwitterPaletteFactory_INBOUND_CHANNEL_ADAPTER_COMPONENT_DESCRIPTION,
				new ModelElementCreationFactory(InboundChannelAdapterModelElement.class, diagram, namespaceUri),
				IntegrationImages.INBOUND_ADAPTER_SMALL, IntegrationImages.INBOUND_ADAPTER);
		entries.add(entry);

		entry = new CombinedTemplateCreationEntry(
				IntTwitterSchemaConstants.ELEM_MENTIONS_INBOUND_CHANNEL_ADAPTER,
				Messages.IntTwitterPaletteFactory_MENTIONS_INBOUND_CHANNEL_ADAPTER_COMPONENT_DESCRIPTION,
				new ModelElementCreationFactory(MentionsInboundChannelAdapterModelElement.class, diagram, namespaceUri),
				IntegrationImages.INBOUND_ADAPTER_SMALL, IntegrationImages.INBOUND_ADAPTER);
		entries.add(entry);

		entry = new CombinedTemplateCreationEntry(IntTwitterSchemaConstants.ELEM_OUTBOUND_CHANNEL_ADAPTER,
				Messages.IntTwitterPaletteFactory_OUTBOUND_CHANNEL_ADAPTER_COMPONENT_DESCRIPTION,
				new ModelElementCreationFactory(OutboundChannelAdapterModelElement.class, diagram, namespaceUri),
				IntegrationImages.OUTBOUND_ADAPTER_SMALL, IntegrationImages.OUTBOUND_ADAPTER);
		entries.add(entry);

		entry = new CombinedTemplateCreationEntry(IntTwitterSchemaConstants.ELEM_SEARCH_INBOUND_CHANNEL_ADAPTER,
				Messages.IntTwitterPaletteFactory_SEARCH_INBOUND_CHANNEL_ADAPTER_COMPONENT_DESCRIPTION,
				new ModelElementCreationFactory(SearchInboundChannelAdapterModelElement.class, diagram, namespaceUri),
				IntegrationImages.INBOUND_ADAPTER_SMALL, IntegrationImages.INBOUND_ADAPTER);
		entries.add(entry);

		drawer.addAll(entries);
		return drawer;
	}

}
