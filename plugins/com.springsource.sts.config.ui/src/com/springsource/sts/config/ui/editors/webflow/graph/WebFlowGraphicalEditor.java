/******************************************************************************************
 * Copyright (c) 2011 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.ui.editors.webflow.graph;

import org.eclipse.gef.ContextMenuProvider;
import org.eclipse.gef.ui.actions.ActionRegistry;
import org.eclipse.jface.action.IAction;

import com.springsource.sts.config.flow.AbstractConfigGraphicalEditor;
import com.springsource.sts.config.flow.model.AbstractConfigFlowDiagram;
import com.springsource.sts.config.flow.parts.AbstractConfigEditPartFactory;
import com.springsource.sts.config.flow.parts.AbstractConfigPaletteFactory;
import com.springsource.sts.config.ui.editors.webflow.graph.model.WebFlowDiagram;
import com.springsource.sts.config.ui.editors.webflow.graph.parts.WebFlowEditPartFactory;

/**
 * @author Leo Dos Santos
 */
public class WebFlowGraphicalEditor extends AbstractConfigGraphicalEditor {

	@Override
	protected void createActions() {
		super.createActions();
		ActionRegistry registry = getActionRegistry();
		IAction action = new ToggleActionStateAction(this);
		registry.registerAction(action);
		getSelectionActions().add(action.getId());

		action = new ToggleDecisionStateAction(this);
		registry.registerAction(action);
		getSelectionActions().add(action.getId());

		action = new ToggleEndStateAction(this);
		registry.registerAction(action);
		getSelectionActions().add(action.getId());

		action = new ToggleSubflowStateAction(this);
		registry.registerAction(action);
		getSelectionActions().add(action.getId());

		action = new ToggleViewStateAction(this);
		registry.registerAction(action);
		getSelectionActions().add(action.getId());
	}

	@Override
	protected ContextMenuProvider createContextMenuProvider() {
		return new WebFlowContextMenuProvider(getGraphicalViewer(), getActionRegistry());
	}

	@Override
	protected AbstractConfigEditPartFactory createEditPartFactory() {
		return new WebFlowEditPartFactory(this);
	}

	@Override
	protected AbstractConfigFlowDiagram createFlowDiagram() {
		return new WebFlowDiagram(this);
	}

	@Override
	protected AbstractConfigPaletteFactory createPaletteFactory() {
		return new WebFlowEditorPaletteFactory(this);
	}

}
