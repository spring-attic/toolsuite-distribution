/******************************************************************************************
 * Copyright (c) 2009 - 2010 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.ui.properties;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.ui.views.properties.IPropertySource;
import org.eclipse.ui.views.properties.IPropertySourceProvider;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMNode;
import org.eclipse.wst.xml.ui.internal.properties.XMLPropertySource;

/**
 * @author Leo Dos Santos
 * @author Christian Dupuis
 */
@SuppressWarnings("restriction")
public class SpringConfigPropertySourceProvider implements IPropertySourceProvider {

	public IPropertySource getPropertySource(Object object) {
		if (object instanceof IDOMNode) {
			return new XMLPropertySource((IDOMNode) object);
		}
		else if (object instanceof IAdaptable) {
			Object adapter = ((IAdaptable) object).getAdapter(IPropertySource.class);
			if (adapter instanceof IPropertySource) {
				return (IPropertySource) adapter;
			}
		}
		return null;
	}

}
