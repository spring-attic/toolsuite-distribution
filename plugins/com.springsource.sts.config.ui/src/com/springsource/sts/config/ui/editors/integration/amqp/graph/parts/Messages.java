package com.springsource.sts.config.ui.editors.integration.amqp.graph.parts;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "com.springsource.sts.config.ui.editors.integration.amqp.graph.parts.messages"; //$NON-NLS-1$

	public static String IntAmqpPaletteFactory_CHANNEL_COMPONENT_DESCRIPTION;

	public static String IntAmqpPaletteFactory_INBOUND_CHANNEL_ADAPTER_COMPONENT_DESCRIPTION;

	public static String IntAmqpPaletteFactory_INBOUND_GATEWAY_COMPONENT_DESCRIPTION;

	public static String IntAmqpPaletteFactory_OUTBOUND_CHANNEL_ADAPTER_COMPONENT_DESCRIPTION;

	public static String IntAmqpPaletteFactory_OUTBOUND_GATEWAY_COMPONENT_DESCRIPTION;

	public static String IntAmqpPaletteFactory_PUBLISH_SUBSCRIBE_CHANNEL_COMPONENT_DESCRIPTION;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
