/******************************************************************************************
 * Copyright (c) 2008 - 2009 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.quickfix.processors;

import java.util.Comparator;

import org.eclipse.jdt.internal.ui.text.correction.NameMatcher;

/**
 * String comparator for sorting a list of suggested names. Sort by most
 * relevant to least relevant.
 * @author Terry Denney
 * @author Leo Dos Santos
 * @author Christian Dupuis
 */
public class NameSuggestionComparator implements Comparator<String> {

	private final String toMatch;

	public NameSuggestionComparator(String toMatch) {
		this.toMatch = toMatch;
	}

	public int compare(String fullName1, String fullName2) {
		return getSimilarity(fullName1) - getSimilarity(fullName2);
	}

	private int getSimilarity(String fullName) {
		String name;
		int pos = fullName.lastIndexOf(".");
		if (pos < 0) {
			name = fullName;
		}
		else {
			name = fullName.substring(pos + 1);
		}

		if (name.equals(toMatch)) {
			return 300;
		}
		return NameMatcher.getSimilarity(toMatch, name);
	}

}
