/******************************************************************************************
 * Copyright (c) 2010 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.ui.editors.integration.jmx.graph.parts;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.gef.palette.CombinedTemplateCreationEntry;
import org.eclipse.gef.palette.PaletteDrawer;
import org.eclipse.gef.palette.PaletteEntry;

import com.springsource.sts.config.core.schemas.IntJmxSchemaConstants;
import com.springsource.sts.config.flow.model.AbstractConfigFlowDiagram;
import com.springsource.sts.config.flow.model.ModelElementCreationFactory;
import com.springsource.sts.config.flow.parts.IPaletteFactory;
import com.springsource.sts.config.ui.editors.integration.graph.IntegrationImages;
import com.springsource.sts.config.ui.editors.integration.jmx.graph.model.AttributePollingChannelAdapterModelElement;
import com.springsource.sts.config.ui.editors.integration.jmx.graph.model.NotificationListeningChannelAdapterModelElement;
import com.springsource.sts.config.ui.editors.integration.jmx.graph.model.NotificationPublishingChannelAdapterModelElement;
import com.springsource.sts.config.ui.editors.integration.jmx.graph.model.OperationInvokingChannelAdapterModelElement;
import com.springsource.sts.config.ui.editors.integration.jmx.graph.model.OperationInvokingOutboundGatewayModelElement;

/**
 * @author Leo Dos Santos
 */
public class IntJmxPaletteFactory implements IPaletteFactory {

	public PaletteDrawer createPaletteDrawer(AbstractConfigFlowDiagram diagram, String namespaceUri) {
		PaletteDrawer drawer = new PaletteDrawer("", IntegrationImages.BADGE_SI_JMX); //$NON-NLS-1$
		List<PaletteEntry> entries = new ArrayList<PaletteEntry>();

		CombinedTemplateCreationEntry entry = new CombinedTemplateCreationEntry(
				IntJmxSchemaConstants.ELEM_ATTRIBUTE_POLLING_CHANNEL_ADAPTER,
				Messages.IntJmxPaletteFactory_ATTRIBUTE_POLLING_CHANNEL_ADAPTER_COMPONENT_DESCRIPTION,
				new ModelElementCreationFactory(AttributePollingChannelAdapterModelElement.class, diagram, namespaceUri),
				IntegrationImages.INBOUND_ADAPTER_SMALL, IntegrationImages.INBOUND_ADAPTER);
		entries.add(entry);

		entry = new CombinedTemplateCreationEntry(IntJmxSchemaConstants.ELEM_NOTIFICATION_LISTENING_CHANNEL_ADAPTER,
				Messages.IntJmxPaletteFactory_NOTIFICATION_LISTENING_CHANNEL_ADAPTER_COMPONENT_DESCRIPTION,
				new ModelElementCreationFactory(NotificationListeningChannelAdapterModelElement.class, diagram,
						namespaceUri), IntegrationImages.INBOUND_ADAPTER_SMALL, IntegrationImages.INBOUND_ADAPTER);
		entries.add(entry);

		entry = new CombinedTemplateCreationEntry(IntJmxSchemaConstants.ELEM_NOTIFICATION_PUBLISHING_CHANNEL_ADAPTER,
				Messages.IntJmxPaletteFactory_NOTIFICATION_PUBLISHING_CHANNEL_ADAPTER_COMPONENT_DESCRIPTION,
				new ModelElementCreationFactory(NotificationPublishingChannelAdapterModelElement.class, diagram,
						namespaceUri), IntegrationImages.OUTBOUND_ADAPTER_SMALL, IntegrationImages.OUTBOUND_ADAPTER);
		entries.add(entry);

		entry = new CombinedTemplateCreationEntry(IntJmxSchemaConstants.ELEM_OPERATION_INVOKING_CHANNEL_ADAPTER,
				Messages.IntJmxPaletteFactory_OPERATION_INVOKING_CHANNEL_ADAPTER_COMPONENT_DESCRIPTION,
				new ModelElementCreationFactory(OperationInvokingChannelAdapterModelElement.class, diagram,
						namespaceUri), IntegrationImages.OUTBOUND_ADAPTER_SMALL, IntegrationImages.OUTBOUND_ADAPTER);
		entries.add(entry);

		entry = new CombinedTemplateCreationEntry(IntJmxSchemaConstants.ELEM_OPERATION_INVOKING_OUTBOUND_GATEWAY,
				Messages.IntJmxPaletteFactory_OPERATION_INVOKING_OUTBOUND_GATEWAY_COMPONENT_DESCRIPTION, new ModelElementCreationFactory(
						OperationInvokingOutboundGatewayModelElement.class, diagram, namespaceUri),
				IntegrationImages.OUTBOUND_GATEWAY_SMALL, IntegrationImages.OUTBOUND_GATEWAY);
		entries.add(entry);

		drawer.addAll(entries);
		return drawer;
	}

}
