/******************************************************************************************
 * Copyright (c) 2009 - 2010 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.ui.editors.integration.graph;

import org.eclipse.gef.ContextMenuProvider;
import org.eclipse.gef.ui.actions.ActionRegistry;
import org.eclipse.jface.action.IAction;

import com.springsource.sts.config.flow.AbstractConfigGraphicalEditor;
import com.springsource.sts.config.flow.model.AbstractConfigFlowDiagram;
import com.springsource.sts.config.flow.parts.AbstractConfigEditPartFactory;
import com.springsource.sts.config.flow.parts.AbstractConfigPaletteFactory;
import com.springsource.sts.config.ui.editors.integration.graph.model.IntegrationDiagram;
import com.springsource.sts.config.ui.editors.integration.graph.parts.IntegrationEditPartFactory;

/**
 * @author Leo Dos Santos
 */
public class IntegrationGraphicalEditor extends AbstractConfigGraphicalEditor {

	@Override
	protected void createActions() {
		super.createActions();
		ActionRegistry registry = getActionRegistry();
		IAction action = new CreateExplicitChannelAction(this);
		registry.registerAction(action);
		getSelectionActions().add(action.getId());
	}

	@Override
	protected ContextMenuProvider createContextMenuProvider() {
		return new IntegrationContextMenuProvider(getGraphicalViewer(), getActionRegistry());
	}

	@Override
	protected AbstractConfigEditPartFactory createEditPartFactory() {
		return new IntegrationEditPartFactory(this);
	}

	@Override
	protected AbstractConfigFlowDiagram createFlowDiagram() {
		return new IntegrationDiagram(this);
	}

	@Override
	protected AbstractConfigPaletteFactory createPaletteFactory() {
		return new IntegrationEditorPaletteFactory(this);
	}

}
