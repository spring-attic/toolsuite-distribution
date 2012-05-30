/******************************************************************************************
 * Copyright (c) 2009 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.tests;

import junit.framework.Test;
import junit.framework.TestSuite;

import com.springsource.sts.config.tests.flow.parts.ActivityDiagramPartUiTest;
import com.springsource.sts.config.tests.flow.parts.ActivityPartUiTest;
import com.springsource.sts.config.tests.flow.parts.SimpleActivityWithContainerPartUiTest;
import com.springsource.sts.config.tests.flow.parts.StructuredActivityPartUiTest;
import com.springsource.sts.config.tests.flow.parts.TransitionPartUiTest;
import com.springsource.sts.config.tests.ui.editors.AbstractNamespaceDetailsPartUiTest;
import com.springsource.sts.config.tests.ui.editors.AbstractNamespaceMasterPartUiTest;

/**
 * @author Leo Dos Santos
 */
public class AllConfigEditorUiTests {

	public static Test suite() {
		TestSuite suite = new TestSuite(AllConfigEditorUiTests.class.getName());
		suite.addTestSuite(ActivityDiagramPartUiTest.class);
		suite.addTestSuite(ActivityPartUiTest.class);
		suite.addTestSuite(SimpleActivityWithContainerPartUiTest.class);
		suite.addTestSuite(StructuredActivityPartUiTest.class);
		suite.addTestSuite(TransitionPartUiTest.class);
		suite.addTestSuite(AbstractNamespaceDetailsPartUiTest.class);
		suite.addTestSuite(AbstractNamespaceMasterPartUiTest.class);
		return suite;
	}

}
