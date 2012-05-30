/******************************************************************************************
 * Copyright (c) 2010 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.roo.ui.internal.wizard;

import org.eclipse.osgi.util.NLS;

/**
 * @author Terry Denney
 */
public class NewRooWizardMessages extends NLS {
	private static final String BUNDLE_NAME = "com.springsource.sts.roo.ui.internal.wizard.messages"; //$NON-NLS-1$

	public static String NewRooProjectWizardPageOne_0;

	public static String NewRooProjectWizardPageOne_Message_notOnWorkspaceRoot;

	public static String NewRooProjectWizardPageOne_noRooInstallationConfigured;

	public static String NewRooProjectWizardPageOne_notExisingProjectOnWorkspaceRoot;

	public static String NewRooProjectWizardPageOne_useDefaultRooInstallation;

	public static String NewRooProjectWizardPageOne_useDefaultRooInstallationNoCurrent;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, NewRooWizardMessages.class);
	}

	private NewRooWizardMessages() {
	}
}
