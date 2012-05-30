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

public class ExampleProjectsPreferenceModel extends AbstractNameUrlPreferenceModel {

	private static ExampleProjectsPreferenceModel instance;

	public static ExampleProjectsPreferenceModel getInstance() {
		if (instance == null) {
			instance = new ExampleProjectsPreferenceModel();
		}
		return instance;
	}

	public final static String DEFAULT_FILENAME = "/defaultExampleUrls.properties";

	public ExampleProjectsPreferenceModel() {
		super();
	}

	@Override
	protected String getStoreKey() {
		return "examples.name.url.key";
	}

	@Override
	protected String getDefaultFilename() {
		return DEFAULT_FILENAME;
	}

	@Override
	protected IEclipsePreferences getStore() {
		return InstanceScope.INSTANCE.getNode(HelpPlugin.PLUGIN_ID);
	}

	@Override
	protected IEclipsePreferences getDefaultStore() {
		return DefaultScope.INSTANCE.getNode(HelpPlugin.PLUGIN_ID);
	}

}
