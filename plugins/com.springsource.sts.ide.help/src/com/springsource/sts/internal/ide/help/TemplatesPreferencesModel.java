/******************************************************************************************
 * Copyright (c) 2012 SpringSource, a division of VMware, Inc. All rights reserved.

 ******************************************************************************************/

/*
 * @author Kaitlin Duck Sherwood
 */
package com.springsource.sts.internal.ide.help;

import org.eclipse.core.runtime.preferences.DefaultScope;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.springsource.ide.eclipse.commons.content.core.ContentManager;
import org.springsource.ide.eclipse.commons.internal.core.CorePlugin;


public class TemplatesPreferencesModel extends AbstractNameUrlPreferenceModel {

	public final static String DEFAULT_FILENAME = "/defaultTemplateUrls.properties";

	private static TemplatesPreferencesModel instance;

	public static TemplatesPreferencesModel getInstance() {
		if (instance == null) {
			instance = new TemplatesPreferencesModel();
		}
		return instance;
	}

	public TemplatesPreferencesModel() {
		super();
	}

	@Override
	protected String getStoreKey() {
		return ContentManager.RESOURCE_CONTENT_DESCRIPTORS;
	}

	@Override
	protected String getDefaultFilename() {
		return DEFAULT_FILENAME;
	}

	@Override
	protected IEclipsePreferences getStore() {
		return InstanceScope.INSTANCE.getNode(CorePlugin.PLUGIN_ID);
	}

	@Override
	protected IEclipsePreferences getDefaultStore() {
		return DefaultScope.INSTANCE.getNode(CorePlugin.PLUGIN_ID);
	}
}
