/******************************************************************************************
 * Copyright (c) 2009 - 2010 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.ide.metadata.core;

import java.util.Set;

import org.springframework.context.annotation.Configuration;
import org.springframework.ide.eclipse.beans.core.metadata.model.AbstractAnnotationMetadata;
import org.springframework.ide.eclipse.beans.core.metadata.model.IMethodMetadata;
import org.springframework.ide.eclipse.beans.core.model.IBean;
import org.springframework.ide.eclipse.core.java.annotation.IAnnotationMetadata;
import org.springframework.ide.eclipse.core.model.IModelSourceLocation;

/**
 * {@link IAnnotationMetadata} implementation that represents a single information from {@link Configuration} annotation.
 * @author Christian Dupuis
 * @since 1.0.0
 */
public class ConfigurationAnnotationMetadata extends AbstractAnnotationMetadata {

	private static final long serialVersionUID = 2292067179938558318L;

	public ConfigurationAnnotationMetadata(IBean bean, String handle, Object value, IModelSourceLocation location,
			Set<IMethodMetadata> methodMetaData) {
		super(bean, handle, value, location, methodMetaData);
	}

}
