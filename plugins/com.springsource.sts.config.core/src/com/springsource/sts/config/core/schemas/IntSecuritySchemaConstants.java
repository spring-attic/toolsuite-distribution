/******************************************************************************************
 * Copyright (c) 2010 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.core.schemas;

/**
 * Integration Security adapter schema derived from
 * <code>http://www.springframework.org/schema/integration/security/spring-integration-security-2.0.xsd</code>
 * @author Leo Dos Santos
 * @since STS 2.5.0
 * @version Spring Integration 2.0
 */
public class IntSecuritySchemaConstants {

	// URI

	public static String URI = "http://www.springframework.org/schema/integration/security"; //$NON-NLS-1$

	// Element tags

	public static String ELEM_ACCESS_POLICY = "access-policy"; //$NON-NLS-1$

	public static String ELEM_SECURED_CHANNELS = "secured-channels"; //$NON-NLS-1$

	// Attribute tags

	public static String ATTR_ACCESS_DECISION_MANAGER = "access-decision-manager"; //$NON-NLS-1$

	public static String ATTR_AUTHENTICATION_MANAGER = "authentication-manager"; //$NON-NLS-1$

	public static String ATTR_PATTERN = "pattern"; //$NON-NLS-1$

	public static String ATTR_RECEIVE_ACCESS = "receive-access"; //$NON-NLS-1$

	public static String ATTR_SEND_ACCESS = "send-access"; //$NON-NLS-1$

}
