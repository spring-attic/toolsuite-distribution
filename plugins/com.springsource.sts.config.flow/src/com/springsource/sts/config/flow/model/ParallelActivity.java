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
public abstract class ParallelActivity extends StructuredActivity {

	public ParallelActivity() {
		super();
	}

	public ParallelActivity(IDOMElement input, AbstractConfigFlowDiagram diagram) {
		super(input, diagram);
	}

}
