/******************************************************************************************
 * Copyright (c) 2010 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.roo.test;

import org.springsource.ide.eclipse.commons.frameworks.test.util.UITestCase;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * From the IDE run this suite as an "SWTBot test".
 * @author Kris De Volder
 */
public class AllRooUITests {
	public static Test suite() {
		final TestSuite suite = new TestSuite(AllRooUITests.class.getName());
		addTest(suite, RooShellTests.class);
		suite.addTestSuite(StyledTextAppenderTest.class);
		//Add more...
		return suite;
	}

	private static void addTest(TestSuite suite, Class<? extends UITestCase> test) {
		suite.addTest(UITestCase.createSuite(test));
	}
}
