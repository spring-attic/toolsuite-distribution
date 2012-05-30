/******************************************************************************************
 * Copyright (c) 2010 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.flow.model;

import org.eclipse.gef.requests.CreationFactory;

/**
 * @author Leo Dos Santos
 */
public class TransitionCreationFactory implements CreationFactory {

	public Object getNewObject() {
		return null;
	}

	public Object getObjectType() {
		return Transition.SOLID_CONNECTION;
	}

}
