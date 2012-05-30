/******************************************************************************************
 * Copyright (c) 2010 - 2011 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.ui.editors.webflow.graph;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.gef.palette.CombinedTemplateCreationEntry;
import org.eclipse.gef.palette.ConnectionCreationToolEntry;
import org.eclipse.gef.palette.PaletteDrawer;
import org.eclipse.gef.palette.PaletteEntry;
import org.eclipse.gef.palette.ToolEntry;

import com.springsource.sts.config.core.schemas.WebFlowSchemaConstants;
import com.springsource.sts.config.flow.AbstractConfigGraphicalEditor;
import com.springsource.sts.config.flow.FlowCommonImages;
import com.springsource.sts.config.flow.model.ModelElementCreationFactory;
import com.springsource.sts.config.flow.model.TransitionCreationFactory;
import com.springsource.sts.config.flow.parts.AbstractConfigPaletteFactory;
import com.springsource.sts.config.ui.editors.webflow.graph.model.ActionStateModelElement;
import com.springsource.sts.config.ui.editors.webflow.graph.model.DecisionStateModelElement;
import com.springsource.sts.config.ui.editors.webflow.graph.model.EndStateModelElement;
import com.springsource.sts.config.ui.editors.webflow.graph.model.SubflowStateModelElement;
import com.springsource.sts.config.ui.editors.webflow.graph.model.ViewStateModelElement;

/**
 * @author Leo Dos Santos
 */
public class WebFlowEditorPaletteFactory extends AbstractConfigPaletteFactory {

	public WebFlowEditorPaletteFactory(AbstractConfigGraphicalEditor editor) {
		super(editor);
	}

	@Override
	protected List<PaletteDrawer> createComponentDrawers() {
		List<PaletteDrawer> categories = new ArrayList<PaletteDrawer>();
		PaletteDrawer drawer = new PaletteDrawer(Messages.WebFlowEditorPaletteFactory_STATE_PALETTE_TITLE);
		List<PaletteEntry> entries = new ArrayList<PaletteEntry>();

		CombinedTemplateCreationEntry entry = new CombinedTemplateCreationEntry(
				WebFlowSchemaConstants.ELEM_ACTION_STATE,
				Messages.WebFlowEditorPaletteFactory_ACTION_STATE_COMPONENT_DESCRIPTION,
				new ModelElementCreationFactory(ActionStateModelElement.class, getDiagram()),
				WebFlowImages.ACTION_SMALL, WebFlowImages.ACTION);
		entries.add(entry);

		entry = new CombinedTemplateCreationEntry(WebFlowSchemaConstants.ELEM_DECISION_STATE,
				Messages.WebFlowEditorPaletteFactory_DECISION_STATE_COMPONENT_DESCRIPTION,
				new ModelElementCreationFactory(DecisionStateModelElement.class, getDiagram()),
				WebFlowImages.DECISION_SMALL, WebFlowImages.DECISION);
		entries.add(entry);

		entry = new CombinedTemplateCreationEntry(WebFlowSchemaConstants.ELEM_END_STATE,
				Messages.WebFlowEditorPaletteFactory_END_STATE_COMPONENT_DESCRIPTION, new ModelElementCreationFactory(
						EndStateModelElement.class, getDiagram()), WebFlowImages.END_SMALL, WebFlowImages.END);
		entries.add(entry);

		entry = new CombinedTemplateCreationEntry(WebFlowSchemaConstants.ELEM_SUBFLOW_STATE,
				Messages.WebFlowEditorPaletteFactory_SUBFLOW_STATE_COMPONENT_DESCRIPTION,
				new ModelElementCreationFactory(SubflowStateModelElement.class, getDiagram()),
				WebFlowImages.SUBFLOW_SMALL, WebFlowImages.SUBFLOW);
		entries.add(entry);

		entry = new CombinedTemplateCreationEntry(WebFlowSchemaConstants.ELEM_VIEW_STATE,
				Messages.WebFlowEditorPaletteFactory_VIEW_STATE_COMPONENT_DESCRIPTION, new ModelElementCreationFactory(
						ViewStateModelElement.class, getDiagram()), WebFlowImages.VIEW_SMALL, WebFlowImages.VIEW);
		entries.add(entry);

		drawer.addAll(entries);
		categories.add(drawer);
		return categories;
	}

	@Override
	protected List<PaletteEntry> createConnectionTools() {
		List<PaletteEntry> entries = new ArrayList<PaletteEntry>();
		ToolEntry tool = new ConnectionCreationToolEntry(Messages.WebFlowEditorPaletteFactory_TO_COMPONENT_TITLE,
				Messages.WebFlowEditorPaletteFactory_TO_COMPONENT_DESCRIPTION, new TransitionCreationFactory(),
				FlowCommonImages.CONNECTION_SOLID, FlowCommonImages.CONNECTION_SOLID);
		entries.add(tool);
		return entries;
	}

}
