/******************************************************************************************
 * Copyright (c) 2011 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.ui.editors.integration.gemfire.graph.parts;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.gef.palette.CombinedTemplateCreationEntry;
import org.eclipse.gef.palette.PaletteDrawer;
import org.eclipse.gef.palette.PaletteEntry;

import com.springsource.sts.config.core.schemas.IntGemfireSchemaConstants;
import com.springsource.sts.config.flow.model.AbstractConfigFlowDiagram;
import com.springsource.sts.config.flow.model.ModelElementCreationFactory;
import com.springsource.sts.config.flow.parts.IPaletteFactory;
import com.springsource.sts.config.ui.editors.integration.gemfire.graph.model.CqInboundChannelAdapterModelElement;
import com.springsource.sts.config.ui.editors.integration.gemfire.graph.model.InboundChannelAdapterModelElement;
import com.springsource.sts.config.ui.editors.integration.gemfire.graph.model.OutboundChannelAdapterModelElement;
import com.springsource.sts.config.ui.editors.integration.graph.IntegrationImages;

/**
 * @author Leo Dos Santos
 */
public class IntGemfirePaletteFactory implements IPaletteFactory {

	public PaletteDrawer createPaletteDrawer(AbstractConfigFlowDiagram diagram, String namespaceUri) {
		PaletteDrawer drawer = new PaletteDrawer("", IntegrationImages.BADGE_SI_GEMFIRE); //$NON-NLS-1$
		List<PaletteEntry> entries = new ArrayList<PaletteEntry>();

		CombinedTemplateCreationEntry entry = new CombinedTemplateCreationEntry(
				IntGemfireSchemaConstants.ELEM_CQ_INBOUND_CHANNEL_ADAPTER,
				Messages.IntGemfirePaletteFactory_CQ_INBOUND_CHANNEL_ADAPTER_COMPONENT_DESCRIPTION, new ModelElementCreationFactory(
						CqInboundChannelAdapterModelElement.class, diagram, namespaceUri),
				IntegrationImages.INBOUND_ADAPTER_SMALL, IntegrationImages.INBOUND_ADAPTER);
		entries.add(entry);

		entry = new CombinedTemplateCreationEntry(IntGemfireSchemaConstants.ELEM_INBOUND_CHANNEL_ADAPTER,
				Messages.IntGemfirePaletteFactory_INBOUND_CHANNEL_ADAPTER_COMPONENT_DESCRIPTION, new ModelElementCreationFactory(
						InboundChannelAdapterModelElement.class, diagram, namespaceUri),
				IntegrationImages.INBOUND_ADAPTER_SMALL, IntegrationImages.INBOUND_ADAPTER);
		entries.add(entry);

		entry = new CombinedTemplateCreationEntry(IntGemfireSchemaConstants.ELEM_OUTBOUND_CHANNEL_ADAPTER,
				Messages.IntGemfirePaletteFactory_OUTBOUND_CHANNEL_ADAPTER_COMPONENT_DESCRIPTION, new ModelElementCreationFactory(
						OutboundChannelAdapterModelElement.class, diagram, namespaceUri),
				IntegrationImages.OUTBOUND_ADAPTER_SMALL, IntegrationImages.OUTBOUND_ADAPTER);
		entries.add(entry);

		drawer.addAll(entries);
		return drawer;
	}

}
