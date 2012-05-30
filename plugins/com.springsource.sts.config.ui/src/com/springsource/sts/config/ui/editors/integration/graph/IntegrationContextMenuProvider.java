/******************************************************************************************
 * Copyright (c) 2010 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.ui.editors.integration.graph;

import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.ui.actions.ActionRegistry;
import org.eclipse.gef.ui.actions.GEFActionConstants;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;

import com.springsource.sts.config.flow.actions.SpringConfigContextMenuProvider;

/**
 * @author Leo Dos Santos
 */
public class IntegrationContextMenuProvider extends SpringConfigContextMenuProvider {

	public IntegrationContextMenuProvider(EditPartViewer viewer, ActionRegistry registry) {
		super(viewer, registry);
	}

	@Override
	public void buildContextMenu(IMenuManager menu) {
		super.buildContextMenu(menu);
		IAction action = getActionRegistry().getAction(CreateExplicitChannelAction.EXPLICIT_CHANNEL_ID);
		if (action.isEnabled()) {
			menu.appendToGroup(GEFActionConstants.GROUP_EDIT, action);
		}
	}

}
