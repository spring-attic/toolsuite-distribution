/******************************************************************************************
 * Copyright (c) 2011 SpringSource, a division of VMware, Inc. All rights reserved.
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
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.springsource.sts.config.core.schemas.BeansSchemaConstants;
import com.springsource.sts.quickfix.processors.ConstructorArgNameQuickAssistProcessor;
import com.springsource.sts.quickfix.tests.QuickfixTestUtil;
import com.springsource.sts.quickfix.validator.ConstructorArgNameValidator;

/**
 * @author Terry Denney
 */
@SuppressWarnings("restriction")
public class ConstructorArgNameValidationTest extends AbstractBeanValidationTestCase {

	private ConstructorArgNameValidator constructorArgNameValidator;

	private boolean hasError(String beanName, String constructorArgName) {
		NodeList children = beansNode.getChildNodes();
		IDOMNode node = QuickfixTestUtil.getNode(BeansSchemaConstants.ELEM_BEAN, beanName, children);

		NodeList beanChildren = node.getChildNodes();
		for (int i = 0; i < beanChildren.getLength(); i++) {
			Node beanChild = beanChildren.item(i);
			String localName = beanChild.getLocalName();
			if (BeansSchemaConstants.ELEM_CONSTRUCTOR_ARG.equals(localName)) {
				NamedNodeMap attrs = beanChild.getAttributes();
				Node attr = attrs.getNamedItem(BeansSchemaConstants.ATTR_NAME);
				if (attr != null && constructorArgName.equals(attr.getNodeValue())) {
					IBeansConfig config = BeansCorePlugin.getModel().getConfig(file);
					Set<IResourceModelElement> contextElements = getContextElements(config);
					for (IResourceModelElement contextElement : contextElements) {
						if (constructorArgNameValidator.validateAttributeWithConfig(config, contextElement,
								(AttrImpl) attr, (IDOMNode) beanChild, reporter, true, validator)) {
							return true;
						}
					}
				}
			}
		}

		return false;
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();

		createBeansEditorValidator("src/constructor-arg-name.xml");
		constructorArgNameValidator = new ConstructorArgNameValidator();
	}

	@SuppressWarnings("unchecked")
	public void testConstructorArgNameFound() {
		assertFalse("Does not expect error", hasError("constructorArgNameTest1", "foo"));
		assertEquals("Expects no messages", 0, getVisibleMessages(reporter.getMessages()).size());
	}

	@SuppressWarnings("unchecked")
	public void testConstructorArgNameNotFound() {
		assertTrue("Expects error no constructor parameter name error", hasError("constructorArgNameTest2", "bar"));
		List<IMessage> messages = reporter.getMessages();
		String expectedMessage = "Cannot find constructor parameter with name 'bar'";
		List<String> visibleMessages = getVisibleMessages(messages);
		assertEquals("Expects 1 message", 1, visibleMessages.size());
		assertEquals(expectedMessage, visibleMessages.get(0));
		assertNotNull("Expects an error message", getWarningMessage(messages));
		assertNotNull("Expects ConstructorArgNameQuickAssistProcessor to be in reporter",
				getProcessor(messages, ConstructorArgNameQuickAssistProcessor.class));
	}

}
