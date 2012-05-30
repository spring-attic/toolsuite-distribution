/******************************************************************************************
 * Copyright (c) 2009 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.ide.metadata.ui;

import org.eclipse.osgi.util.NLS;

/**
 * @author Leo Dos Santos
 */
public class Messages extends NLS {
	private static final String BUNDLE_NAME = "com.springsource.sts.ide.metadata.ui.messages"; //$NON-NLS-1$
	public static String AnnotationMetadataLabelProvider_DESCRIPTION_STEREOTYPE_ANNOTATION_GROUPING;
	public static String RequestMappingView_DESCRIPTION_EMPTY_JAVADOC;
	public static String RequestMappingView_DESCRIPTION_EMPTY_REQUESTMAPPINGS;
	public static String RequestMappingView_ERROR_GENERATING_JAVADOC;
	public static String RequestMappingView_ERROR_PROCESSING_RESOURCE_CHANGE;
	public static String RequestMappingView_HEADER_HANDLER_METHOD;
	public static String RequestMappingView_HEADER_REQUEST_METHOD;
	public static String RequestMappingView_HEADER_RESOURCE_URL;
	public static String RequestMappingView_PREFIX_CONFIG_FILE;
	public static String RequestMappingView_PREFIX_CONFIG_SET;
	public static String RequestMappingView_PREFIX_PROJECT;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
