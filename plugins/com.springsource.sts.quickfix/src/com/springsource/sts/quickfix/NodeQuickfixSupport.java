/******************************************************************************************
 * Copyright (c) 2008 - 2010 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.quickfix;

import com.springsource.sts.config.core.schemas.BeansSchemaConstants;
import com.springsource.sts.quickfix.validator.BeanValidator;
import com.springsource.sts.quickfix.validator.ClassAttributeValidator;
import com.springsource.sts.quickfix.validator.NamespaceElementsValidator;
import com.springsource.sts.quickfix.validator.PropertyValidator;

/**
 * Support class for getting the right validator for a node of a bean
 * configuration
 * @author Terry Denney
 * @author Leo Dos Santos
 * @author Christian Dupuis
 * @since 2.0
 */
public class NodeQuickfixSupport extends QuickfixSupport {

	@Override
	protected void init() {
		setValidator(BEAN_NAMESPACE, BeansSchemaConstants.ELEM_BEAN, new ClassAttributeValidator());
		setValidator(BEAN_NAMESPACE, BeansSchemaConstants.ELEM_CONSTRUCTOR_ARG, new BeanValidator());
		setValidator(BEAN_NAMESPACE, BeansSchemaConstants.ELEM_PROPERTY, new PropertyValidator());
		setValidator(BEAN_NAMESPACE, BeansSchemaConstants.ELEM_ALIAS, new BeanValidator());

		setValidator("!" + BEAN_NAMESPACE, null, new NamespaceElementsValidator());
	}

}
