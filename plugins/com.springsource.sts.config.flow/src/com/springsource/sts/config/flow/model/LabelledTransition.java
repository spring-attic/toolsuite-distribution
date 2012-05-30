/******************************************************************************************
 * Copyright (c) 2011 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.flow.model;

import org.eclipse.wst.xml.core.internal.provisional.document.IDOMElement;

/**
 * @author Leo Dos Santos
 */
@SuppressWarnings("restriction")
public abstract class LabelledTransition extends Transition {

	public LabelledTransition(Activity source, Activity target, IDOMElement input) {
		super(source, target, input);
	}

	@Override
	public IDOMElement getInput() {
		return (IDOMElement) super.getInput();
	}

	public abstract String getLabel();

}
