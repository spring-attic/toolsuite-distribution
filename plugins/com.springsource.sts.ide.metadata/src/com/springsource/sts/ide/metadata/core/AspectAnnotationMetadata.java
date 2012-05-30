/******************************************************************************************
 * Copyright (c) 2008 - 2010 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.ide.metadata.core;

import java.util.Set;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.ide.eclipse.beans.core.metadata.model.AbstractAnnotationMetadata;
import org.springframework.ide.eclipse.beans.core.metadata.model.IMethodMetadata;
import org.springframework.ide.eclipse.beans.core.model.IBean;
import org.springframework.ide.eclipse.core.java.annotation.IAnnotationMetadata;
import org.springframework.ide.eclipse.core.model.IModelSourceLocation;

/**
 * {@link IAnnotationMetadata} implementation that represents a single information from
 * {@link Aspect} annotation.
 * @author Christian Dupuis
 * @author Leo Dos Santos
 * @since 1.0.0
 */
public class AspectAnnotationMetadata extends AbstractAnnotationMetadata {

	private static final long serialVersionUID = 6978657032146327628L;

	public AspectAnnotationMetadata(IBean bean, String handle, Object value,
			IModelSourceLocation location, Set<IMethodMetadata> methodMetaData) {
		super(bean, handle, value, location, methodMetaData);
	}

}
