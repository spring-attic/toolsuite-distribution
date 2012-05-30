/******************************************************************************************
 * Copyright (c) 2008 - 2009 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.core.schemas;


/**
 * OSGi Compendium schema derived from
 * <code>http://www.springframework.org/schema/osgi-compendium/spring-osgi-compendium-1.1.xsd</code>
 * @author Leo Dos Santos
 * @author Christian Dupuis
 * @since STS 2.0.0
 * @version Spring OSGi Compendium 1.1
 */
public class OsgiCompendiumSchemaConstants {

	// URI

	public static String URI = "http://www.springframework.org/schema/osgi-compendium"; //$NON-NLS-1$

	// Element tags

	public static String ELEM_CONFIG_PROPERTIES = "config-properties"; //$NON-NLS-1$

	public static String ELEM_DEFAULT_PROPERTIES = "default-properties"; //$NON-NLS-1$

	public static String ELEM_MANAGED_SERVICE = "managed-service"; //$NON-NLS-1$

	public static String ELEM_MANAGED_SERVICE_FACTORY = "managed-service-factory"; //$NON-NLS-1$

	public static String ELEM_PROPERTY_PLACEHOLDER = "property-placeholder"; //$NON-NLS-1$

	// Attribute tags

	public static String ATTR_DEFAULTS_REF = "defaults-ref"; //$NON-NLS-1$

	public static String ATTR_FACTORY_PID = "factory-pid"; //$NON-NLS-1$

	public static String ATTR_ID = "id"; //$NON-NLS-1$

	public static String ATTR_PERSISTENT_ID = "persistent-id"; //$NON-NLS-1$

	public static String ATTR_PLACEHOLDER_PREFIX = "placeholder-prefix"; //$NON-NLS-1$

	public static String ATTR_PLACEHOLDER_SUFFIX = "placeholder-suffix"; //$NON-NLS-1$

	public static String ATTR_UPDATE_METHOD = "update-method"; //$NON-NLS-1$

	public static String ATTR_UPDATESTRATEGY = "updateStrategy"; //$NON-NLS-1$

}
