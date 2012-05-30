/******************************************************************************************
 * Copyright (c) 2009 - 2010 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.flow.model;

import org.eclipse.osgi.util.NLS;

/**
 * @author Leo Dos Santos
 */
public class Messages extends NLS {
	private static final String BUNDLE_NAME = "com.springsource.sts.config.flow.model.messages"; //$NON-NLS-1$

	public static String AbstractConfigFlowDiagram_ERROR_CREATING_GRAPH;

	public static String AbstractConfigFlowDiagram_ERROR_READING_COORDINATES;

	public static String AbstractConfigFlowDiagram_ERROR_SAVING_COORDINATES;

	public static String ModelElementCreationFactory_ERROR_CREATING_ELEMENT_MODEL;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
