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
import com.springsource.sts.quickfix.processors.ConstructorArgQuickAssistProcessor;
import com.springsource.sts.quickfix.tests.QuickfixTestUtil;
import com.springsource.sts.quickfix.validator.ClassAttributeValidator;

/**
 * @author Terry Denney
 * @author Leo Dos Santos
 * @author Christian Dupuis
 */
@SuppressWarnings("restriction")
public class AutowireClassAttributeValidationTest extends AbstractBeanValidationTestCase {

	private ClassAttributeValidator classAttrValidator;

	private boolean hasError(String beanName) {
		NodeList children = beansNode.getChildNodes();
		IDOMNode node = QuickfixTestUtil.getNode(BeansSchemaConstants.ELEM_BEAN, beanName, children);

		AttrImpl classAttr = (AttrImpl) node.getAttributes().getNamedItem(BeansSchemaConstants.ATTR_CLASS);

		String className = classAttr.getNodeValue();

		IBeansConfig config = BeansCorePlugin.getModel().getConfig(file);
		Set<IResourceModelElement> contextElements = getContextElements(config);
		for (IResourceModelElement contextElement : contextElements) {
			if (classAttrValidator.validateAttributeWithConfig(config, contextElement, file, classAttr, node, reporter,
					true, validator, className)) {
				return true;
			}
		}
		return false;
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();

		createBeansEditorValidator("src/autowire.xml");
		classAttrValidator = new ClassAttributeValidator();
	}

	@SuppressWarnings("unchecked")
	public void testAutowireWithNoError() {
		assertFalse("Does not expect error", hasError("autowireTest4"));
		assertEquals("Expects no messages", 0, getVisibleMessages(reporter.getMessages()).size());
	}

	@SuppressWarnings("unchecked")
	public void testAutowireWithTooFewConstructorArg() {
		assertFalse("Does not expect error", hasError("autowireTest3"));
		assertEquals("Expects no messages", 0, getVisibleMessages(reporter.getMessages()).size());
	}

	@SuppressWarnings("unchecked")
	public void testNoAutowireWithNoError() {
		assertFalse("Does not expect error", hasError("autowireTest2"));
		assertEquals("Expects no messages", 0, getVisibleMessages(reporter.getMessages()).size());
	}

	@SuppressWarnings("unchecked")
	public void testNoAutowireWithTooFewConstructorArg() {
		assertTrue("Expects error with too few constructor-arg", hasError("autowireTest1"));
		List<IMessage> messages = reporter.getMessages();
		String expectedMessage = "No constructor with 0 arguments defined in class 'com.test.AccountContribution'";
		List<String> visibleMessages = getVisibleMessages(messages);
		assertEquals("Expects 1 message", 1, visibleMessages.size());
		assertEquals(expectedMessage, visibleMessages.get(0));
		assertNotNull("Expects an error message", getErrorMessage(messages));
		assertNotNull("Expects ClassAttributeQuickAssistProcessor to be in reporter", getProcessor(messages,
				ConstructorArgQuickAssistProcessor.class));
	}

}
