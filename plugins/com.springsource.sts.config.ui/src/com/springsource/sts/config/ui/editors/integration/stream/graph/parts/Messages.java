/******************************************************************************************
 * Copyright (c) 2010 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.ui.editors.integration.stream.graph.parts;

import org.eclipse.osgi.util.NLS;

/**
 * @author Leo Dos Santos
 */
public class Messages extends NLS {
	private static final String BUNDLE_NAME = "com.springsource.sts.config.ui.editors.integration.stream.graph.parts.messages"; //$NON-NLS-1$

	public static String IntStreamPaletteFactory_STDERR_CHANNEL_ADAPTER_COMPONENT_DESCRIPTION;

	public static String IntStreamPaletteFactory_STDIN_CHANNEL_ADAPTER_COMPONENT_DESCRIPTION;

	public static String IntStreamPaletteFactory_STDOUT_CHANNEL_ADAPTER_COMPONENT_DESCRIPTION;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
