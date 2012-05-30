/******************************************************************************************
 * Copyright (c) 2009 - 2010 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.flow.parts;

import org.eclipse.draw2d.AbstractConnectionAnchor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;

/**
 * @author Leo Dos Santos
 * @author Christian Dupuis
 */
public class LeftAnchor extends AbstractConnectionAnchor {

	private final int offset;

	public LeftAnchor(IFigure source, int offset) {
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
		return r.getTopLeft().translate(0, off);
	}

	@Override
	public Point getReferencePoint() {
		Point ref = getOwner().getBounds().getLeft();
		getOwner().translateToAbsolute(ref);
		return ref;
	}

}
