/******************************************************************************************
 * Copyright (c) 2009 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.ui.editors.batch.graph.parts;

import com.springsource.sts.config.flow.parts.SequentialActivityPart;
import com.springsource.sts.config.ui.editors.batch.graph.model.FlowModelElement;

/**
 * @author Leo Dos Santos
 * @author Christian Dupuis
 */
public class FlowGraphicalEditPart extends SequentialActivityPart {

	public FlowGraphicalEditPart(FlowModelElement flow) {
		super(flow);
	}

	@Override
	public FlowModelElement getModelElement() {
		return (FlowModelElement) getModel();
	}

}
