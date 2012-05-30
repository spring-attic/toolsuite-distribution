/******************************************************************************************
 * Copyright (c) 2010 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.ui.editors.integration.graph.model;

import org.eclipse.draw2d.Graphics;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMNode;

import com.springsource.sts.config.flow.model.Activity;
import com.springsource.sts.config.flow.model.Transition;

/**
 * @author Leo Dos Santos
 */
@SuppressWarnings("restriction")
public class ImplicitTransition extends Transition {

	public ImplicitTransition(Activity source, Activity target, IDOMNode input) {
		super(source, target, input, Graphics.LINE_DOT);
	}

}
