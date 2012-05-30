/******************************************************************************************
 * Copyright (c) 2010 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.ui.editors.integration.mail.graph.parts;

import org.eclipse.osgi.util.NLS;

/**
 * @author Leo Dos Santos
 */
public class Messages extends NLS {
	private static final String BUNDLE_NAME = "com.springsource.sts.config.ui.editors.integration.mail.graph.parts.messages"; //$NON-NLS-1$

	public static String IntMailPaletteFactory_HEADER_ENRICHER_COMPONENT_DESCRIPTION;

	public static String IntMailPaletteFactory_IMAP_IDLE_CHANNEL_ADAPTER_COMPONENT_DESCRIPTION;

	public static String IntMailPaletteFactory_INBOUND_CHANNEL_ADAPTER_COMPONENT_DESCRIPTION;

	public static String IntMailPaletteFactory_MAIL_TO_STRING_TRANSFORMER_COMPONENT_DESCRIPTION;

	public static String IntMailPaletteFactory_OUTBOUND_CHANNEL_ADAPTER_COMPONENT_DESCRIPTION;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
