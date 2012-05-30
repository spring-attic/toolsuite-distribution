/******************************************************************************************
 * Copyright (c) 2010 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.ui.editors.integration.graph.parts;

import org.eclipse.gef.requests.CreationFactory;

import com.springsource.sts.config.ui.editors.integration.graph.model.AlternateTransition;

/**
 * @author Leo Dos Santos
 */
public class AlternateTransitionCreationFactory implements CreationFactory {

	public Object getNewObject() {
		return null;
	}

	public Object getObjectType() {
		return AlternateTransition.class;
	}

}
