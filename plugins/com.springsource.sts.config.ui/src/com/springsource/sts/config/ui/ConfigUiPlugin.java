/******************************************************************************************
 * Copyright (c) 2008 - 2011 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.ui;

import org.eclipse.jface.dialogs.MessageDialogWithToggle;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

import com.springsource.sts.config.core.preferences.SpringConfigPreferenceConstants;

/**
 * The activator class controls the plug-in life cycle
 * @author Leo Dos Santos
 * @author Christian Dupuis
 */
public class ConfigUiPlugin extends AbstractUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "com.springsource.sts.config.ui"; //$NON-NLS-1$

	// The shared instance
	private static ConfigUiPlugin plugin;

	/**
	 * Returns the shared instance
	 * 
	 * @return the shared instance
	 */
	public static ConfigUiPlugin getDefault() {
		return plugin;
	}

	/**
	 * Returns an image descriptor for the image file at the given plug-in
	 * relative path
	 * 
	 * @param path the path
	 * @return the image descriptor
	 */
	public static ImageDescriptor getImageDescriptor(String path) {
		return imageDescriptorFromPlugin(PLUGIN_ID, path);
	}

	/**
	 * The constructor
	 */
	public ConfigUiPlugin() {
	}

	@Override
	protected void initializeDefaultPreferences(IPreferenceStore store) {
		store.setDefault(SpringConfigPreferenceConstants.PREF_DISPLAY_TABS_DIALOG, MessageDialogWithToggle.PROMPT);
		store.setDefault(SpringConfigPreferenceConstants.PREF_ENABLE_GEF_PAGES, true);
		store.setDefault(SpringConfigPreferenceConstants.PREF_DISPLAY_ACTION_STATE, true);
		store.setDefault(SpringConfigPreferenceConstants.PREF_DISPLAY_DECISION_STATE, true);
		store.setDefault(SpringConfigPreferenceConstants.PREF_DISPLAY_END_STATE, true);
		store.setDefault(SpringConfigPreferenceConstants.PREF_DISPLAY_SUBFLOW_STATE, true);
		store.setDefault(SpringConfigPreferenceConstants.PREF_DISPLAY_VIEW_STATE, true);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext
	 * )
	 */
	@Override
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext
	 * )
	 */
	@Override
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}
}
