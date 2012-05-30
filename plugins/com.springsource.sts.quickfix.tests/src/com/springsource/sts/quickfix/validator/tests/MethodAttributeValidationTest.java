/******************************************************************************************
 * Copyright (c) 2009 - 2010 SpringSource, a division of VMware, Inc. All rights reserved.
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
import com.springsource.sts.quickfix.processors.MethodAttributeQuickAssistProcessor;
import com.springsource.sts.quickfix.processors.MethodDeprecatedQuickAssistProcessor;
import com.springsource.sts.quickfix.processors.RenameMethodQuickAssistProcessor;
import com.springsource.sts.quickfix.tests.QuickfixTestUtil;
import com.springsource.sts.quickfix.validator.BeanValidator;
import com.springsource.sts.quickfix.validator.FactoryMethodValidator;
import com.springsource.sts.quickfix.validator.InitDestroyMethodValidator;

/**
 * @author Terry Denney
 * @author Leo Dos Santos
 * @author Christian Dupuis
 */
@SuppressWarnings("restriction")
public class MethodAttributeValidationTest extends AbstractBeanValidationTestCase {

	private FactoryMethodValidator factoryMethodAttrValidator;

	private InitDestroyMethodValidator initDestroyAttrValidator;

	private boolean hasMethodError(String beanName, String attrName, BeanValidator beanValidator) {
		NodeList children = beansNode.getChildNodes();
		IDOMNode beanNode = QuickfixTestUtil.getNode(BeansSchemaConstants.ELEM_BEAN, beanName, children);
		AttrImpl attr = (AttrImpl) beanNode.getAttributes().getNamedItem(attrName);

		IBeansConfig config = BeansCorePlugin.getModel().getConfig(file);
		Set<IResourceModelElement> contextElements = getContextElements(config);
		for (IResourceModelElement contextElement : contextElements) {
			if (beanValidator.validateAttributeWithConfig(config, contextElement, attr, beanNode, reporter, true,
					validator)) {
				return true;
			}
		}
		return false;
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();

		createBeansEditorValidator("src/method-attribute.xml");
		factoryMethodAttrValidator = new FactoryMethodValidator();
		initDestroyAttrValidator = new InitDestroyMethodValidator();
	}

	@SuppressWarnings("unchecked")
	public void testDeprecatedDestroyMethod() {
		assertTrue("Expects error in destroy-method", hasMethodError("deprecatedDestroyMethod",
				BeansSchemaConstants.ATTR_DESTROY_METHOD, initDestroyAttrValidator));
		List<IMessage> messages = reporter.getMessages();
		String expectedMessage = "Method 'deleteAccount' is marked deprecated";
		List<String> visibleMessages = getVisibleMessages(messages);
		assertEquals("Expects 1 message", 1, visibleMessages.size());
		assertEquals(expectedMessage, visibleMessages.get(0));
		assertNotNull("Expects a warning message", getWarningMessage(messages));
		assertNotNull("Expects MethodAttributeQuickAssistProcessor to be in reporter", getProcessor(messages,
				MethodDeprecatedQuickAssistProcessor.class));
		assertNotNull("Expects RenameMethodQuickAssistProcessor to be in reporter", getProcessor(messages,
				RenameMethodQuickAssistProcessor.class));
	}

	@SuppressWarnings("unchecked")
	public void testDeprecatedInitMethod() {
		assertTrue("Expects error in init-method", hasMethodError("deprecatedInitMethod",
				BeansSchemaConstants.ATTR_INIT_METHOD, initDestroyAttrValidator));
		List<IMessage> messages = reporter.getMessages();
		String expectedMessage = "Method 'createAccount' is marked deprecated";
		List<String> visibleMessages = getVisibleMessages(messages);
		assertEquals("Expects 1 message", 1, visibleMessages.size());
		assertEquals(expectedMessage, visibleMessages.get(0));
		assertNotNull("Expects a warning message", getWarningMessage(messages));
		assertNotNull("Expects MethodAttributeQuickAssistProcessor to be in reporter", getProcessor(messages,
				MethodDeprecatedQuickAssistProcessor.class));
		assertNotNull("Expects a RenameMethodQuickAssistProcessor to be in reporter", getProcessor(messages,
				RenameMethodQuickAssistProcessor.class));
	}

