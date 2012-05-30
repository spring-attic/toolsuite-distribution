/******************************************************************************************
 * Copyright (c) 2008 - 2010 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.ide.metadata.core;

import org.springframework.ide.eclipse.beans.core.metadata.model.AbstractAnnotationMetadata;
import org.springframework.ide.eclipse.beans.core.metadata.model.IBeanMetadata;
import org.springframework.ide.eclipse.beans.core.model.IBean;
import org.springframework.ide.eclipse.core.model.IModelSourceLocation;

/**
 * {@link IBeanMetadata} implementation for Stereotype annotations.
 * @author Christian Dupuis
 * @author Leo Dos Santos
 * @since 1.0.0
 */
public class StereotypeAnnotationMetadata extends AbstractAnnotationMetadata {

	private static final long serialVersionUID = 7049736313729714666L;

	public StereotypeAnnotationMetadata(IBean bean, String handle, Object value,
			IModelSourceLocation location) {
		super(bean, handle, value, location);
	}

}
