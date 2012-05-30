/******************************************************************************************
 * Copyright (c) 2010 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.ui.editors.integration.xml.graph.parts;

import org.eclipse.osgi.util.NLS;

/**
 * @author Leo Dos Santos
 */
public class Messages extends NLS {
	private static final String BUNDLE_NAME = "com.springsource.sts.config.ui.editors.integration.xml.graph.parts.messages"; //$NON-NLS-1$

	public static String IntXmlPaletteFactory_MARSHALLING_TRANSFORMER_COMPONENT_DESCRIPTION;

	public static String IntXmlPaletteFactory_UNMARSHALLING_TRANSFORMER_COMPONENT_DESCRIPTION;

	public static String IntXmlPaletteFactory_VALIDATING_FILTER_COMPONENT_DESCRIPTION;

	public static String IntXmlPaletteFactory_XPATH_FILTER_COMPONENT_DESCRIPTION;

	public static String IntXmlPaletteFactory_XPATH_HEADER_ENRICHER_COMPONENT_DESCRIPTION;

	public static String IntXmlPaletteFactory_XPATH_ROUTER_COMPONENT_DESCRIPTION;

	public static String IntXmlPaletteFactory_XPATH_SPLITTER_COMPONENT_DESCRIPTION;

	public static String IntXmlPaletteFactory_XPATH_TRANSFORMER_COMPONENT_DESCRIPTION;

	public static String IntXmlPaletteFactory_XSLT_TRANSFORMER_COMPONENT_DESCRIPTION;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
