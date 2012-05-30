/******************************************************************************************
 * Copyright (c) 2009 - 2010 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.roo.ui;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

import com.springsource.sts.roo.ui.internal.listener.RooEarlyStartup;

/**
 * The activator class controls the plug-in life cycle
 * @author Christian Dupuis
 * @author Andrew Eisenberg
 */
public class RooUiActivator extends AbstractUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "com.springsource.sts.roo.ui";

	// The shared instance
	private static RooUiActivator plugin;
	

	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
		
		// should already have been called by early
		// start up, but this may have been disabled
		// by user
		RooEarlyStartup.registerListener();
	}

	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
		RooEarlyStartup.unregisterListener();
	}

	public static RooUiActivator getDefault() {
		return plugin;
	}
	
	public static ImageDescriptor getImageDescriptor(String path) {
		return imageDescriptorFromPlugin(PLUGIN_ID, path);
	}
	
}