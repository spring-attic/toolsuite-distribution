/******************************************************************************************
 * Copyright (c) 2009 - 2010 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.tests;

import junit.framework.Test;
import junit.framework.TestSuite;

import com.springsource.sts.config.tests.core.ConfigCoreUtilsTest;
import com.springsource.sts.config.tests.core.contentassist.SpringConfigContentAssistProcessorTest;
import com.springsource.sts.config.tests.flow.AbstractConfigGraphicalEditorTest;
import com.springsource.sts.config.tests.ui.actions.CollapseAndExpandNodeActionTest;
import com.springsource.sts.config.tests.ui.actions.InsertAndDeleteNodeActionTest;
import com.springsource.sts.config.tests.ui.actions.RaiseAndLowerNodeActionTest;
import com.springsource.sts.config.tests.ui.editors.AbstractConfigDetailsPartTest;
import com.springsource.sts.config.tests.ui.editors.AbstractConfigFormPageTest;
import com.springsource.sts.config.tests.ui.editors.AbstractNamespaceDetailsPartTest;
import com.springsource.sts.config.tests.ui.editors.SpringConfigEditorTest;
import com.springsource.sts.config.tests.ui.editors.SpringConfigInputAccessorTest;
import com.springsource.sts.config.tests.ui.editors.namespaces.NamespacesDetailsPartTest;
import com.springsource.sts.config.tests.ui.editors.namespaces.NamespacesMasterPartTest;

/**
 * @author Leo Dos Santos
 * @author Steffen Pingel
 * @author Christian Dupuis
 */
public class AllConfigEditorTests {

	public static Test suite() {
		return suite(false);
	}

	public static Test suite(boolean heartbeat) {
		TestSuite suite = new TestSuite(AllConfigEditorTests.class.getName());
		suite.addTestSuite(ConfigCoreUtilsTest.class);
		suite.addTestSuite(AbstractConfigGraphicalEditorTest.class);
		suite.addTestSuite(CollapseAndExpandNodeActionTest.class);
		suite.addTestSuite(InsertAndDeleteNodeActionTest.class);
		suite.addTestSuite(RaiseAndLowerNodeActionTest.class);
		suite.addTestSuite(AbstractConfigDetailsPartTest.class);
		suite.addTestSuite(AbstractConfigFormPageTest.class);
		suite.addTestSuite(AbstractNamespaceDetailsPartTest.class);
		if (!heartbeat) {
			suite.addTestSuite(SpringConfigEditorTest.class);
		}
		suite.addTestSuite(SpringConfigInputAccessorTest.class);
		suite.addTestSuite(NamespacesDetailsPartTest.class);
		suite.addTestSuite(NamespacesMasterPartTest.class);
		return suite;
	}

	public static Test experimentalSuite() {
		TestSuite suite = new TestSuite(AllConfigEditorTests.class.getName());
		suite.addTestSuite(SpringConfigContentAssistProcessorTest.class);
		return suite;
	}

}
