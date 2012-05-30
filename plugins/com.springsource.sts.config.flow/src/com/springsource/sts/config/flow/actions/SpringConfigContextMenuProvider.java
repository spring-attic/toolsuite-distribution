/******************************************************************************************
 * Copyright (c) 2010 - 2011 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.flow.actions;

import org.eclipse.gef.ContextMenuProvider;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.ui.actions.ActionRegistry;
import org.eclipse.gef.ui.actions.GEFActionConstants;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.ui.actions.ActionFactory;

/**
 * @author Leo Dos Santos
 */
public class SpringConfigContextMenuProvider extends ContextMenuProvider {

	private final ActionRegistry registry;

	public SpringConfigContextMenuProvider(EditPartViewer viewer, ActionRegistry registry) {
		super(viewer);
		this.registry = registry;
	}

	@Override
	public void buildContextMenu(IMenuManager menu) {
		GEFActionConstants.addStandardActionGroups(menu);

		IAction action = registry.getAction(ActionFactory.UNDO.getId());
		menu.appendToGroup(GEFActionConstants.GROUP_UNDO, action);

		action = registry.getAction(ActionFactory.REDO.getId());
		menu.appendToGroup(GEFActionConstants.GROUP_UNDO, action);

		action = registry.getAction(ActionFactory.DELETE.getId());
		if (action.isEnabled()) {
			menu.appendToGroup(GEFActionConstants.GROUP_EDIT, action);
		}

		action = registry.getAction(ResetManualLayoutAction.RESET_LAYOUT_ID);
		menu.appendToGroup(GEFActionConstants.GROUP_EDIT, action);

		action = registry.getAction(ShowPropertiesAction.SHOW_PROPERTIES_ID);
		if (action.isEnabled()) {
			menu.appendToGroup(GEFActionConstants.GROUP_VIEW, action);
		}

		action = registry.getAction(ShowSourceAction.SHOW_SOURCE_ID);
		if (action.isEnabled()) {
			menu.appendToGroup(GEFActionConstants.GROUP_VIEW, action);
		}
	}

	public ActionRegistry getActionRegistry() {
		return registry;
	}

}