	@SuppressWarnings("unchecked")
	public void testDestroyMethodWithError() {
		assertTrue("Expects error in destroy-method", hasMethodError("destroyMethodTest2",
				BeansSchemaConstants.ATTR_DESTROY_METHOD, initDestroyAttrValidator));
		List<IMessage> messages = reporter.getMessages();
		String expectedMessage = "Destroy-method 'disposeAccoun' not found in bean class 'com.test.Account'";
		List<String> visibleMessages = getVisibleMessages(messages);
		assertEquals("Expects 1 message", 1, visibleMessages.size());
		assertEquals(expectedMessage, visibleMessages.get(0));
		assertNotNull("Expects an error message", getErrorMessage(messages));
		assertNotNull("Expects MethodAttributeQuickAssistProcessor to be in reporter", getProcessor(messages,
				MethodAttributeQuickAssistProcessor.class));
		assertNotNull("Expects a RenameMethodQuickAssistProcessor to be in reporter", getProcessor(messages,
				RenameMethodQuickAssistProcessor.class));
	}

	@SuppressWarnings("unchecked")
	public void testDestroyMethodWithNoError() {
		assertFalse("Does not expect error", hasMethodError("destroyMethodTest1",
				BeansSchemaConstants.ATTR_DESTROY_METHOD, initDestroyAttrValidator));
		List<IMessage> messages = reporter.getMessages();
		assertEquals("Expects no messages", 0, getVisibleMessages(messages).size());
		assertNotNull("Expects RenameMethodQuickAssistProcessor to be in reporter", getProcessor(messages,
				RenameMethodQuickAssistProcessor.class));
	}

	@SuppressWarnings("unchecked")
	public void testFactoryMethodWithError() {
		assertTrue("Expects error in factory-method", hasMethodError("factoryMethodTest2",
				BeansSchemaConstants.ATTR_FACTORY_METHOD, factoryMethodAttrValidator));
		List<IMessage> messages = reporter.getMessages();
		String expectedMessage = "Static factory method 'createDefaultAccounts' with 0 arguments not found in factory bean class 'com.test.AccountManager'";
		List<String> visibleMessages = getVisibleMessages(messages);
		assertEquals("Expects 1 message", 1, visibleMessages.size());
		assertEquals(expectedMessage, visibleMessages.get(0));
		assertNotNull("Expects an error message", getErrorMessage(messages));
		assertNotNull("Expects MethodAttributeQuickAssistProcessor to be in reporter", getProcessor(messages,
				MethodAttributeQuickAssistProcessor.class));
		assertNotNull("Expects a RenameMethodQuickAssistProcessor to be in reporter", getProcessor(messages,
				RenameMethodQuickAssistProcessor.class));
	}

	@SuppressWarnings("unchecked")
	public void testFactoryMethodWithNoError() {
		assertFalse("Does not expect error", hasMethodError("factoryMethodTest1",
				BeansSchemaConstants.ATTR_FACTORY_METHOD, factoryMethodAttrValidator));
		assertEquals("Expects no messages", 0, getVisibleMessages(reporter.getMessages()).size());
		List<IMessage> messages = reporter.getMessages();
		assertNotNull("Expects RenameMethodQuickAssistProcessor to be in reporter", getProcessor(messages,
				RenameMethodQuickAssistProcessor.class));
	}

	@SuppressWarnings("unchecked")
	public void testInitMethodWithError() {
		assertTrue("Expects error in init-method", hasMethodError("initMethodTest2",
				BeansSchemaConstants.ATTR_INIT_METHOD, initDestroyAttrValidator));
		List<IMessage> messages = reporter.getMessages();
		String expectedMessage = "Init-method 'initializeAccounts' not found in bean class 'com.test.Account'";
		List<String> visibleMessages = getVisibleMessages(messages);
		assertEquals("Expects 1 message", 1, visibleMessages.size());
		assertEquals(expectedMessage, visibleMessages.get(0));
		assertNotNull("Expects an error message", getErrorMessage(messages));
		assertNotNull("Expects MethodAttributeQuickAssistProcessor to be in reporter", getProcessor(messages,
				MethodAttributeQuickAssistProcessor.class));
		assertNotNull("Expects a RenameMethodQuickAssistProcessor to be in reporter", getProcessor(messages,
				RenameMethodQuickAssistProcessor.class));
	}

	@SuppressWarnings("unchecked")
	public void testInitMethodWithNoError() {
		assertFalse("Does not expect error", hasMethodError("initMethodTest1", BeansSchemaConstants.ATTR_INIT_METHOD,
				initDestroyAttrValidator));
		List<IMessage> messages = reporter.getMessages();
		assertEquals("Expects no messages", 0, getVisibleMessages(messages).size());
		assertNotNull("Expects RenameMethodQuickAssistProcessor to be in reporter", getProcessor(messages,
				RenameMethodQuickAssistProcessor.class));
	}

}
