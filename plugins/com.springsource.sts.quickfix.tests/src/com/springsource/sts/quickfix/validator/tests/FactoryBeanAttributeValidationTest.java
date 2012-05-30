/******************************************************************************************
 * Copyright (c) 2010 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.quickfix.validator.tests;

import java.util.List;
import java.util.Set;

import org.eclipse.wst.validation.internal.provisional.core.IMessage;
import org.eclipse.wst.xml.core.internal.document.AttrImpl;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMNode;
import org.springframework.ide.eclipse.beans.core.BeansCorePlugin;
import org.springframework.ide.eclipse.beans.core.model.IBeansConfig;
import org.springframework.ide.eclipse.core.model.IResourceModelElement;
import org.w3c.dom.NodeList;

import com.springsource.sts.config.core.schemas.BeansSchemaConstants;
import com.springsource.sts.quickfix.processors.BeanReferenceQuickAssistProcessor;
import com.springsource.sts.quickfix.processors.MissingFactoryMethodAttributeQuickAssistProcessor;
import com.springsource.sts.quickfix.tests.QuickfixTestUtil;
import com.springsource.sts.quickfix.validator.FactoryBeanValidator;

/**
 * @author Terry Denney
 */
@SuppressWarnings("restriction")
public class FactoryBeanAttributeValidationTest extends AbstractBeanValidationTestCase {

	private FactoryBeanValidator factoryBeanAttrValidator;

	private boolean hasError(String beanName) {
		NodeList children = beansNode.getChildNodes();
		IDOMNode beanNode = QuickfixTestUtil.getNode(BeansSchemaConstants.ELEM_BEAN, beanName, children);
		AttrImpl attr = (AttrImpl) beanNode.getAttributes().getNamedItem(BeansSchemaConstants.ATTR_FACTORY_BEAN);

		IBeansConfig config = BeansCorePlugin.getModel().getConfig(file);
		Set<IResourceModelElement> contextElements = getContextElements(config);
		for (IResourceModelElement contextElement : contextElements) {
			if (factoryBeanAttrValidator.validateAttributeWithConfig(config, contextElement, attr, beanNode, reporter,
					true, validator)) {
				return true;
			}
		}
		return false;
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();

		createBeansEditorValidator("src/factory-bean-test.xml");
		factoryBeanAttrValidator = new FactoryBeanValidator();
	}

	@SuppressWarnings("unchecked")
	public void testInvalidBean() {
		assertTrue("Expects error", hasError("invalidBean"));
		List<IMessage> messages = reporter.getMessages();
		String expectedMessage = "Referenced factory bean 'foo' is invalid (abstract or no bean class)";
		List<String> visibleMessages = getVisibleMessages(messages);
		assertEquals("Expects 1 message", 1, visibleMessages.size());
		assertEquals(expectedMessage, visibleMessages.get(0));
		assertNotNull("Expects an error message", getErrorMessage(messages));
	}

	@SuppressWarnings("unchecked")
	public void testNoError() {
		assertFalse("Expects no error", hasError("correctBean"));
		assertEquals("Expects no messages", 0, getVisibleMessages(reporter.getMessages()).size());
	}

	@SuppressWarnings("unchecked")
	public void testNoFactoryMethod() {
		assertTrue("Expects error", hasError("noFactoryMethod"));
		List<IMessage> messages = reporter.getMessages();
		String expectedMessage = "A factory bean requires a factory method";
		List<String> visibleMessages = getVisibleMessages(messages);
		assertEquals("Expects 1 message", 1, visibleMessages.size());
		assertEquals(expectedMessage, visibleMessages.get(0));
		assertNotNull("Expects an error message", getErrorMessage(messages));
		assertNotNull("Expects MissingFactoryMethodAttributeQuickAssistProcessor to be in reporter",
				getProcessor(messages, MissingFactoryMethodAttributeQuickAssistProcessor.class));
	}

	@SuppressWarnings("unchecked")
	public void testUnknownFactoryBean() {
		assertTrue("Expects error", hasError("unknownFactoryBean"));
		List<IMessage> messages = reporter.getMessages();
		String expectedMessage = "Factory bean 'no_such_bean' not found";
		List<String> visibleMessages = getVisibleMessages(messages);
		assertEquals("Expects 1 message", 1, visibleMessages.size());
		assertEquals(expectedMessage, visibleMessages.get(0));
		assertNotNull("Expects an error message", getErrorMessage(messages));
		assertNotNull("Expects BeanReferenceQuickAssistProcessor to be in reporter",
				getProcessor(messages, BeanReferenceQuickAssistProcessor.class));
	}

}
