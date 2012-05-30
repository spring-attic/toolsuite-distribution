/******************************************************************************************
 * Copyright (c) 2009 - 2010 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.ui.editors.integration.graph.parts;

import com.springsource.sts.config.flow.parts.ParallelActivityPart;
import com.springsource.sts.config.ui.editors.integration.graph.model.ChainContainerElement;

/**
 * @author Leo Dos Santos
 */
public class ChainContainerEditPart extends ParallelActivityPart {

	public ChainContainerEditPart(ChainContainerElement chain) {
		super(chain);
	}

	@Override
	public ChainContainerElement getModelElement() {
		return (ChainContainerElement) getModel();
	}

}
