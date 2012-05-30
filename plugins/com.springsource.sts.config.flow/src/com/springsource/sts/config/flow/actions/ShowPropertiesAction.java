/******************************************************************************************
 * Copyright (c) 2010 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.flow.actions;

import java.util.List;

import org.eclipse.gef.ui.actions.SelectionAction;
import org.eclipse.ui.IWorkbenchPart;

import com.springsource.sts.config.flow.parts.ActivityPart;

/**
 * @author Leo Dos Santos
 */
public class ShowPropertiesAction extends SelectionAction {

	public static String SHOW_PROPERTIES_ID = "ShowProperties"; //$NON-NLS-1$

	public ShowPropertiesAction(IWorkbenchPart part) {
		super(part);
		setId(SHOW_PROPERTIES_ID);
		setText(Messages.ShowPropertiesAction_SHOW_PROPERTIES_ACTION_LABEL);
	}

	@Override
	protected boolean calculateEnabled() {
		List parts = getSelectedObjects();
		if (parts.isEmpty()) {
			return false;
		}
		return (parts.get(0) instanceof ActivityPart);
	}

	@Override
	public void run() {
		ActivityPart part = (ActivityPart) getSelectedObjects().get(0);
		part.showProperties();
	}

}
