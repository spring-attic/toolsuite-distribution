/******************************************************************************************
 * Copyright (c) 2008 - 2009 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.quickfix;

import org.eclipse.wst.sse.ui.internal.reconcile.validator.AnnotationInfo;
import org.eclipse.wst.validation.internal.provisional.core.IMessage;

/**
 * Annotation info for beans editor quick fix
 * @author Terry Denney
 * @author Leo Dos Santos
 * @author Christian Dupuis
 * @since 2.0
 */
public class QuickfixAnnotationInfo extends AnnotationInfo {

	public QuickfixAnnotationInfo(IMessage message) {
		super(message);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof QuickfixAnnotationInfo) {
			QuickfixAnnotationInfo info = (QuickfixAnnotationInfo) obj;
			return info.getMessage().equals(getMessage());
		}
		return super.equals(obj);
	}

	@Override
	public int hashCode() {
		return getMessage().hashCode();
	}

}
