/******************************************************************************************
 * Copyright (c) 2009 - 2010 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.flow.parts;

import org.eclipse.osgi.util.NLS;

/**
 * @author Leo Dos Santos
 */
public class Messages extends NLS {
	private static final String BUNDLE_NAME = "com.springsource.sts.config.flow.parts.messages"; //$NON-NLS-1$

	public static String AbstractConfigEditPartFactory_ERROR_CREATING_GRAPH;

	public static String AbstractConfigPaletteFactory_CONTROL_GROUP_TITLE;

	public static String AbstractConfigPaletteFactory_ERROR_CREATING_PALETTE;

	public static String StructuredActivityPart_ERROR_OPENING_VIEW;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
