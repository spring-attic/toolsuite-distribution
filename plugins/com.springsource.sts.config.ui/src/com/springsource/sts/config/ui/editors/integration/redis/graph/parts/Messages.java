package com.springsource.sts.config.ui.editors.integration.redis.graph.parts;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "com.springsource.sts.config.ui.editors.integration.redis.graph.parts.messages"; //$NON-NLS-1$

	public static String IntRedisPaletteFactory_INBOUND_CHANNEL_ADAPTER_COMPONENT_DESCRIPTION;

	public static String IntRedisPaletteFactory_OUTBOUND_CHANNEL_ADAPTER_COMPONENT_DESCRIPTION;

	public static String IntRedisPaletteFactory_PUBLISH_SUBSCRIBE_CHANNEL_COMPONENT_DESCRIPTION;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
