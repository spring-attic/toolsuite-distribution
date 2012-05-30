/******************************************************************************************
 * Copyright (c) 2008 - 2009 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.core;

import org.eclipse.core.runtime.Plugin;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle
 * @author Leo Dos Santos
 * @author Christian Dupuis
 */
public class ConfigCorePlugin extends Plugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "com.springsource.sts.config.core"; //$NON-NLS-1$

	// The shared instance
	private static ConfigCorePlugin plugin;
	
	/**
	 * The constructor
	 */
	public ConfigCorePlugin() {
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.core.runtime.Plugins#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.core.runtime.Plugin#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 *
	 * @return the shared instance
	 */
	public static ConfigCorePlugin getDefault() {
		return plugin;
	}

}
