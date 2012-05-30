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
public class ScaledImageDescriptor extends CompositeImageDescriptor {

	private final ImageData base;

	private final double scale;

	public ScaledImageDescriptor(ImageDescriptor descriptor, double scale) {
		base = getImageData(descriptor);
		this.scale = scale;
	}

	@Override
	protected void drawCompositeImage(int width, int height) {
		drawImage(base.scaledTo(width, height), 0, 0);
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
		int x = (int) (base.width * scale);
		int y = (int) (base.height * scale);
		return new Point(x, y);
	}

}
