/******************************************************************************************
 * Copyright (c) 2008 - 2010 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.core.schemas;

/**
 * Web Flow schema derived from:
 * <code>http://www.springframework.org/schema/webflow-config/spring-webflow-config-2.0.xsd</code>
 * @author Leo Dos Santos
 * @author Christian Dupuis
 * @since STS 2.0.0
 * @version Spring Web Flow 2.0
 */
public class WebFlowConfigSchemaConstants {

	// URI

	public static String URI = "http://www.springframework.org/schema/webflow-config"; //$NON-NLS-1$

	// Element tags

	public static String ELEM_ALWAYS_REDIRECT_ON_PAUSE = "always-redirect-on-pause"; //$NON-NLS-1$

	public static String ELEM_ATTRIBUTE = "attribute"; //$NON-NLS-1$

	public static String ELEM_FLOW_BUILDER = "flow-builder"; //$NON-NLS-1$

	public static String ELEM_FLOW_BUILDER_SERVICES = "flow-builder-services"; //$NON-NLS-1$

	public static String ELEM_FLOW_DEFINITION_ATTRIBUTES = "flow-definition-attributes"; //$NON-NLS-1$

	public static String ELEM_FLOW_EXECUTION_ATTRIBUTES = "flow-execution-attributes"; //$NON-NLS-1$

	public static String ELEM_FLOW_EXECUTION_LISTENERS = "flow-execution-listeners"; //$NON-NLS-1$

	public static String ELEM_FLOW_EXECUTION_REPOSITORY = "flow-execution-repository"; //$NON-NLS-1$

	public static String ELEM_FLOW_EXECUTOR = "flow-executor"; //$NON-NLS-1$

	public static String ELEM_FLOW_LOCATION = "flow-location"; //$NON-NLS-1$

	public static String ELEM_FLOW_LOCATION_PATTERN = "flow-location-pattern"; //$NON-NLS-1$

	public static String ELEM_FLOW_REGISTRY = "flow-registry"; //$NON-NLS-1$

	public static String ELEM_LISTENER = "listener"; //$NON-NLS-1$

	// Attribute tags

	public static String ATTR_CLASS = "class"; //$NON-NLS-1$

	public static String ATTR_CONVERSION_SERVICE = "conversion-service"; //$NON-NLS-1$

	public static String ATTR_CRITERIA = "criteria"; //$NON-NLS-1$

	public static String ATTR_EXPRESSION_PARSER = "expression-parser"; //$NON-NLS-1$

	public static String ATTR_FLOW_BUILDER_SERVICES = "flow-builder-services"; //$NON-NLS-1$

	public static String ATTR_FLOW_REGISTRY = "flow-registry"; //$NON-NLS-1$

	public static String ATTR_ID = "id"; //$NON-NLS-1$

	public static String ATTR_MAX_EXECUTION_SNAPSHOTS = "max-execution-snapshots"; //$NON-NLS-1$

	public static String ATTR_MAX_EXECUTIONS = "max-executions"; //$NON-NLS-1$

	public static String ATTR_NAME = "name"; //$NON-NLS-1$

	public static String ATTR_PARENT = "parent"; //$NON-NLS-1$

	public static String ATTR_PATH = "path"; //$NON-NLS-1$

	public static String ATTR_REF = "ref"; //$NON-NLS-1$

	public static String ATTR_TYPE = "type"; //$NON-NLS-1$

	public static String ATTR_VALUE = "value"; //$NON-NLS-1$

	public static String ATTR_VIEW_FACTORY_CREATOR = "view-factory-creator"; //$NON-NLS-1$

	// Web Flow 1.x tags (incomplete)

	public static String ELEM_EXECUTOR = "executor"; //$NON-NLS-1$

	public static String ELEM_REPOSITORY = "repository"; //$NON-NLS-1$

	public static String ATTR_CONVERSATION_MANAGER_REF = "conversation-manager-ref"; //$NON-NLS-1$

	public static String ATTR_REGISTRY_REF = "registry-ref"; //$NON-NLS-1$

}
