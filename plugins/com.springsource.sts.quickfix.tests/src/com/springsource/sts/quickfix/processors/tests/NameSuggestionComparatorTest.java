/******************************************************************************************
 * Copyright (c) 2009 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.quickfix.processors.tests;

import junit.framework.TestCase;

import com.springsource.sts.quickfix.processors.NameSuggestionComparator;

/**
 * @author Terry Denney
 * @author Leo Dos Santos
 * @author Christian Dupuis
 */
public class NameSuggestionComparatorTest extends TestCase {

	public void testSimpleNameComparator() {
		String name1 = "com.test.Account";
		String name2 = "com.test.Accounts";
		String name3 = "com.test.Accountsss";
		String name4 = "com.test.Car";
		String name5 = "Account";
		
		NameSuggestionComparator comparator = new NameSuggestionComparator("Account");
		assertEquals("Expects 0 when string are equal", 0, comparator.compare(name1, name1));
		assertTrue(comparator.compare(name1, name2) > 0);
		assertTrue(comparator.compare(name2, name1) < 0);
		assertTrue(comparator.compare(name1, name3) >= 0);
		assertTrue(comparator.compare(name3, name1) <= 0);
		assertTrue(comparator.compare(name2, name3) >= 0);
		assertTrue(comparator.compare(name3, name2) <= 0);
		assertTrue(comparator.compare(name3, name4) > 0);
		assertTrue(comparator.compare(name1, name5) == 0);
	}
	
}
