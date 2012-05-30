/******************************************************************************************
 * Copyright (c) 2009 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.ide.metadata.actions;

import org.eclipse.osgi.util.NLS;

/**
 * @author Leo Dos Santos
 */
public class Messages extends NLS {
	private static final String BUNDLE_NAME = "com.springsource.sts.ide.metadata.actions.messages"; //$NON-NLS-1$
	public static String OpenInBrowserAction_TITLE;
	public static String OpenInJavaEditorAction_TITLE;
	public static String ShowRequestMappingsAction_ERROR_OPENING_VIEW;
	public static String ShowRequestMappingsAction_TITLE;
	public static String ToggleBreakPointAction_TITLE;
	public static String ToggleLinkingAction_DESCRIPTION;
	public static String ToggleLinkingAction_LABEL;
	public static String ToggleLinkingAction_TOOLTIP;
	public static String ToggleOrientationAction_DESCRIPTION_HORIZONTAL;
	public static String ToggleOrientationAction_DESCRIPTION_VERTICAL;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
