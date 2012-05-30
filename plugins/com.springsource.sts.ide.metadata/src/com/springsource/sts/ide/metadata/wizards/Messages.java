/******************************************************************************************
 * Copyright (c) 2009 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.ide.metadata.wizards;

import org.eclipse.osgi.util.NLS;

/**
 * @author Leo Dos Santos
 */
public class Messages extends NLS {
	private static final String BUNDLE_NAME = "com.springsource.sts.ide.metadata.wizards.messages"; //$NON-NLS-1$
	public static String OpenRequestMappingUrlWizard_TITLE;
	public static String OpenRequestMappingUrlWizardPage_DESCRIPTION;
	public static String OpenRequestMappingUrlWizardPage_ERROR_LOADING_CACHE;
	public static String OpenRequestMappingUrlWizardPage_ERROR_SAVING_CACHE;
	public static String OpenRequestMappingUrlWizardPage_HEADER_TITLE;
	public static String OpenRequestMappingUrlWizardPage_LABEL_URL_PREFIX;
	public static String OpenRequestMappingUrlWizardPage_PAGE_TITLE;
	public static String OpenRequestMappingUrlWizardPage_WARNING_URL_CONSTRUCTION;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
