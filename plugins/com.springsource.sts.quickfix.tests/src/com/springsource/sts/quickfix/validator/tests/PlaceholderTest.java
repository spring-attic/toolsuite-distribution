/******************************************************************************************
 * Copyright (c) 2009 - 2010 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.quickfix.validator.tests;

import java.util.List;
import java.util.Set;

import org.eclipse.wst.validation.internal.provisional.core.IMessage;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMNode;
import org.springframework.ide.eclipse.beans.core.BeansCorePlugin;
import org.springframework.ide.eclipse.beans.core.model.IBeansConfig;
import org.springframework.ide.eclipse.core.model.IResourceModelElement;

import com.springsource.sts.config.core.schemas.BeansSchemaConstants;
import com.springsource.sts.quickfix.processors.ClassAttributeQuickAssistProcessor;
import com.springsource.sts.quickfix.processors.ConstructorArgQuickAssistProcessor;
import com.springsource.sts.quickfix.processors.RenamePropertyQuickAssistProcessor;
import com.springsource.sts.quickfix.tests.QuickfixTestUtil;
import com.springsource.sts.quickfix.validator.BeanValidatorVisitor;

/**
 * @author Terry Denney
 * @author Leo Dos Santos
 * @author Christian Dupuis
 */
@SuppressWarnings("restriction")
public class PlaceholderTest extends AbstractBeanValidationTestCase {

	@Override
	protected void setUp() throws Exception {
		super.setUp();

		createBeansEditorValidator("src/placeholder.xml");
	}

	public void testClass() {
		validate("placeholderTest1");
		assertTrue("Expects no messages", reporter.getMessages().isEmpty());
	}

	@SuppressWarnings({ "unchecked" })
	public void testNestedClass() {
		validate("placeholderTest3");
		assertNull("Expects no messages", getErrorMessage(reporter.getMessages()));

		validate("placeholderTest4");
		List<IMessage> messages = reporter.getMessages();
		String expectedMessage = "No constructor with 0 arguments defined in class 'com.test.AccountContribution'";
		List<String> visibleMessages = getVisibleMessages(messages);
		assertEquals("Expects 1 message", 1, visibleMessages.size());
		assertEquals(expectedMessage, visibleMessages.get(0));
		assertNotNull("Expects an error message", getErrorMessage(messages));

		assertNotNull("Expects ClassAttributeQuickAssistProcessor to be in reporter",
				getProcessor(messages, ConstructorArgQuickAssistProcessor.class));
		// assertNotNull("Expects a RenamePropertyQuickAssistProcessor to be in reporter",
		// getProcessor(messages,
		// RenamePropertyQuickAssistProcessor.class));

	}

	@SuppressWarnings({ "unchecked" })
	public void testNoPlaceholder() {
		validate("placeholderTest5");
		List<IMessage> messages = reporter.getMessages();
		String expectedMessage = "Class 'batch.database.incrementer.class' not found";
		List<String> visibleMessages = getVisibleMessages(messages);
		assertEquals("Expects 1 message", 1, visibleMessages.size());
		assertEquals(expectedMessage, visibleMessages.get(0));
		assertNotNull("Expects an error message", getErrorMessage(messages));
		assertNotNull("Expects ClassAttributeQuickAssistProcessor to be in reporter",
				getProcessor(messages, ClassAttributeQuickAssistProcessor.class));
	}

	@SuppressWarnings("unchecked")
	public void testProperty() {
		validate("placeholderTest2");
		List<IMessage> messages = reporter.getMessages();
		assertEquals("Expects no messages", 0, getVisibleMessages(messages).size());
		assertNotNull("Expects RenamePropertyQuickAssistProcessor to be in reporter",
				getProcessor(messages, RenamePropertyQuickAssistProcessor.class));
	}

	private void validate(String beanName) {
		IDOMNode node = QuickfixTestUtil.getNode(BeansSchemaConstants.ELEM_BEAN, beanName, beansNode.getChildNodes());
		IBeansConfig config = BeansCorePlugin.getModel().getConfig(file);
		Set<IResourceModelElement> contextElements = getContextElements(config);
		for (IResourceModelElement contextElement : contextElements) {
			BeanValidatorVisitor visitor = new BeanValidatorVisitor(config, contextElement, reporter, validator);
			visitor.visitNode(node, true, true);
		}
	}

}
