/******************************************************************************************
 * Copyright (c) 2009 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.quickfix.ui.tests;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * @author Terry Denney
 */
public class AllQuickfixUiTests {

	public static Test suite() {
		TestSuite suite = new TestSuite(AllQuickfixUiTests.class.getName());
		suite.addTestSuite(ConstructorArgUiTest.class);
		suite.addTestSuite(PropertyAttributeUiTest.class);
		suite.addTestSuite(BeanReferenceAttributeUiTest.class);
		suite.addTestSuite(MethodAttributeUiTest.class);
		suite.addTestSuite(ClassAttributeUiTest.class);
		return suite;
	}

}
