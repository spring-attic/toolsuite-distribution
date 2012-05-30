/******************************************************************************************
 * Copyright (c) 2010 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.maven.legacy;

import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

import com.springsource.sts.maven.legacy.internal.core.MavenProjectChangeHandler;

/**
 * @author Christian Dupuis
 * @author Andrew Eiseneberg
 */
public class MavenCorePlugin extends AbstractUIPlugin {
	
	private static final String M2ECLIPSE_CLASS = "org.maven.ide.eclipse.MavenPlugin";

	public static final boolean IS_M2ECLIPSE_PRESENT = isPresent(M2ECLIPSE_CLASS);

	private static boolean isPresent(String className) {
		try {
			Class.forName(className);
			return true;
		}
		catch (ClassNotFoundException e) {
			return false;
		}
	}

	// Use the plugin id of the non-legacy plugin so they can share functionality and preferences
	public static final String NON_LEGACY_PLUGIN_ID = "com.springsource.sts.maven"; //$NON-NLS-1$

	// this property is shared with the property from com.springsource.sts.maven
	public static final String AUTOMATICALLY_UPDATE_DEPENDENCIES_KEY = "maven.automatically.update";

	public static final boolean AUTOMATICALLY_UPDATE_DEPENDENCIES_DEFAULT = false;

	public static final String M2ECLIPSE_NATURE = "org.maven.ide.eclipse.maven2Nature";

	private static MavenCorePlugin plugin;

	private IResourceChangeListener resourceChangeListener;
	
	private MavenProjectChangeHandler changeHandler;

	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
	}

	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
		if (resourceChangeListener != null && IS_M2ECLIPSE_PRESENT) {
			ResourcesPlugin.getWorkspace().removeResourceChangeListener(resourceChangeListener);
		}
		if (changeHandler != null && IS_M2ECLIPSE_PRESENT) {
			changeHandler.unregister();
		}
	}

	public static MavenCorePlugin getDefault() {
		return plugin;
	}

}
