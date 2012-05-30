/******************************************************************************************
 * Copyright (c) 2009 - 2011 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.quickfix.tests;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.ui.preferences.ScopedPreferenceStore;

import com.springsource.sts.quickfix.jdt.processors.tests.AutowiredAnnotationQuickAssistProcessorTest;
import com.springsource.sts.quickfix.jdt.processors.tests.ControllerAnnotationQuickAssistProcessorTest;
import com.springsource.sts.quickfix.jdt.processors.tests.PathVariableAnnotationQuickAssistProcessorTest;
import com.springsource.sts.quickfix.jdt.processors.tests.QualifierAnnotationQuickAssistProcessorTest;
import com.springsource.sts.quickfix.processors.tests.AddAutowiredConstructorTest;

/**
 * @author Terry Denney
 * @author Leo Dos Santos
 * @author Christian Dupuis
 * @author Steffen Pingel
 */
public class AllJDTQuickfixTests {

	public static Test suite() {
		IPreferenceStore store = new ScopedPreferenceStore(new InstanceScope(), "org.eclipse.ajdt.ui");
		store.setValue("promptForAutoOpenCrossReference", false);

		TestSuite suite = new TestSuite(AllJDTQuickfixTests.class.getName());
		// $JUnit-BEGIN$

		suite.addTest(new TestSuite(AutowiredAnnotationQuickAssistProcessorTest.class));
		suite.addTest(new TestSuite(QualifierAnnotationQuickAssistProcessorTest.class));
		suite.addTest(new TestSuite(ControllerAnnotationQuickAssistProcessorTest.class));
		suite.addTest(new TestSuite(PathVariableAnnotationQuickAssistProcessorTest.class));
		suite.addTest(new TestSuite(AddAutowiredConstructorTest.class));
		suite.addTest(new TestSuite(ConfigurationLocationProposalComputerTest.class));

		// $JUnit-END$
		return suite;
	}

}
