/******************************************************************************************
 * Copyright (c) 2010 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.ui.contenttype;

import org.eclipse.osgi.util.NLS;

/**
 * @author Leo Dos Santos
 */
public class ContentMessages extends NLS {
	private static final String BUNDLE_NAME = "com.springsource.sts.config.ui.contenttype.messages"; //$NON-NLS-1$

	public static String SpringElementContentDescriber_ERROR_CREATING_CONTENT_DESCRIBER;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, ContentMessages.class);
	}

	private ContentMessages() {
	}
}
