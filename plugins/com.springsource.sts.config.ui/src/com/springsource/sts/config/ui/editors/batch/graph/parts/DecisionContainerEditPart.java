/******************************************************************************************
 * Copyright (c) 2009 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.ui.editors.batch.graph.parts;

import com.springsource.sts.config.flow.parts.ParallelActivityPart;
import com.springsource.sts.config.ui.editors.batch.graph.model.DecisionContainerElement;

/**
 * @author Leo Dos Santos
 * @author Christian Dupuis
 */
public class DecisionContainerEditPart extends ParallelActivityPart {

	public DecisionContainerEditPart(DecisionContainerElement decision) {
		super(decision);
	}

	@Override
	public DecisionContainerElement getModelElement() {
		return (DecisionContainerElement) getModel();
	}

}
