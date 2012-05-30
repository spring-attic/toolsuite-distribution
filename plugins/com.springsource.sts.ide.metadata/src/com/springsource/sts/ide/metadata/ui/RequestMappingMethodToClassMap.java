/******************************************************************************************
 * Copyright (c) 2009 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.ide.metadata.ui;

import com.springsource.sts.ide.metadata.core.RequestMappingAnnotationMetadata;
import com.springsource.sts.ide.metadata.core.RequestMappingMethodAnnotationMetadata;

/**
 * Maps a {@link RequestMappingMethodAnnotationMetadata} to the
 * {@link RequestMappingAnnotationMetadata} that it is parented to, for use by
 * label and content providers.
 * 
 * @author Leo Dos Santos
 */
public class RequestMappingMethodToClassMap {

	private RequestMappingAnnotationMetadata classMetadata;

	private RequestMappingMethodAnnotationMetadata methodMetadata;

	public RequestMappingMethodToClassMap(
			RequestMappingMethodAnnotationMetadata methodMetadata,
			RequestMappingAnnotationMetadata classMetadata) {
		this.methodMetadata = methodMetadata;
		this.classMetadata = classMetadata;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RequestMappingMethodToClassMap other = (RequestMappingMethodToClassMap) obj;
		if (classMetadata == null) {
			if (other.classMetadata != null)
				return false;
		} else if (!classMetadata.equals(other.classMetadata))
			return false;
		if (methodMetadata == null) {
			if (other.methodMetadata != null)
				return false;
		} else if (!methodMetadata.equals(other.methodMetadata))
			return false;
		return true;
	}

	public RequestMappingAnnotationMetadata getClassMetadata() {
		return classMetadata;
	}

	public RequestMappingMethodAnnotationMetadata getMethodMetadata() {
		return methodMetadata;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((classMetadata == null) ? 0 : classMetadata.hashCode());
		result = prime * result
				+ ((methodMetadata == null) ? 0 : methodMetadata.hashCode());
		return result;
	}

}
