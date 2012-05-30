package com.springsource.sts.internal.ide.help;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerSorter;

class NameUrlViewerSorter extends ViewerSorter {
	// sortByName true to sort by name, false to
	// sort by URL
	boolean sortByName;

	public NameUrlViewerSorter(boolean sortByName) {
		this.sortByName = sortByName;
	}

	@SuppressWarnings("unchecked")
	@Override
	public int compare(Viewer viewer, Object e1, Object e2) {
		NameUrlPair nameUrlPair1 = (NameUrlPair) e1;
		NameUrlPair nameUrlPair2 = (NameUrlPair) e2;
		if (sortByName) {
			return getComparator().compare(notNull(nameUrlPair1.getName()), notNull(nameUrlPair2.getName()));
		}

		if (nameUrlPair1.getUrlString() == null) {
			return -1;
		}
		if (nameUrlPair2.getUrlString() == null) {
			return 1;
		}
		return getComparator().compare(notNull(nameUrlPair1.getUrlString()), notNull(nameUrlPair2.getUrlString()));
	}

	protected String notNull(String s) {
		if (s == null) {
			return "";
		}
		return s;
	}
}