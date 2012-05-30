/******************************************************************************************
 * Copyright (c) 2008 - 2010 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.quickfix;

import java.util.HashSet;
import java.util.Set;

import org.springframework.ide.eclipse.beans.core.namespaces.NamespaceUtils;
import org.w3c.dom.Attr;
import org.w3c.dom.Node;

import com.springsource.sts.quickfix.validator.BeanValidator;

/**
 * Abstract support class for getting the right validator for an attribute of a
 * bean configuration. Subclasses need to specify the initiation of the
 * available validators.
 * @author Terry Denney
 * @author Leo Dos Santos
 * @author Christian Dupuis
 * @since 2.0
 */
public abstract class QuickfixSupport {

	public static final String BEAN_NAMESPACE = NamespaceUtils.DEFAULT_NAMESPACE_URI;

	private final Set<BeanValidator> validators;

	public QuickfixSupport() {
		this.validators = new HashSet<BeanValidator>();
		init();
	}

	public Set<BeanValidator> getQuickfixValidators(Node node) {
		Set<BeanValidator> matchedValidators = new HashSet<BeanValidator>();
		for (BeanValidator validator : validators) {
			if (validator.accept(node)) {
				matchedValidators.add(validator);
			}
		}

		return matchedValidators;
	}

	public Set<BeanValidator> getQuickfixValidators(Node node, Attr attr) {
		Set<BeanValidator> matchedValidators = new HashSet<BeanValidator>();
		for (BeanValidator validator : validators) {
			if (validator.acceptAttribute(node, attr)) {
				matchedValidators.add(validator);
			}
		}

		return matchedValidators;
	}

	abstract protected void init();

	protected void setAttributeValidator(String namespaceURI, String nodeName, String attributeName,
			BeanValidator validator) {
		validator.setNamespaceURI(namespaceURI);
		validator.setNodeName(nodeName);
		validator.setAttributeName(attributeName);
		validators.add(validator);
	}

	protected void setValidator(String namespaceURI, String nodeName, BeanValidator validator) {
		validator.setNamespaceURI(namespaceURI);
		validator.setNodeName(nodeName);
		validators.add(validator);
	}

}
