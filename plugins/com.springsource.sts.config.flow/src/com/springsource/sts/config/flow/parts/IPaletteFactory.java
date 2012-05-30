/******************************************************************************************
 * Copyright (c) 2010 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.flow.parts;

import org.eclipse.gef.palette.PaletteDrawer;

import com.springsource.sts.config.flow.model.AbstractConfigFlowDiagram;

/**
 * @author Leo Dos Santos
 */
public interface IPaletteFactory {

	public PaletteDrawer createPaletteDrawer(AbstractConfigFlowDiagram diagram, String namespaceUri);

}
