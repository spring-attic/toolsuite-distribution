/******************************************************************************************
 * Copyright (c) 2010 - 2011 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.ui.editors.integration.jdbc.graph.parts;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.gef.palette.CombinedTemplateCreationEntry;
import org.eclipse.gef.palette.PaletteDrawer;
import org.eclipse.gef.palette.PaletteEntry;

import com.springsource.sts.config.core.schemas.IntJdbcSchemaConstants;
import com.springsource.sts.config.flow.model.AbstractConfigFlowDiagram;
import com.springsource.sts.config.flow.model.ModelElementCreationFactory;
import com.springsource.sts.config.flow.parts.IPaletteFactory;
import com.springsource.sts.config.ui.editors.integration.graph.IntegrationImages;
import com.springsource.sts.config.ui.editors.integration.jdbc.graph.model.InboundChannelAdapterModelElement;
import com.springsource.sts.config.ui.editors.integration.jdbc.graph.model.OutboundChannelAdapterModelElement;
import com.springsource.sts.config.ui.editors.integration.jdbc.graph.model.OutboundGatewayModelElement;
import com.springsource.sts.config.ui.editors.integration.jdbc.graph.model.StoredProcInboundChannelAdapterModelElement;
import com.springsource.sts.config.ui.editors.integration.jdbc.graph.model.StoredProcOutboundChannelAdapterModelElement;
import com.springsource.sts.config.ui.editors.integration.jdbc.graph.model.StoredProcOutboundGatewayModelElement;

/**
 * @author Leo Dos Santos
 */
public class IntJdbcPaletteFactory implements IPaletteFactory {

	public PaletteDrawer createPaletteDrawer(AbstractConfigFlowDiagram diagram, String namespaceUri) {
		PaletteDrawer drawer = new PaletteDrawer("", IntegrationImages.BADGE_SI_JDBC); //$NON-NLS-1$
		List<PaletteEntry> entries = new ArrayList<PaletteEntry>();

		CombinedTemplateCreationEntry entry = new CombinedTemplateCreationEntry(
				IntJdbcSchemaConstants.ELEM_INBOUND_CHANNEL_ADAPTER,
				Messages.IntJdbcPaletteFactory_INBOUND_CHANNEL_ADAPTER_COMPONENT_DESCRIPTION,
				new ModelElementCreationFactory(InboundChannelAdapterModelElement.class, diagram, namespaceUri),
				IntegrationImages.INBOUND_ADAPTER_SMALL, IntegrationImages.INBOUND_ADAPTER);
		entries.add(entry);

		entry = new CombinedTemplateCreationEntry(IntJdbcSchemaConstants.ELEM_OUTBOUND_CHANNEL_ADAPTER,
				Messages.IntJdbcPaletteFactory_OUTBOUND_CHANNEL_ADAPTER_COMPONENT_DESCRIPTION,
				new ModelElementCreationFactory(OutboundChannelAdapterModelElement.class, diagram, namespaceUri),
				IntegrationImages.OUTBOUND_ADAPTER_SMALL, IntegrationImages.OUTBOUND_ADAPTER);
		entries.add(entry);

		entry = new CombinedTemplateCreationEntry(IntJdbcSchemaConstants.ELEM_OUTBOUND_GATEWAY,
				Messages.IntJdbcPaletteFactory_OUTBOUND_GATEWAY_COMPONENT_DESCRIPTION, new ModelElementCreationFactory(
						OutboundGatewayModelElement.class, diagram, namespaceUri),
				IntegrationImages.OUTBOUND_GATEWAY_SMALL, IntegrationImages.OUTBOUND_GATEWAY);
		entries.add(entry);

		entry = new CombinedTemplateCreationEntry(IntJdbcSchemaConstants.ELEM_STORED_PROC_INBOUND_CHANNEL_ADAPTER,
				Messages.IntJdbcPaletteFactory_STORED_PROC_INBOUND_CHANNEL_ADAPTER_COMPONENT_DESCRIPTION, new ModelElementCreationFactory(
						StoredProcInboundChannelAdapterModelElement.class, diagram, namespaceUri),
				IntegrationImages.INBOUND_ADAPTER_SMALL, IntegrationImages.INBOUND_ADAPTER);
		entries.add(entry);

		entry = new CombinedTemplateCreationEntry(IntJdbcSchemaConstants.ELEM_STORED_PROC_OUTBOUND_CHANNEL_ADAPTER,
				Messages.IntJdbcPaletteFactory_STORED_PROC_OUTBOUND_CHANNEL_ADAPTER_COMPONENT_DESCRIPTION, new ModelElementCreationFactory(
						StoredProcOutboundChannelAdapterModelElement.class, diagram, namespaceUri),
				IntegrationImages.OUTBOUND_ADAPTER_SMALL, IntegrationImages.OUTBOUND_ADAPTER);
		entries.add(entry);

		entry = new CombinedTemplateCreationEntry(IntJdbcSchemaConstants.ELEM_STORED_PROC_OUTBOUND_GATEWAY,
				Messages.IntJdbcPaletteFactory_STORED_PROC_OUTBOUND_GATEWAY_COMPONENT_DESCRIPTION, new ModelElementCreationFactory(
						StoredProcOutboundGatewayModelElement.class, diagram, namespaceUri),
				IntegrationImages.OUTBOUND_GATEWAY_SMALL, IntegrationImages.OUTBOUND_GATEWAY);
		entries.add(entry);

		drawer.addAll(entries);
		return drawer;
	}

}
