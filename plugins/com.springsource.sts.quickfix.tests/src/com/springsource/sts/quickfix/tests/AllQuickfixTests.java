/******************************************************************************************
 * Copyright (c) 2009 - 2011 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.quickfix.tests;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.ui.preferences.ScopedPreferenceStore;

import com.springsource.sts.quickfix.processors.tests.NameSuggestionComparatorTest;
import com.springsource.sts.quickfix.proposals.tests.AddConfigSetQuickFixProposalTest;
import com.springsource.sts.quickfix.proposals.tests.AddConstructorArgQuickFixProposalTest;
import com.springsource.sts.quickfix.proposals.tests.AddConstructorParamQuickFixProposalTest;
import com.springsource.sts.quickfix.proposals.tests.AddToConfigSetQuickFixProposalTest;
import com.springsource.sts.quickfix.proposals.tests.CreateConstructorQuickFixProposalTest;
import com.springsource.sts.quickfix.proposals.tests.CreateImportQuickFixProposalTest;
import com.springsource.sts.quickfix.proposals.tests.CreateNewBeanQuickFixProposalTest;
import com.springsource.sts.quickfix.proposals.tests.CreateNewClassQuickFixProposalTest;
import com.springsource.sts.quickfix.proposals.tests.CreateNewMethodQuickFixProposalTest;
import com.springsource.sts.quickfix.proposals.tests.QuickfixReflectionUtilsTest;
import com.springsource.sts.quickfix.proposals.tests.RemoveConstructorArgQuickFixProposalTest;
import com.springsource.sts.quickfix.proposals.tests.RemoveConstructorParamQuickFixProposalTest;
import com.springsource.sts.quickfix.proposals.tests.RenamePropertyQuickFixProposalTest;
import com.springsource.sts.quickfix.proposals.tests.RenameToSimilarNameQuickFixProposalTest;
import com.springsource.sts.quickfix.validator.tests.BeanReferenceAttributeValidationTest;
import com.springsource.sts.quickfix.validator.tests.ClassAttributeValidationTest;
import com.springsource.sts.quickfix.validator.tests.ConstructorArgNameValidationTest;
import com.springsource.sts.quickfix.validator.tests.FactoryBeanAttributeValidationTest;
import com.springsource.sts.quickfix.validator.tests.FactoryMethodAttributeValidationTest;
import com.springsource.sts.quickfix.validator.tests.MethodAttributeValidationTest;
import com.springsource.sts.quickfix.validator.tests.NamespaceElementsValidationTest;
import com.springsource.sts.quickfix.validator.tests.PlaceholderTest;
import com.springsource.sts.quickfix.validator.tests.PropertyAttributeValidationTest;

/**
 * @author Terry Denney
 * @author Leo Dos Santos
 * @author Christian Dupuis
 * @author Steffen Pingel
 */
public class AllQuickfixTests {

	public static Test suite() {
		IPreferenceStore store = new ScopedPreferenceStore(new InstanceScope(), "org.eclipse.ajdt.ui");
		store.setValue("promptForAutoOpenCrossReference", false);

		TestSuite suite = new TestSuite(AllQuickfixTests.class.getName());
		// $JUnit-BEGIN$

		suite.addTest(new TestSuite(ReflectionTests.class));
		suite.addTest(new TestSuite(QuickfixReflectionUtilsTest.class));
		suite.addTest(new TestSuite(QuickfixUtilsTest.class));

		// suite.addTest(new
		// TestSuite(AutowireClassAttributeValidationTest.class));
		// TODO: remove since autowire setup is incorrect in test

		// TODO: removed till alias attribute validation is fully implemented
		// suite.addTest(new TestSuite(AliasAttributeValidationTest.class));

		suite.addTest(new TestSuite(FactoryBeanAttributeValidationTest.class));
		suite.addTest(new TestSuite(NamespaceElementsValidationTest.class));
		suite.addTest(new TestSuite(BeanReferenceAttributeValidationTest.class));
		suite.addTest(new TestSuite(ClassAttributeValidationTest.class));
		suite.addTest(new TestSuite(MethodAttributeValidationTest.class));
		suite.addTest(new TestSuite(PlaceholderTest.class));
		suite.addTest(new TestSuite(FactoryMethodAttributeValidationTest.class));
		suite.addTest(new TestSuite(PropertyAttributeValidationTest.class));
		suite.addTest(new TestSuite(ConstructorArgNameValidationTest.class));

		suite.addTest(new TestSuite(AddConstructorArgQuickFixProposalTest.class));
		suite.addTest(new TestSuite(AddConstructorParamQuickFixProposalTest.class));
		suite.addTest(new TestSuite(CreateConstructorQuickFixProposalTest.class));
		suite.addTest(new TestSuite(CreateNewBeanQuickFixProposalTest.class));
		suite.addTest(new TestSuite(CreateNewClassQuickFixProposalTest.class));
		suite.addTest(new TestSuite(CreateNewMethodQuickFixProposalTest.class));
		suite.addTest(new TestSuite(RemoveConstructorArgQuickFixProposalTest.class));
		suite.addTest(new TestSuite(RemoveConstructorParamQuickFixProposalTest.class));
		suite.addTest(new TestSuite(RenamePropertyQuickFixProposalTest.class));
		suite.addTest(new TestSuite(RenameToSimilarNameQuickFixProposalTest.class));
		suite.addTest(new TestSuite(CreateImportQuickFixProposalTest.class));
		suite.addTest(new TestSuite(AddToConfigSetQuickFixProposalTest.class));
		suite.addTest(new TestSuite(AddConfigSetQuickFixProposalTest.class));

		suite.addTest(new TestSuite(NameSuggestionComparatorTest.class));

		suite.addTest(AllJDTQuickfixTests.suite());

		// $JUnit-END$
		return suite;
	}

}
