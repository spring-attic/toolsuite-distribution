/******************************************************************************************
 * Copyright (c) 2010 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.ui.navigator;

import org.eclipse.jface.action.IMenuManager;
import org.eclipse.ui.navigator.CommonActionProvider;
import org.eclipse.ui.navigator.ICommonActionExtensionSite;
import org.eclipse.ui.navigator.ICommonMenuConstants;

/**
 * @author Leo Dos Santos
 * @since 2.5.2
 */
public class StsSpringExplorerActionProvider extends CommonActionProvider {

	private StsShowBeansGraphAction showBeansGraphAction;

	public StsSpringExplorerActionProvider() {
	}

	@Override
	public void init(ICommonActionExtensionSite site) {
		showBeansGraphAction = new StsShowBeansGraphAction(site);
	}

	@Override
	public void fillContextMenu(IMenuManager menu) {
		if (showBeansGraphAction.isEnabled()) {
			menu.appendToGroup(ICommonMenuConstants.GROUP_OPEN, showBeansGraphAction);
		}
	}

}
