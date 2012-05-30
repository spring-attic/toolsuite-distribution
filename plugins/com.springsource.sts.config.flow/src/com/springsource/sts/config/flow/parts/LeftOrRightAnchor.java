/******************************************************************************************
 * Copyright (c) 2011 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.flow.parts;

import org.eclipse.draw2d.AbstractConnectionAnchor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;

/**
 * @author Leo Dos Santos
 */
public class LeftOrRightAnchor extends AbstractConnectionAnchor {

	private final int offset;

	public LeftOrRightAnchor(IFigure source, int offset) {
		super(source);
		this.offset = offset;
	}

	public Point getLocation(Point reference) {
		Rectangle r = getOwner().getBounds().getCopy();
		getOwner().translateToAbsolute(r);
		int off = offset;
		if (off == -1) {
			off = r.height / 2;
		}
		if (r.contains(reference) || r.x < reference.x) {
			return r.getTopRight().translate(-1, off);
		}
		else {
			return r.getTopLeft().translate(0, off);
		}
	}
}
