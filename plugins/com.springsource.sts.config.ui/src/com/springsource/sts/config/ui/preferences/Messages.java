/******************************************************************************************
 * Copyright (c) 2009 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.ui.preferences;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * @author Leo Dos Santos
 * @author Christian Dupuis
 */
public class Messages {
	private static final String BUNDLE_NAME = "com.springsource.sts.config.ui.preferences.messages"; //$NON-NLS-1$

	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME);

	public static String getString(String key) {
		try {
			return RESOURCE_BUNDLE.getString(key);
		}
		catch (MissingResourceException e) {
			return '!' + key + '!';
		}
	}

	private Messages() {
	}
}
