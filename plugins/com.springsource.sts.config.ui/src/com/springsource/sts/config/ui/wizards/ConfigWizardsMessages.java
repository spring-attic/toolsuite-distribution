/******************************************************************************************
 * Copyright (c) 2008 - 2009 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.ui.wizards;

import org.eclipse.osgi.util.NLS;

/**
 * Message constants for bean configuration wizards and dialogs.
 * @author Wesley Coelho
 * @author Leo Dos Santos
 * @author Christian Dupuis
 */
public final class ConfigWizardsMessages extends NLS {

	private static final String FULLY_QUALIFIED_CLASS_NAME = "com.springsource.sts.config.ui.wizards.ConfigWizardsMessages";

	public static String NamespaceConfig_title;

	public static String NamespaceConfig_xsdDescription;

	public static String NamespaceConfig_selectSpecificXsd;

	public static String NamespaceConfig_selectNamespace;

	public static String NamespaceConfig_default;

	public static String NamespaceConfig_windowTitle;

	public static String NamespaceConfig_mustIncludeDefault;

	static {
		NLS.initializeMessages(FULLY_QUALIFIED_CLASS_NAME, ConfigWizardsMessages.class);
	}

	private ConfigWizardsMessages() {
		// Do not instantiate
	}
}
