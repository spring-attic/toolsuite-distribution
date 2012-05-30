/******************************************************************************************
 * Copyright (c) 2009 - 2010 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.ide.metadata.ui;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.springframework.ide.eclipse.beans.core.metadata.model.IMethodMetadata;

import com.springsource.sts.ide.metadata.core.RequestMappingAnnotationMetadata;
import com.springsource.sts.ide.metadata.core.RequestMappingMethodAnnotationMetadata;

/**
 * @author Leo Dos Santos
 * @author Christian Dupuis
 */
public class RequestMappingViewContentProvider implements
		IStructuredContentProvider {

	@SuppressWarnings("unchecked")
	public Object[] getElements(Object inputElement) {
		Set elements = new HashSet();
		Set<RequestMappingAnnotationMetadata> annotations = (Set<RequestMappingAnnotationMetadata>) inputElement;
		for (RequestMappingAnnotationMetadata annotation : annotations) {
			// elements.add(annotation);
			Set<IMethodMetadata> methods = annotation.getMethodMetaData();
			for (IMethodMetadata method : methods) {
				if (method instanceof RequestMappingMethodAnnotationMetadata) {
					elements.add(new RequestMappingMethodToClassMap(
							(RequestMappingMethodAnnotationMetadata) method,
							annotation));
				}
			}
		}
		return elements.toArray();
	}

	public void dispose() {
		// TODO Auto-generated method stub

	}

	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		// TODO Auto-generated method stub

	}

}
