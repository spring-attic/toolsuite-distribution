/******************************************************************************************
 * Copyright (c) 2008 - 2010 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.core.extensions;

/**
 * This extension point is used to add new wizard actions to an
 * {@link AbstractConfigEditor}. Each wizard added through this extension point
 * is intended to add a common XML template to a Spring configuration file.
 * @author Leo Dos Santos
 * @author Christian Dupuis
 * @since STS 2.0.0
 */
public class CommonActionsExtensionPointConstants {

	public static String POINT_ID = "com.springsource.sts.config.ui.commonActions"; //$NON-NLS-1$

	public static String ELEM_WIZARD = "wizard"; //$NON-NLS-1$

	public static String ATTR_CLASS = "class"; //$NON-NLS-1$

	public static String ATTR_DESCRIPTION = "description"; //$NON-NLS-1$

	public static String ATTR_ID = "id"; //$NON-NLS-1$

	public static String ATTR_NAMESPACE_URI = "namespaceUri"; //$NON-NLS-1$

}
