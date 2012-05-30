/******************************************************************************************
 * Copyright (c) 2012 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.internal.ide.help;

import org.eclipse.osgi.util.NLS;

/**
 * @author Kaitlin Duck Sherwood
 */

// modified from ConfiguratorPreference, with some help from
// RuntimePreferencePage and SpringConfigPreferencePage
public class ExampleProjectsPreferencePage extends AbstractNameUrlPreferencePage {

	public static final String EXAMPLE_PREFERENCES_PAGE_ID = "com.springsource.sts.help.ui.examplepreferencepage";

	public static final String ADD_EDIT_URL_DIALOG_INSTRUCTIONS = NLS.bind(
			"Give the URL to a Github project or a ZIP file on your local filesystem.\n"
					+ "Note that the projects must be Eclipse projects or Maven projects.", null);

	public static final String PREFERENCE_PAGE_HEADER = NLS.bind(
			"Example projects appear on the dashboard, where you can click to import them.\n"
					+ "At the moment, we only download projects from github or your local file system", null);

	@Override
	protected boolean validateUrl(String urlString) {
		// github and local files only
		if (urlString.startsWith("http") && urlString.indexOf("github.com") > 0) {
			return true;
		}

		if (urlString.startsWith("file:")) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	protected String validationErrorMessage(String urlString) {
		return NLS.bind(
				"Sorry, {0} isn't a valid URL.  Right now we only take projects on github or your local filesystem.",
				urlString);
	}

	@Override
	protected String preferencePageHeaderText() {
		return ExampleProjectsPreferencePage.PREFERENCE_PAGE_HEADER;
	}

	@Override
	protected ExampleProjectsPreferenceModel getModel() {
		return ExampleProjectsPreferenceModel.getInstance();
	}

}
