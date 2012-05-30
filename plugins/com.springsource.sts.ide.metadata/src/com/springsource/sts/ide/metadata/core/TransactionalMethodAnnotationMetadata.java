/******************************************************************************************
 * Copyright (c) 2008 - 2010 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.ide.metadata.core;

import org.springframework.ide.eclipse.beans.core.metadata.model.AbstractMethodAnnotationMetadata;
import org.springframework.ide.eclipse.beans.core.metadata.model.IMethodMetadata;
import org.springframework.ide.eclipse.core.model.IModelSourceLocation;
import org.springframework.transaction.annotation.Transactional;

/**
 * {@link IMethodMetadata} for {@link Transactional} annotation usage on the method level.
 * @author Christian Dupuis
 * @author Leo Dos Santos
 * @since 1.0.0
 */
public class TransactionalMethodAnnotationMetadata extends AbstractMethodAnnotationMetadata {

	private static final long serialVersionUID = -5855055542136631913L;

	public TransactionalMethodAnnotationMetadata(String key, String handle, Object value,
			IModelSourceLocation location) {
		super(key, handle, value, location);
	}

}
