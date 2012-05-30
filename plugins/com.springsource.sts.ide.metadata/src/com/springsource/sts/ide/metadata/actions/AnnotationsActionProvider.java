/******************************************************************************************
 * Copyright (c) 2009 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.ide.metadata.actions;

import org.eclipse.jface.action.IMenuManager;
import org.eclipse.ui.navigator.CommonActionProvider;
import org.eclipse.ui.navigator.ICommonActionExtensionSite;
import org.eclipse.ui.navigator.ICommonMenuConstants;

/**
 * @author Leo Dos Santos
 */
public class AnnotationsActionProvider extends CommonActionProvider {

	private ShowRequestMappingsAction requestMappingsAction;

	@Override
	public void init(ICommonActionExtensionSite aSite) {
		requestMappingsAction = new ShowRequestMappingsAction(aSite);
	}

	@Override
	public void fillContextMenu(IMenuManager menu) {
		if (requestMappingsAction.isEnabled()) {
			menu.appendToGroup(ICommonMenuConstants.GROUP_PROPERTIES,
					requestMappingsAction);
		}
	}

}
