/******************************************************************************************
 * Copyright (c) 2009 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.flow.parts;

import org.eclipse.draw2d.AbstractLayout;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;

/**
 * The dummy layout class does nothing during normal layouts. The Graph layout
 * is entirely performed in one place: {@link GraphLayoutManager}, on the
 * diagram's figure. During animation, THIS layout will playback the
 * intermediate steps between the two invocations of the graph layout.
 * @author Leo Dos Santos
 * @author Christian Dupuis
 */
public class DummyLayout extends AbstractLayout {

	/**
	 * @see org.eclipse.draw2d.AbstractLayout#calculatePreferredSize(org.eclipse.draw2d.IFigure,
	 * int, int)
	 */
	@Override
	protected Dimension calculatePreferredSize(IFigure container, int wHint, int hHint) {
		return null;
	}

	/**
	 * @see org.eclipse.draw2d.LayoutManager#layout(org.eclipse.draw2d.IFigure)
	 */
	public void layout(IFigure container) {
		// GraphAnimation.recordInitialState(container);
		GraphAnimation.playbackState(container);
	}

}
