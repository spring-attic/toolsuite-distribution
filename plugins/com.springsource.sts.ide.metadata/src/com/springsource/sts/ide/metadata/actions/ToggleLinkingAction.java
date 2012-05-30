/******************************************************************************************
 * Copyright (c) 2011 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.ide.metadata.actions;

import org.eclipse.jdt.internal.ui.actions.AbstractToggleLinkingAction;

import com.springsource.sts.ide.metadata.ui.RequestMappingView;

/**
 * @author Leo Dos Santos
 */
@SuppressWarnings("restriction")
public class ToggleLinkingAction extends AbstractToggleLinkingAction {

	private RequestMappingView view;

	public ToggleLinkingAction(RequestMappingView view) {
		setText(Messages.ToggleLinkingAction_LABEL);
		setDescription(Messages.ToggleLinkingAction_DESCRIPTION);
		setToolTipText(Messages.ToggleLinkingAction_TOOLTIP);
		setChecked(view.isLinkingEnabled());
		this.view = view;
	}

	@Override
	public void run() {
		view.setLinkingEnabled(isChecked());
	}

}
