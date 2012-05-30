/******************************************************************************************
 * Copyright (c) 2012 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.internal.ide.help;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.osgi.util.NLS;
import org.springsource.ide.eclipse.commons.content.core.ContentManager;
import org.springsource.ide.eclipse.commons.content.core.ContentPlugin;

/**
 * @author Kaitlin Duck Sherwood
 */

// modified from ExampleProjectsPreferences, with some help from
// RuntimePreferencePage and SpringConfigPreferencePage
public class TemplatesPreferencePage extends AbstractNameUrlPreferencePage {
	public static final String EXAMPLE_PREFERENCES_PAGE_ID = "com.springsource.sts.help.ui.templatepreferencepage";

	public static final String URL_SUFFIX = "/descriptions.xml";

	public static final String PREFERENCE_PAGE_HEADER = NLS.bind(
			"You can reach templates by selecting New->Templates.\n", null);

	public static final String ADD_EDIT_URL_DIALOG_INSTRUCTIONS = NLS.bind("Give the URL to a template.\n"
			+ "Note that templates require special packaging", null);

	@Override
	protected boolean validateUrl(String urlString) {
		if (urlString.startsWith("http")) {
			return true;
		}

		// if (urlString.startsWith("file:")) {
		// return true;
		// }
		else {
			return false;
		}
	}

	@Override
	protected String preferencePageHeaderText() {
		return PREFERENCE_PAGE_HEADER;
	}

	@Override
	// TODO: mildly useful to make a singleton, perhaps
	protected TemplatesPreferencesModel getModel() {
		return TemplatesPreferencesModel.getInstance();
	}

	@Override
	protected String validationErrorMessage(String urlString) {
		return NLS.bind("Sorry, {0} isn't a valid URL.  Right now we only take HTTP or HTTPS URLs.", urlString);
	}

	@Override
	public boolean performOk() {
		boolean okay = super.performOk();
		updateDescriptorsInBackground();
		return okay;
	}

	@Override
	public void performApply() {
		updateDescriptorsInBackground();
	}

	private void updateDescriptorsInBackground() {

		Job job = new Job("Refreshing template projects") {
			@Override
			protected IStatus run(IProgressMonitor monitor) {
				final ContentManager defaultContentManager = ContentPlugin.getDefault().getManager();
				defaultContentManager.refresh(monitor);
				return Status.OK_STATUS;
			}
		};

		// Start the Job
		job.schedule();

	}
}
