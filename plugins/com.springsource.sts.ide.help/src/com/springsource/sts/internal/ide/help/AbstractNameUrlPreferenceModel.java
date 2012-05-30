/******************************************************************************************
 * Copyright (c) 2012 SpringSource, a division of VMware, Inc. All rights reserved.

 ******************************************************************************************/

/*
 * @author Kaitlin Duck Sherwood
 */
package com.springsource.sts.internal.ide.help;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.regex.Pattern;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.osgi.util.NLS;
import org.osgi.service.prefs.BackingStoreException;

public abstract class AbstractNameUrlPreferenceModel {

	private final IEclipsePreferences store;

	private final IEclipsePreferences defaultStore;

	private String currentString;

	protected abstract IEclipsePreferences getStore();

	protected abstract String getStoreKey();

	protected abstract String getDefaultFilename();

	protected abstract IEclipsePreferences getDefaultStore();

	public AbstractNameUrlPreferenceModel() {
		store = getStore();
		defaultStore = getDefaultStore();

		if (store == null) {
			String title = NLS.bind("Serious error", null);
			String message = NLS
					.bind("This is a serious error: we didn't think this could happen.  Please tell the STS team that there was no preferences store for the Help bundle."
							+ "The issue tracker is at https://issuetracker.springsource.com/browse/STS", null);
			MessageDialog.openError(null, title, message);
		}
		setDefaults();
		currentString = store.get(getStoreKey(), null);
		if (currentString == null) {
			currentString = defaultStore.get(getStoreKey(), "");
		}
	}

	public void setDefaults() {
		InputStream defaultsStream;
		try {
			defaultsStream = FileLocator.openStream(HelpPlugin.getDefault().getBundle(),
					new Path(getDefaultFilename()), false);
			Properties urlProperties = new Properties();
			urlProperties.load(defaultsStream);

			String encodedString = "";
			for (Object key : urlProperties.keySet()) {
				if (key instanceof String && urlProperties.get(key) instanceof String) {
					try {
						encodedString = encodedString
								+ (new NameUrlPair((String) key, urlProperties.getProperty((String) key)))
										.asCombinedString();
					}
					catch (URISyntaxException e) {
						String title = NLS.bind("Malformed URL", null);
						String message = NLS.bind("The {0} is not a legal URL; ignoring.", urlProperties.get(key));
						MessageDialog.openError(null, title, message);
					}
				}
			}
			defaultStore.put(getStoreKey(), encodedString);
		}
		catch (IOException e1) {
			String title = NLS.bind("Could not read defaults", null);
			String message = NLS.bind(
					"Could not read defaults from file {0}; check that the file exists and is readable",
					getDefaultFilename());
			MessageDialog.openError(null, title, message);
		}
	}

	public ArrayList<NameUrlPair> getElements() {
		if (currentString == null) {
			currentString = defaultStore.get(getStoreKey(), "");
		}
		return NameUrlPair.decodeMultipleNameUrlStrings(currentString);
	}

	public String replaceNameUrlPairInEncodedString(NameUrlPair oldNameUrl, NameUrlPair newNameUrl) {
		currentString = currentString.replaceFirst(oldNameUrl.asCombinedString(), newNameUrl.asCombinedString());
		return currentString;
	}

	public String removeNameUrlPairInEncodedString(NameUrlPair oldNameUrl) {
		String combinedString = oldNameUrl.asCombinedString();
		String adjustedString = Pattern.quote(combinedString);

		currentString = currentString.replaceFirst(adjustedString, "");
		return currentString;
	}

	public String addNameUrlPairInEncodedString(NameUrlPair newNameUrl) {
		currentString = currentString + newNameUrl.asCombinedString();
		return currentString;
	}

	public void persist() {
		if (currentString == null) {
			System.err.println("INTERNAL ERROR: currentString should not be null in " + this.getClass());
		}
		else {
			store.put(getStoreKey(), currentString);
		}
	}

	protected void clearNonDefaults() {
		try {
			store.clear();
			currentString = defaultStore.get(getStoreKey(), null);
		}
		catch (BackingStoreException e) {
			System.err.println("INTERNAL ERROR: could not reset the defaults by clearing out the non-defaults" + e);
		}
	}

}
