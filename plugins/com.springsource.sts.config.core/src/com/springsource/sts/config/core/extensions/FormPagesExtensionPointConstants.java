/******************************************************************************************
 * Copyright (c) 2008 - 2010 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.core.extensions;

/**
 * This extension point is used to add new form pages to an
 * {@link AbstractConfigEditor}. Each page added through this extension point is
 * intended to represent a single namespace in a Spring configuration XML file.
 * @author Leo Dos Santos
 * @author Christian Dupuis
 * @since STS 2.0.0
 */
public class FormPagesExtensionPointConstants {

	public static String POINT_ID = "com.springsource.sts.config.ui.formPages"; //$NON-NLS-1$

	public static String ELEM_FORM_PAGE = "formPage"; //$NON-NLS-1$

	public static String ATTR_CLASS = "class"; //$NON-NLS-1$

	public static String ATTR_ID = "id"; //$NON-NLS-1$

	public static String ATTR_NAME = "name"; //$NON-NLS-1$

	public static String ATTR_NAMESPACE_PREFIX = "namespacePrefix"; //$NON-NLS-1$

	public static String ATTR_NAMESPACE_URI = "namespaceUri"; //$NON-NLS-1$

	public static String ATTR_GRAPH = "graph"; //$NON-NLS-1$

}
