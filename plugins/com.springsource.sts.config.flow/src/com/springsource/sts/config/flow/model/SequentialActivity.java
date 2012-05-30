/******************************************************************************************
 * Copyright (c) 2009 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.flow.model;

import org.eclipse.wst.xml.core.internal.provisional.document.IDOMElement;

/**
 * @author Leo Dos Santos
 * @author Christian Dupuis
 */
@SuppressWarnings("restriction")
public abstract class SequentialActivity extends StructuredActivity {

	public SequentialActivity() {
		super();
	}

	public SequentialActivity(IDOMElement input, AbstractConfigFlowDiagram diagram) {
		super(input, diagram);
	}

}
