/******************************************************************************************
 * Copyright (c) 2010 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.ui.editors.integration.ip.graph.parts;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.gef.palette.CombinedTemplateCreationEntry;
import org.eclipse.gef.palette.PaletteDrawer;
import org.eclipse.gef.palette.PaletteEntry;

import com.springsource.sts.config.core.schemas.IntIpSchemaConstants;
import com.springsource.sts.config.flow.model.AbstractConfigFlowDiagram;
import com.springsource.sts.config.flow.model.ModelElementCreationFactory;
import com.springsource.sts.config.flow.parts.IPaletteFactory;
import com.springsource.sts.config.ui.editors.integration.graph.IntegrationImages;
import com.springsource.sts.config.ui.editors.integration.ip.graph.model.UdpInboundChannelAdapterModelElement;
import com.springsource.sts.config.ui.editors.integration.ip.graph.model.TcpInboundGatewayModelElement;
import com.springsource.sts.config.ui.editors.integration.ip.graph.model.UdpOutboundChannelAdapterModelElement;
import com.springsource.sts.config.ui.editors.integration.ip.graph.model.TcpOutboundGatewayModelElement;
import com.springsource.sts.config.ui.editors.integration.ip.graph.model.TcpInboundChannelAdapterModelElement;
import com.springsource.sts.config.ui.editors.integration.ip.graph.model.TcpOutboundChannelAdapterModelElement;

/**
 * @author Leo Dos Santos
 */
public class IntIpPaletteFactory implements IPaletteFactory {

	public PaletteDrawer createPaletteDrawer(AbstractConfigFlowDiagram diagram, String namespaceUri) {
		PaletteDrawer drawer = new PaletteDrawer("", IntegrationImages.BADGE_SI_IP); //$NON-NLS-1$
		List<PaletteEntry> entries = new ArrayList<PaletteEntry>();

		CombinedTemplateCreationEntry entry = new CombinedTemplateCreationEntry(
				IntIpSchemaConstants.ELEM_TCP_INBOUND_CHANNEL_ADAPTER,
				Messages.IntIpPaletteFactory_TCP_INBOUND_CHANNEL_ADAPTER_COMPONENT_DESCRIPTION,
				new ModelElementCreationFactory(TcpInboundChannelAdapterModelElement.class, diagram, namespaceUri),
				IntegrationImages.INBOUND_ADAPTER_SMALL, IntegrationImages.INBOUND_ADAPTER);
		entries.add(entry);

		entry = new CombinedTemplateCreationEntry(IntIpSchemaConstants.ELEM_TCP_INBOUND_GATEWAY,
				Messages.IntIpPaletteFactory_TCP_INBOUND_GATEWAY_COMPONENT_DESCRIPTION,
				new ModelElementCreationFactory(TcpInboundGatewayModelElement.class, diagram, namespaceUri),
				IntegrationImages.INBOUND_GATEWAY_SMALL, IntegrationImages.INBOUND_GATEWAY);
		entries.add(entry);

		entry = new CombinedTemplateCreationEntry(IntIpSchemaConstants.ELEM_TCP_OUTBOUND_CHANNEL_ADAPTER,
				Messages.IntIpPaletteFactory_TCP_OUTBOUND_CHANNEL_ADAPTER_COMPONENT_DESCRIPTION,
				new ModelElementCreationFactory(TcpOutboundChannelAdapterModelElement.class, diagram, namespaceUri),
				IntegrationImages.OUTBOUND_ADAPTER_SMALL, IntegrationImages.OUTBOUND_ADAPTER);
		entries.add(entry);

		entry = new CombinedTemplateCreationEntry(IntIpSchemaConstants.ELEM_TCP_OUTBOUND_GATEWAY,
				Messages.IntIpPaletteFactory_TCP_OUTBOUND_GATEWAY_COMPONENT_DESCRIPTION,
				new ModelElementCreationFactory(TcpOutboundGatewayModelElement.class, diagram, namespaceUri),
				IntegrationImages.OUTBOUND_GATEWAY_SMALL, IntegrationImages.OUTBOUND_GATEWAY);
		entries.add(entry);

		entry = new CombinedTemplateCreationEntry(IntIpSchemaConstants.ELEM_UDP_INBOUND_CHANNEL_ADAPTER,
				Messages.IntIpPaletteFactory_UDP_INBOUND_CHANNEL_ADAPTER_COMPONENT_DESCRIPTION,
				new ModelElementCreationFactory(UdpInboundChannelAdapterModelElement.class, diagram, namespaceUri),
				IntegrationImages.INBOUND_ADAPTER_SMALL, IntegrationImages.INBOUND_ADAPTER);
		entries.add(entry);

		entry = new CombinedTemplateCreationEntry(IntIpSchemaConstants.ELEM_UDP_OUTBOUND_CHANNEL_ADAPTER,
				Messages.IntIpPaletteFactory_UDP_OUTBOUND_CHANNEL_ADAPTER_COMPONENT_DESCRIPTION,
				new ModelElementCreationFactory(UdpOutboundChannelAdapterModelElement.class, diagram, namespaceUri),
				IntegrationImages.OUTBOUND_ADAPTER_SMALL, IntegrationImages.OUTBOUND_ADAPTER);
		entries.add(entry);

		drawer.addAll(entries);
		return drawer;
	}

}
