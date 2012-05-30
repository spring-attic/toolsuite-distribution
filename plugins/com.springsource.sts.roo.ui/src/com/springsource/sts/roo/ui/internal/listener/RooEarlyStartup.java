/******************************************************************************************
 * Copyright (c) 2010 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.roo.ui.internal.listener;

import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.ui.IStartup;

/**
 * @author Andrew Eisenberg
 * @author Christian Dupuis
 * @since 2.5.1
 */
public class RooEarlyStartup implements IStartup {

	static private RooProjectImportingListener listener;
	
	public void earlyStartup() {
		registerListener();
	}

	public static void registerListener() {
		if (listener == null) {
			listener = new RooProjectImportingListener();
			ResourcesPlugin.getWorkspace().addResourceChangeListener(listener, IResourceChangeEvent.POST_CHANGE);
		}
	}
	
	public static void unregisterListener() {
		ResourcesPlugin.getWorkspace().removeResourceChangeListener(listener);
	}

}
