/******************************************************************************************
 * Copyright (c) 2010 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.core.extensions;

/**
 * This extension point is used to contribute support for additional namespaces
 * into existing {@link AbstractConfigEditor} form pages. Each contribution is
 * intended to represent a single namespace in a Spring configuration XML file.
 * @author Leo Dos Santos
 * @author Christian Dupuis
 * @since 2.5.0
 */
public class PageAdaptersExtensionPointConstants {

	public static String POINT_ID = "com.springsource.sts.config.ui.pageAdapters"; //$NON-NLS-1$

	public static String ELEM_ADAPTER = "adapter"; //$NON-NLS-1$

	public static String ATTR_ID = "id"; //$NON-NLS-1$

	public static String ATTR_NAMESPACE_URI = "namespaceUri"; //$NON-NLS-1$

	public static String ATTR_PARENT_URI = "parentUri"; //$NON-NLS-1$

	public static String ATTR_DETAILS_FACTORY = "detailsFactory"; //$NON-NLS-1$

	public static String ATTR_MODEL_FACTORY = "modelFactory"; //$NON-NLS-1$

	public static String ATTR_EDITPART_FACTORY = "editpartFactory"; //$NON-NLS-1$

	public static String ATTR_PALETTE_FACTORY = "paletteFactory"; //$NON-NLS-1$

	public static String ATTR_LABEL = "label"; //$NON-NLS-1$

}
