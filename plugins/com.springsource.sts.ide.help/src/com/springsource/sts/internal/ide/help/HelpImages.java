/******************************************************************************************
 * Copyright (c) 2008 - 2009 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.internal.ide.help;

import java.net.MalformedURLException;
import java.net.URL;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.swt.graphics.Image;

/**
 * @author Steffen Pingel
 * @author Wesley Coelho
 * @author Leo Dos Santos
 * @author Christian Dupuis
 */
public class HelpImages {

	private static ImageRegistry imageRegistry;

	private static final String T_ETOOL = "etool16";

	private static final String T_OBJ = "obj16";

	private static final String T_WIZBAN = "wizban";

	private static final URL baseURL = HelpPlugin.getDefault().getBundle().getEntry("/icons/");

	public static final ImageDescriptor BROWSER = create(T_OBJ, "browser-small.gif");

	public static final ImageDescriptor RESTART_STEP = create(T_ETOOL, "restart-step.gif");

	public static final ImageDescriptor TUTORIAL = create(T_OBJ, "tutorial.png");

	public static final ImageDescriptor TUTORIAL_CATEGORY = create(T_OBJ, "folder_obj.gif");

	public static final ImageDescriptor TUTORIAL_WIZBAN = create(T_WIZBAN, "wizban-tutorial.png");

	public static final ImageDescriptor IMPORT_WIZBAN = create(T_WIZBAN, "wizban-import-project.png");

	private static ImageDescriptor create(String prefix, String name) {
		try {
			return ImageDescriptor.createFromURL(makeIconFileURL(prefix, name));
		}
		catch (MalformedURLException e) {
			return ImageDescriptor.getMissingImageDescriptor();
		}
	}

	public static Image getImage(ImageDescriptor imageDescriptor) {
		ImageRegistry imageRegistry = getImageRegistry();
		Image image = imageRegistry.get("" + imageDescriptor.hashCode());
		if (image == null) {
			image = imageDescriptor.createImage(true);
			imageRegistry.put("" + imageDescriptor.hashCode(), image);
		}
		return image;
	}

	private static ImageRegistry getImageRegistry() {
		if (imageRegistry == null) {
			imageRegistry = new ImageRegistry();
		}

		return imageRegistry;
	}

	private static URL makeIconFileURL(String prefix, String name) throws MalformedURLException {
		if (baseURL == null) {
			throw new MalformedURLException();
		}

		StringBuffer buffer = new StringBuffer(prefix);
		buffer.append('/');
		buffer.append(name);
		return new URL(baseURL, buffer.toString());
	}

}
