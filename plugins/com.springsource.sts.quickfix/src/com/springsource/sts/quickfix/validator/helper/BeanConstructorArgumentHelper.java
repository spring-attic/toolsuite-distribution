/******************************************************************************************
 * Copyright (c) 2010 - 2011 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.quickfix.validator.helper;

import org.eclipse.core.resources.IFile;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMAttr;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMNode;
import org.springframework.beans.factory.config.ConstructorArgumentValues;
import org.springframework.ide.eclipse.beans.core.internal.model.BeanConstructorArgument;
import org.springframework.ide.eclipse.beans.core.model.IBean;
import org.springframework.ide.eclipse.beans.core.model.IBeanConstructorArgument;
import org.springframework.ide.eclipse.beans.core.model.IBeansModelElementTypes;
import org.springframework.ide.eclipse.core.model.IModelElement;
import org.springframework.ide.eclipse.core.model.IResourceModelElement;
import org.w3c.dom.Node;

import com.springsource.sts.config.core.schemas.BeansSchemaConstants;

/**
 * @author Terry Denney
 */
public class BeanConstructorArgumentHelper extends BeanConstructorArgument implements IBeanConstructorArgument {

	private static String getElementValue(IDOMNode n) {
		if (n instanceof IDOMAttr) {
			return n.getNodeValue();
		}
		else {
			Node value = n.getAttributes().getNamedItem(BeansSchemaConstants.ATTR_VALUE);
			if (value != null) {
				return value.getNodeValue();
			}

			value = n.getAttributes().getNamedItem(BeansSchemaConstants.ATTR_REF);
			if (value != null) {
				return value.getNodeValue();
			}
			return null;
		}
	}

	private final IBean parentBean;

	private final IDOMNode argNode;

	private final String name;

	public BeanConstructorArgumentHelper(int index, IDOMNode argNode, IFile file, IBean parentBean) {
		this(index, null, argNode, file, parentBean);
	}

	public BeanConstructorArgumentHelper(int index, String name, IDOMNode argNode, IFile file, IBean parentBean) {
		super(parentBean, index, new ConstructorArgumentValues.ValueHolder(getElementValue(argNode)));
		this.argNode = argNode;
		this.parentBean = parentBean;
		this.name = name;
	}

	@Override
	public IModelElement[] getElementChildren() {
		return new IModelElement[0];
	}

	@Override
	public IModelElement getElementParent() {
		return parentBean;
	}

	@Override
	public IResourceModelElement getElementSourceElement() {
		return parentBean;
	}

	@Override
	public int getElementType() {
		return IBeansModelElementTypes.CONSTRUCTOR_ARGUMENT_TYPE;
	}

	@Override
	public Object getValue() {
		return getElementValue(argNode);
	}

	@Override
	public String getName() {
		return name;
	}
}
