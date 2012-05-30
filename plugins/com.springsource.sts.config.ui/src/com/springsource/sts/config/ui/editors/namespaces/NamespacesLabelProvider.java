/******************************************************************************************
 * Copyright (c) 2008 - 2010 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.ui.editors.namespaces;

import org.eclipse.core.resources.IResource;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.swt.graphics.Image;
import org.springframework.ide.eclipse.beans.ui.BeansUIImages;
import org.springframework.ide.eclipse.beans.ui.namespaces.INamespaceDefinition;

import com.springsource.sts.config.ui.editors.AbstractConfigLabelProvider;

/**
 * @author Leo Dos Santos
 * @author Christian Dupuis
 */
public class NamespacesLabelProvider extends AbstractConfigLabelProvider {

	private final IResource resource;

	public NamespacesLabelProvider(IResource resource) {
		this.resource = resource;
	}

	public void addListener(ILabelProviderListener listener) {
		// TODO Auto-generated method stub

	}

	public void dispose() {
		// TODO Auto-generated method stub

	}

	public Image getColumnImage(Object element, int columnIndex) {
		return getImage(element);
	}

	public String getColumnText(Object element, int columnIndex) {
		return getText(element);
	}

	public Image getImage(Object element) {
		if (element instanceof INamespaceDefinition) {
			INamespaceDefinition xsdDef = (INamespaceDefinition) element;
			return xsdDef.getNamespaceImage();
		}
		return BeansUIImages.getImage(BeansUIImages.IMG_OBJS_XSD);
	}

	public String getText(Object element) {
		if (element instanceof INamespaceDefinition) {
			INamespaceDefinition xsdDef = (INamespaceDefinition) element;
			return xsdDef.getNamespacePrefix(resource) + " - " + xsdDef.getNamespaceURI(); //$NON-NLS-1$
		}
		return ""; //$NON-NLS-1$
	}

	public boolean isLabelProperty(Object element, String property) {
		// TODO Auto-generated method stub
		return false;
	}

	public void removeListener(ILabelProviderListener listener) {
		// TODO Auto-generated method stub

	}

}
