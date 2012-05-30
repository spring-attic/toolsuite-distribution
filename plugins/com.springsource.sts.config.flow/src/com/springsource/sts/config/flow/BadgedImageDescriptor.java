/******************************************************************************************
 * Copyright (c) 2010 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.flow;

import org.eclipse.jface.resource.CompositeImageDescriptor;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.Point;

/**
 * @author Leo Dos Santos
 */
public class BadgedImageDescriptor extends CompositeImageDescriptor {

	private final ImageData base;

	private final ImageData badge;

	private final boolean top;

	private final boolean left;

	public BadgedImageDescriptor(ImageDescriptor descriptor, ImageDescriptor badgeDescriptor, boolean top, boolean left) {
		this.top = top;
		this.left = left;
		base = getImageData(descriptor);
		badge = getImageData(badgeDescriptor);
	}

	@Override
	protected void drawCompositeImage(int width, int height) {
		drawImage(base, 0, 0);
		int x = 0;
		int y = 0;
		if (!left) {
			x = base.width - badge.width;
		}
		if (!top) {
			y = base.height - badge.height;
		}
		drawImage(badge, x, y);
	}

	private ImageData getImageData(ImageDescriptor descriptor) {
		ImageData data = descriptor.getImageData();
		// see bug 51965: getImageData can return null
		if (data == null) {
			data = DEFAULT_IMAGE_DATA;
		}
		return data;
	}

	@Override
	protected Point getSize() {
		return new Point(base.width, base.height);
	}

}
