/******************************************************************************************
 * Copyright (c) 2012 SpringSource, a division of VMware, Inc. All rights reserved.

 ******************************************************************************************/

/*
 * @author Kaitlin Duck Sherwood
 */

// @@@ probably don't need this class!
package com.springsource.sts.internal.ide.help;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Properties;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

public class ExampleProjectsContentProvider implements IStructuredContentProvider {

	Properties properties;

	protected ExampleProjectsContentProvider(Properties someProperties) {
		this.properties = someProperties;
	}

	public void dispose() {
		// do nothing @@@
	}

	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		// do nothing, input won't change
	}

	public Object[] getElements(Object inputElement) {
		ArrayList<NameUrlPair> nameUrlPairs = new ArrayList<NameUrlPair>();
		for (Object key : this.properties.keySet()) {
			if (key instanceof String) {
				if (properties.get(key) instanceof String) {
					String urlString = (String) properties.get(key);
					try {
						NameUrlPair nameUrlPair;
						nameUrlPair = new NameUrlPair((String) key, urlString);
						nameUrlPairs.add(nameUrlPair);
					}
					catch (URISyntaxException e) {
						// don't show any broken URLs
					}
				}
			}
		}
		return nameUrlPairs.toArray();

	}
}
