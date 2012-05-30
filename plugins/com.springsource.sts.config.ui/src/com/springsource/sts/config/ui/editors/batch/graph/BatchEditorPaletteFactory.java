/******************************************************************************************
 * Copyright (c) 2009 - 2010 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.ui.editors.batch.graph;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.gef.palette.CombinedTemplateCreationEntry;
import org.eclipse.gef.palette.ConnectionCreationToolEntry;
import org.eclipse.gef.palette.PaletteDrawer;
import org.eclipse.gef.palette.PaletteEntry;
import org.eclipse.gef.palette.ToolEntry;

import com.springsource.sts.config.core.schemas.BatchSchemaConstants;
import com.springsource.sts.config.flow.AbstractConfigGraphicalEditor;
import com.springsource.sts.config.flow.FlowCommonImages;
import com.springsource.sts.config.flow.model.ModelElementCreationFactory;
import com.springsource.sts.config.flow.model.TransitionCreationFactory;
import com.springsource.sts.config.flow.parts.AbstractConfigPaletteFactory;
import com.springsource.sts.config.ui.editors.batch.graph.model.DecisionModelElement;
import com.springsource.sts.config.ui.editors.batch.graph.model.EndModelElement;
import com.springsource.sts.config.ui.editors.batch.graph.model.FailModelElement;
import com.springsource.sts.config.ui.editors.batch.graph.model.FlowModelElement;
import com.springsource.sts.config.ui.editors.batch.graph.model.JobModelElement;
import com.springsource.sts.config.ui.editors.batch.graph.model.NextModelElement;
import com.springsource.sts.config.ui.editors.batch.graph.model.SplitContainerElement;
import com.springsource.sts.config.ui.editors.batch.graph.model.StepModelElement;
import com.springsource.sts.config.ui.editors.batch.graph.model.StopModelElement;

/**
 * @author Leo Dos Santos
 * @author Christian Dupuis
 */
public class BatchEditorPaletteFactory extends AbstractConfigPaletteFactory {

	public BatchEditorPaletteFactory(AbstractConfigGraphicalEditor editor) {
		super(editor);
	}

	@Override
	protected List<PaletteDrawer> createComponentDrawers() {
		List<PaletteDrawer> categories = new ArrayList<PaletteDrawer>();
		PaletteDrawer drawer = new PaletteDrawer(Messages
				.getString("BatchEditorPaletteFactory.COMPONENTS_PALETTE_TITLE")); //$NON-NLS-1$
		List<PaletteEntry> entries = new ArrayList<PaletteEntry>();

		CombinedTemplateCreationEntry entry = new CombinedTemplateCreationEntry(BatchSchemaConstants.ELEM_JOB, Messages
				.getString("BatchEditorPaletteFactory.JOB_COMPONENT_DESCRIPTION"), new ModelElementCreationFactory( //$NON-NLS-1$
				JobModelElement.class, getDiagram()), FlowCommonImages.SEQUENCE_BEGIN_VERTICAL_SMALL,
				FlowCommonImages.SEQUENCE_BEGIN_VERTICAL);
		entries.add(entry);

		entry = new CombinedTemplateCreationEntry(BatchSchemaConstants.ELEM_FLOW,
				Messages.getString("BatchEditorPaletteFactory.FLOW_COMPONENT_DESCRIPTION"), //$NON-NLS-1$
				new ModelElementCreationFactory(FlowModelElement.class, getDiagram()),
				FlowCommonImages.SEQUENCE_BEGIN_VERTICAL_SMALL, FlowCommonImages.SEQUENCE_BEGIN_VERTICAL);
		entries.add(entry);

		entry = new CombinedTemplateCreationEntry(BatchSchemaConstants.ELEM_STEP,
				Messages.getString("BatchEditorPaletteFactory.STEP_COMPONENT_DESCRIPTION"), //$NON-NLS-1$
				new ModelElementCreationFactory(StepModelElement.class, getDiagram()), FlowCommonImages.ACTIVITY_SMALL,
				FlowCommonImages.ACTIVITY);
		entries.add(entry);

		entry = new CombinedTemplateCreationEntry(BatchSchemaConstants.ELEM_SPLIT,
				Messages.getString("BatchEditorPaletteFactory.SPLIT_COMPONENT_DESCRIPTION"), //$NON-NLS-1$
				new ModelElementCreationFactory(SplitContainerElement.class, getDiagram()), BatchImages.SPLIT_SMALL,
				BatchImages.SPLIT);
		entries.add(entry);

		entry = new CombinedTemplateCreationEntry(BatchSchemaConstants.ELEM_DECISION,
				Messages.getString("BatchEditorPaletteFactory.DECISION_COMPONENT_DESCRIPTION"), //$NON-NLS-1$
				new ModelElementCreationFactory(DecisionModelElement.class, getDiagram()), BatchImages.DECISION_SMALL,
				BatchImages.DECISION);
		entries.add(entry);

		entry = new CombinedTemplateCreationEntry(BatchSchemaConstants.ELEM_END,
				Messages.getString("BatchEditorPaletteFactory.END_COMPONENT_DESCRIPTION"), //$NON-NLS-1$
				new ModelElementCreationFactory(EndModelElement.class, getDiagram()), BatchImages.END_SMALL,
				BatchImages.END);
		entries.add(entry);

		entry = new CombinedTemplateCreationEntry(BatchSchemaConstants.ELEM_FAIL,
				Messages.getString("BatchEditorPaletteFactory.FAIL_COMPONENT_DESCRIPTION"), //$NON-NLS-1$
				new ModelElementCreationFactory(FailModelElement.class, getDiagram()), BatchImages.FAIL_SMALL,
				BatchImages.FAIL);
		entries.add(entry);

		entry = new CombinedTemplateCreationEntry(BatchSchemaConstants.ELEM_NEXT,
				Messages.getString("BatchEditorPaletteFactory.NEXT_COMPONENT_DESCRIPTION"), //$NON-NLS-1$
				new ModelElementCreationFactory(NextModelElement.class, getDiagram()), BatchImages.NEXT_SMALL,
				BatchImages.NEXT);
		entries.add(entry);

		entry = new CombinedTemplateCreationEntry(BatchSchemaConstants.ELEM_STOP,
				Messages.getString("BatchEditorPaletteFactory.STOP_COMPONENT_DESCRIPTION"), //$NON-NLS-1$
				new ModelElementCreationFactory(StopModelElement.class, getDiagram()), BatchImages.STOP_SMALL,
				BatchImages.STOP);
		entries.add(entry);

		drawer.addAll(entries);
		categories.add(drawer);
		return categories;
	}

	@Override
	protected List<PaletteEntry> createConnectionTools() {
		List<PaletteEntry> entries = new ArrayList<PaletteEntry>();
		ToolEntry tool = new ConnectionCreationToolEntry(Messages
				.getString("BatchEditorPaletteFactory.NEXT_CONNECTION_TITLE"), Messages //$NON-NLS-1$
				.getString("BatchEditorPaletteFactory.NEXT_CONNECTION_DESCRIPTION"), new TransitionCreationFactory(), //$NON-NLS-1$
				FlowCommonImages.CONNECTION_SOLID, FlowCommonImages.CONNECTION_SOLID);
		entries.add(tool);
		return entries;
	}

}
