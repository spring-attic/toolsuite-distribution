/******************************************************************************************
 * Copyright (c) 2009 - 2010 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.ide.metadata.core;

import org.springframework.ide.eclipse.beans.core.metadata.model.AbstractMethodAnnotationMetadata;
import org.springframework.ide.eclipse.beans.core.metadata.model.IMethodMetadata;
import org.springframework.ide.eclipse.core.model.IModelSourceLocation;
import org.springframework.transaction.annotation.Transactional;

/**
 * {@link IMethodMetadata} for {@link Transactional} annotation usage on the method level.
 * @author Christian Dupuis
 * @since 2.2.0
 */
public class AutowireMethodAnnotationMetadata extends AbstractMethodAnnotationMetadata {

	private static final long serialVersionUID = -841530374514332472L;

	public AutowireMethodAnnotationMetadata(String key, String handle, Object value,
			IModelSourceLocation location) {
		super(key, handle, value, location);
	}

}
