/******************************************************************************************
 * Copyright (c) 2010 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.ui.editors.integration.ws.graph.parts;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.gef.palette.CombinedTemplateCreationEntry;
import org.eclipse.gef.palette.PaletteDrawer;
import org.eclipse.gef.palette.PaletteEntry;

import com.springsource.sts.config.core.schemas.IntWsSchemaConstants;
import com.springsource.sts.config.flow.model.AbstractConfigFlowDiagram;
import com.springsource.sts.config.flow.model.ModelElementCreationFactory;
import com.springsource.sts.config.flow.parts.IPaletteFactory;
import com.springsource.sts.config.ui.editors.integration.graph.IntegrationImages;
import com.springsource.sts.config.ui.editors.integration.ws.graph.model.HeaderEnricherModelElement;
import com.springsource.sts.config.ui.editors.integration.ws.graph.model.InboundGatewayModelElement;
import com.springsource.sts.config.ui.editors.integration.ws.graph.model.OutboundGatewayModelElement;

/**
 * @author Leo Dos Santos
 */
public class IntWsPaletteFactory implements IPaletteFactory {

	public PaletteDrawer createPaletteDrawer(AbstractConfigFlowDiagram diagram, String namespaceUri) {
		PaletteDrawer drawer = new PaletteDrawer("", IntegrationImages.BADGE_SI_WS); //$NON-NLS-1$
		List<PaletteEntry> entries = new ArrayList<PaletteEntry>();

		CombinedTemplateCreationEntry entry = new CombinedTemplateCreationEntry(
				IntWsSchemaConstants.ELEM_HEADER_ENRICHER, Messages.IntWsPaletteFactory_HEADER_ENRICHER_COMPONENT_DESCRIPTION,
				new ModelElementCreationFactory(HeaderEnricherModelElement.class, diagram, namespaceUri),
				IntegrationImages.ENRICHER_SMALL, IntegrationImages.ENRICHER);
		entries.add(entry);

		entry = new CombinedTemplateCreationEntry(IntWsSchemaConstants.ELEM_INBOUND_GATEWAY,
				Messages.IntWsPaletteFactory_INBOUND_GATEWAY_COMPONENT_DESCRIPTION, new ModelElementCreationFactory(InboundGatewayModelElement.class,
						diagram, namespaceUri), IntegrationImages.INBOUND_GATEWAY_SMALL,
				IntegrationImages.INBOUND_GATEWAY);
		entries.add(entry);

		entry = new CombinedTemplateCreationEntry(IntWsSchemaConstants.ELEM_OUTBOUND_GATEWAY,
				Messages.IntWsPaletteFactory_OUTBOUND_GATEWAY_COMPONENT_DESCRIPTION, new ModelElementCreationFactory(
						OutboundGatewayModelElement.class, diagram, namespaceUri),
				IntegrationImages.OUTBOUND_GATEWAY_SMALL, IntegrationImages.OUTBOUND_GATEWAY);
		entries.add(entry);

		drawer.addAll(entries);
		return drawer;
	}

}
