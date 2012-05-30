package com.springsource.sts.config.ui.editors.integration.gemfire.graph.parts;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "com.springsource.sts.config.ui.editors.integration.gemfire.graph.parts.messages"; //$NON-NLS-1$

	public static String IntGemfirePaletteFactory_CQ_INBOUND_CHANNEL_ADAPTER_COMPONENT_DESCRIPTION;

	public static String IntGemfirePaletteFactory_INBOUND_CHANNEL_ADAPTER_COMPONENT_DESCRIPTION;

	public static String IntGemfirePaletteFactory_OUTBOUND_CHANNEL_ADAPTER_COMPONENT_DESCRIPTION;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
