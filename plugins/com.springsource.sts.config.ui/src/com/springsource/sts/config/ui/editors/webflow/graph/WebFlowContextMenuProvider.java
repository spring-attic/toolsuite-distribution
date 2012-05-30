/******************************************************************************************
 * Copyright (c) 2011 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.ui.editors.webflow.graph;

import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.ui.actions.ActionRegistry;
import org.eclipse.gef.ui.actions.GEFActionConstants;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;

import com.springsource.sts.config.flow.actions.SpringConfigContextMenuProvider;

/**
 * @author Leo Dos Santos
 */
public class WebFlowContextMenuProvider extends SpringConfigContextMenuProvider {

	public WebFlowContextMenuProvider(EditPartViewer viewer, ActionRegistry registry) {
		super(viewer, registry);
	}

	@Override
	public void buildContextMenu(IMenuManager menu) {
		super.buildContextMenu(menu);
		IAction action = getActionRegistry().getAction(ToggleActionStateAction.ID_ACTION);
		if (action.isEnabled()) {
			menu.appendToGroup(GEFActionConstants.GROUP_REST, action);
		}

		action = getActionRegistry().getAction(ToggleDecisionStateAction.ID_ACTION);
		if (action.isEnabled()) {
			menu.appendToGroup(GEFActionConstants.GROUP_REST, action);
		}

		action = getActionRegistry().getAction(ToggleEndStateAction.ID_ACTION);
		if (action.isEnabled()) {
			menu.appendToGroup(GEFActionConstants.GROUP_REST, action);
		}

		action = getActionRegistry().getAction(ToggleSubflowStateAction.ID_ACTION);
		if (action.isEnabled()) {
			menu.appendToGroup(GEFActionConstants.GROUP_REST, action);
		}

		action = getActionRegistry().getAction(ToggleViewStateAction.ID_ACTION);
		if (action.isEnabled()) {
			menu.appendToGroup(GEFActionConstants.GROUP_REST, action);
		}
	}

}
