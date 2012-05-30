/******************************************************************************************
 * Copyright (c) 2010 - 2011 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.ui.editors.integration.xml.graph.parts;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.gef.palette.CombinedTemplateCreationEntry;
import org.eclipse.gef.palette.PaletteDrawer;
import org.eclipse.gef.palette.PaletteEntry;

import com.springsource.sts.config.core.schemas.IntXmlSchemaConstants;
import com.springsource.sts.config.flow.model.AbstractConfigFlowDiagram;
import com.springsource.sts.config.flow.model.ModelElementCreationFactory;
import com.springsource.sts.config.flow.parts.IPaletteFactory;
import com.springsource.sts.config.ui.editors.integration.graph.IntegrationImages;
import com.springsource.sts.config.ui.editors.integration.xml.graph.model.MarshallingTransformerModelElement;
import com.springsource.sts.config.ui.editors.integration.xml.graph.model.UnmarshallingTransformerModelElement;
import com.springsource.sts.config.ui.editors.integration.xml.graph.model.ValidatingFilterModelElement;
import com.springsource.sts.config.ui.editors.integration.xml.graph.model.XpathFilterModelElement;
import com.springsource.sts.config.ui.editors.integration.xml.graph.model.XpathHeaderEnricherModelElement;
import com.springsource.sts.config.ui.editors.integration.xml.graph.model.XpathRouterModelElement;
import com.springsource.sts.config.ui.editors.integration.xml.graph.model.XpathSplitterModelElement;
import com.springsource.sts.config.ui.editors.integration.xml.graph.model.XpathTransformerModelElement;
import com.springsource.sts.config.ui.editors.integration.xml.graph.model.XslTransformerModelElement;

/**
 * @author Leo Dos Santos
 */
public class IntXmlPaletteFactory implements IPaletteFactory {

	public PaletteDrawer createPaletteDrawer(AbstractConfigFlowDiagram diagram, String namespaceUri) {
		PaletteDrawer drawer = new PaletteDrawer("", IntegrationImages.BADGE_SI_XML); //$NON-NLS-1$
		List<PaletteEntry> entries = new ArrayList<PaletteEntry>();

		CombinedTemplateCreationEntry entry = new CombinedTemplateCreationEntry(
				IntXmlSchemaConstants.ELEM_MARSHALLING_TRANSFORMER,
				Messages.IntXmlPaletteFactory_MARSHALLING_TRANSFORMER_COMPONENT_DESCRIPTION,
				new ModelElementCreationFactory(MarshallingTransformerModelElement.class, diagram, namespaceUri),
				IntegrationImages.TRANSFORMER_SMALL, IntegrationImages.TRANSFORMER);
		entries.add(entry);

		entry = new CombinedTemplateCreationEntry(IntXmlSchemaConstants.ELEM_UNMARSHALLING_TRANSFORMER,
				Messages.IntXmlPaletteFactory_UNMARSHALLING_TRANSFORMER_COMPONENT_DESCRIPTION,
				new ModelElementCreationFactory(UnmarshallingTransformerModelElement.class, diagram, namespaceUri),
				IntegrationImages.TRANSFORMER_SMALL, IntegrationImages.TRANSFORMER);
		entries.add(entry);

		entry = new CombinedTemplateCreationEntry(IntXmlSchemaConstants.ELEM_VALIDATING_FILTER,
				Messages.IntXmlPaletteFactory_VALIDATING_FILTER_COMPONENT_DESCRIPTION, new ModelElementCreationFactory(
						ValidatingFilterModelElement.class, diagram, namespaceUri), IntegrationImages.FILTER_SMALL,
				IntegrationImages.FILTER);
		entries.add(entry);

		entry = new CombinedTemplateCreationEntry(IntXmlSchemaConstants.ELEM_XPATH_FILTER,
				Messages.IntXmlPaletteFactory_XPATH_FILTER_COMPONENT_DESCRIPTION, new ModelElementCreationFactory(XpathFilterModelElement.class,
						diagram, namespaceUri), IntegrationImages.FILTER_SMALL, IntegrationImages.FILTER);
		entries.add(entry);

		entry = new CombinedTemplateCreationEntry(IntXmlSchemaConstants.ELEM_XPATH_HEADER_ENRICHER,
				Messages.IntXmlPaletteFactory_XPATH_HEADER_ENRICHER_COMPONENT_DESCRIPTION,
				new ModelElementCreationFactory(XpathHeaderEnricherModelElement.class, diagram, namespaceUri),
				IntegrationImages.ENRICHER_SMALL, IntegrationImages.ENRICHER);
		entries.add(entry);

		entry = new CombinedTemplateCreationEntry(IntXmlSchemaConstants.ELEM_XPATH_ROUTER,
				Messages.IntXmlPaletteFactory_XPATH_ROUTER_COMPONENT_DESCRIPTION, new ModelElementCreationFactory(
						XpathRouterModelElement.class, diagram, namespaceUri), IntegrationImages.ROUTER_SMALL,
				IntegrationImages.ROUTER);
		entries.add(entry);

		entry = new CombinedTemplateCreationEntry(IntXmlSchemaConstants.ELEM_XPATH_SPLITTER,
				Messages.IntXmlPaletteFactory_XPATH_SPLITTER_COMPONENT_DESCRIPTION, new ModelElementCreationFactory(
						XpathSplitterModelElement.class, diagram, namespaceUri), IntegrationImages.SPLITTER_SMALL,
				IntegrationImages.SPLITTER);
		entries.add(entry);

		entry = new CombinedTemplateCreationEntry(IntXmlSchemaConstants.ELEM_XPATH_TRANSFORMER,
				Messages.IntXmlPaletteFactory_XPATH_TRANSFORMER_COMPONENT_DESCRIPTION, new ModelElementCreationFactory(
						XpathTransformerModelElement.class, diagram, namespaceUri),
				IntegrationImages.TRANSFORMER_SMALL, IntegrationImages.TRANSFORMER);
		entries.add(entry);

		entry = new CombinedTemplateCreationEntry(IntXmlSchemaConstants.ELEM_XSLT_TRANSFORMER,
				Messages.IntXmlPaletteFactory_XSLT_TRANSFORMER_COMPONENT_DESCRIPTION, new ModelElementCreationFactory(
						XslTransformerModelElement.class, diagram, namespaceUri), IntegrationImages.TRANSFORMER_SMALL,
				IntegrationImages.TRANSFORMER);
		entries.add(entry);

		drawer.addAll(entries);
		return drawer;
	}
}
