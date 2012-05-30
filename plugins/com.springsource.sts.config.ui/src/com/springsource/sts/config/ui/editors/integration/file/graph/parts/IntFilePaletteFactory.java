/******************************************************************************************
 * Copyright (c) 2010 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.ui.editors.integration.file.graph.parts;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.gef.palette.CombinedTemplateCreationEntry;
import org.eclipse.gef.palette.PaletteDrawer;
import org.eclipse.gef.palette.PaletteEntry;

import com.springsource.sts.config.core.schemas.IntFileSchemaConstants;
import com.springsource.sts.config.flow.model.AbstractConfigFlowDiagram;
import com.springsource.sts.config.flow.model.ModelElementCreationFactory;
import com.springsource.sts.config.flow.parts.IPaletteFactory;
import com.springsource.sts.config.ui.editors.integration.file.graph.model.FileToBytesTransformerModelElement;
import com.springsource.sts.config.ui.editors.integration.file.graph.model.FileToStringTransformerModelElement;
import com.springsource.sts.config.ui.editors.integration.file.graph.model.InboundChannelAdapterModelElement;
import com.springsource.sts.config.ui.editors.integration.file.graph.model.OutboundChannelAdapterModelElement;
import com.springsource.sts.config.ui.editors.integration.file.graph.model.OutboundGatewayModelElement;
import com.springsource.sts.config.ui.editors.integration.graph.IntegrationImages;

/**
 * @author Leo Dos Santos
 */
public class IntFilePaletteFactory implements IPaletteFactory {

	public PaletteDrawer createPaletteDrawer(AbstractConfigFlowDiagram diagram, String namespaceUri) {
		PaletteDrawer drawer = new PaletteDrawer("", IntegrationImages.BADGE_SI_FILE); //$NON-NLS-1$
		List<PaletteEntry> entries = new ArrayList<PaletteEntry>();

		CombinedTemplateCreationEntry entry = new CombinedTemplateCreationEntry(
				IntFileSchemaConstants.ELEM_FILE_TO_BYTES_TRANSFORMER, Messages.IntFilePaletteFactory_FILE_TO_BYTES_TRANSFORMER_COMPONENT_DESCRIPTION,
				new ModelElementCreationFactory(FileToBytesTransformerModelElement.class, diagram, namespaceUri),
				IntegrationImages.TRANSFORMER_SMALL, IntegrationImages.TRANSFORMER);
		entries.add(entry);

		entry = new CombinedTemplateCreationEntry(IntFileSchemaConstants.ELEM_FILE_TO_STRING_TRANSFORMER,
				Messages.IntFilePaletteFactory_FILE_TO_STRING_TRANSFORMER_COMPONENT_DESCRIPTION, new ModelElementCreationFactory(
						FileToStringTransformerModelElement.class, diagram, namespaceUri),
				IntegrationImages.TRANSFORMER_SMALL, IntegrationImages.TRANSFORMER);
		entries.add(entry);

		entry = new CombinedTemplateCreationEntry(IntFileSchemaConstants.ELEM_INBOUND_CHANNEL_ADAPTER,
				Messages.IntFilePaletteFactory_INBOUND_CHANNEL_ADAPTER_COMPONENT_DESCRIPTION, new ModelElementCreationFactory(
						InboundChannelAdapterModelElement.class, diagram, namespaceUri),
				IntegrationImages.INBOUND_ADAPTER_SMALL, IntegrationImages.INBOUND_ADAPTER);
		entries.add(entry);

		entry = new CombinedTemplateCreationEntry(IntFileSchemaConstants.ELEM_OUTBOUND_CHANNEL_ADAPTER,
				Messages.IntFilePaletteFactory_OUTBOUND_CHANNEL_ADAPTER_COMPONENT_DESCRIPTION, new ModelElementCreationFactory(
						OutboundChannelAdapterModelElement.class, diagram, namespaceUri),
				IntegrationImages.OUTBOUND_ADAPTER_SMALL, IntegrationImages.OUTBOUND_ADAPTER);
		entries.add(entry);

		entry = new CombinedTemplateCreationEntry(IntFileSchemaConstants.ELEM_OUTBOUND_GATEWAY,
				Messages.IntFilePaletteFactory_OUTBOUND_GATEWAY_COMPONENT_DESCRIPTION, new ModelElementCreationFactory(
						OutboundGatewayModelElement.class, diagram, namespaceUri),
				IntegrationImages.OUTBOUND_GATEWAY_SMALL, IntegrationImages.OUTBOUND_GATEWAY);
		entries.add(entry);

		drawer.addAll(entries);
		return drawer;
	}

}
