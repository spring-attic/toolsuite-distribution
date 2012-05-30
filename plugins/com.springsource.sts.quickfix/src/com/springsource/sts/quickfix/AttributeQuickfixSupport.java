/******************************************************************************************
 * Copyright (c) 2008 - 2010 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.quickfix;

import com.springsource.sts.config.core.schemas.BeansSchemaConstants;
import com.springsource.sts.quickfix.validator.BeanReferenceValidator;
import com.springsource.sts.quickfix.validator.ClassAttributeValidator;
import com.springsource.sts.quickfix.validator.ConstructorArgNameValidator;
import com.springsource.sts.quickfix.validator.FactoryBeanValidator;
import com.springsource.sts.quickfix.validator.FactoryMethodValidator;
import com.springsource.sts.quickfix.validator.InitDestroyMethodValidator;
import com.springsource.sts.quickfix.validator.NamespaceElementsValidator;
import com.springsource.sts.quickfix.validator.PropertyValidator;

/**
 * Support class for getting the right validator for an attribute of a bean
 * configuration
 * @author Terry Denney
 * @author Leo Dos Santos
 * @author Christian Dupuis
 * @since 2.0
 */
public class AttributeQuickfixSupport extends QuickfixSupport {

	@Override
	protected void init() {
		setAttributeValidator(BEAN_NAMESPACE, BeansSchemaConstants.ELEM_BEAN, BeansSchemaConstants.ATTR_CLASS,
				new ClassAttributeValidator());
		setAttributeValidator(BEAN_NAMESPACE, BeansSchemaConstants.ELEM_BEAN, BeansSchemaConstants.ATTR_DESTROY_METHOD,
				new InitDestroyMethodValidator());
		setAttributeValidator(BEAN_NAMESPACE, BeansSchemaConstants.ELEM_BEAN, BeansSchemaConstants.ATTR_FACTORY_METHOD,
				new FactoryMethodValidator());
		setAttributeValidator(BEAN_NAMESPACE, BeansSchemaConstants.ELEM_BEAN, BeansSchemaConstants.ATTR_INIT_METHOD,
				new InitDestroyMethodValidator());
		setAttributeValidator(BEAN_NAMESPACE, BeansSchemaConstants.ELEM_BEAN, BeansSchemaConstants.ATTR_FACTORY_BEAN,
				new FactoryBeanValidator());
		setAttributeValidator(BEAN_NAMESPACE, BeansSchemaConstants.ELEM_BEAN, BeansSchemaConstants.ATTR_DEPENDS_ON,
				new BeanReferenceValidator());
		setAttributeValidator(BEAN_NAMESPACE, BeansSchemaConstants.ELEM_BEAN, BeansSchemaConstants.ATTR_PARENT,
				new BeanReferenceValidator());

		setAttributeValidator(BEAN_NAMESPACE, BeansSchemaConstants.ELEM_PROPERTY, BeansSchemaConstants.ATTR_NAME,
				new PropertyValidator());
		setAttributeValidator(BEAN_NAMESPACE, BeansSchemaConstants.ELEM_PROPERTY, BeansSchemaConstants.ATTR_REF,
				new BeanReferenceValidator());

		setAttributeValidator(BEAN_NAMESPACE, BeansSchemaConstants.ELEM_CONSTRUCTOR_ARG, BeansSchemaConstants.ATTR_REF,
				new BeanReferenceValidator());
		setAttributeValidator(BEAN_NAMESPACE, BeansSchemaConstants.ELEM_CONSTRUCTOR_ARG,
				BeansSchemaConstants.ATTR_NAME, new ConstructorArgNameValidator());

		// TODO: uncomment when BeanAliasRule works properly
		// setAttributeValidator(BEAN_NAMESPACE,
		// BeansSchemaConstants.ELEM_ALIAS, BeansSchemaConstants.ATTR_ALIAS,
		// new BeanAliasValidator());

		setAttributeValidator("!" + BEAN_NAMESPACE, null, null, new NamespaceElementsValidator());
	}
}
