/******************************************************************************************
 * Copyright (c) 2010 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.flow.actions;

import org.eclipse.osgi.util.NLS;

/**
 * @author Leo Dos Santos
 */
public class Messages extends NLS {
	private static final String BUNDLE_NAME = "com.springsource.sts.config.flow.actions.messages"; //$NON-NLS-1$

	public static String ExportAction_ACTION_LABEL;

	public static String ExportAction_ERROR_PNG_EXPORT_33_OR_NEWER;

	public static String ExportAction_ERROR_TITLE;

	public static String ExportAction_JPEG_ORIGINAL_TITLE;

	public static String ExportAction_PNG_ORIGINAL_TITLE;

	public static String ExportAction_TOOLTIP_LABEL;

	public static String ResetManualLayoutAction_RESET_LAYOUT_ACTION_LABEL;

	public static String ShowPropertiesAction_SHOW_PROPERTIES_ACTION_LABEL;

	public static String ShowSourceAction_SHOW_SOURCE_ACTION_LABEL;

	public static String ToggleLayoutAction_ACTION_NAME;

	public static String ToggleLayoutAction_TOOLTIP_ENABLE_AUTO_LAYOUT;

	public static String ToggleLayoutAction_TOOLTIP_ENABLE_MANUAL_LAYOUT;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
