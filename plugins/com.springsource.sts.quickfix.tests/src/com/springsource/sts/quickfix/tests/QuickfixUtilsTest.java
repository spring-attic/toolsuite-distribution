/******************************************************************************************
 * Copyright (c) 2009 - 2010 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.quickfix.tests;

import org.eclipse.wst.xml.core.internal.provisional.document.IDOMNode;

import com.springsource.sts.config.core.schemas.BeansSchemaConstants;
import com.springsource.sts.quickfix.QuickfixUtils;
import com.springsource.sts.quickfix.proposals.tests.AbstractBeanFileQuickfixTestCase;

/**
 * Tests method of QuickfixUtils
 * @author Terry Denney
 * @author Leo Dos Santos
 * @author Christian Dupuis
 */
@SuppressWarnings("restriction")
public class QuickfixUtilsTest extends AbstractBeanFileQuickfixTestCase {

	@Override
	protected void setUp() throws Exception {
		super.setUp();

		openBeanEditor("src/quickfix-util.xml");
	}

	public void testGetEnclosingBeanNode() {
		assertNull("Does not expect to have enclosing bean node", QuickfixUtils
				.getEnclosingBeanNode((IDOMNode) beansNode));

		IDOMNode beanNode = QuickfixTestUtil.getNode(BeansSchemaConstants.ELEM_BEAN, "enclosingBeanTest", beansNode
				.getChildNodes());
		assertEquals(beanNode, QuickfixUtils.getEnclosingBeanNode(beanNode));

		IDOMNode propertyNode = QuickfixTestUtil.getFirstNode(BeansSchemaConstants.ELEM_PROPERTY, beanNode
				.getChildNodes());
		assertEquals(beanNode, QuickfixUtils.getEnclosingBeanNode(propertyNode));

		IDOMNode innerBeanNode = QuickfixTestUtil.getFirstNode(BeansSchemaConstants.ELEM_BEAN, propertyNode
				.getChildNodes());
		assertEquals("Expects enclosing bean node to match bean node", innerBeanNode, QuickfixUtils
				.getEnclosingBeanNode(innerBeanNode));
	}

	// public void testGetFile() {
	// IDOMNode beanNode =
	// QuickfixTestUtil.getFirstNode(BeansSchemaConstants.ELEM_BEAN,
	// beansNode.getChildNodes());
	// assertEquals(file,
	// QuickfixUtils.getFile(beanNode.getFirstStructuredDocumentRegion()));
	// assertEquals(file,
	// QuickfixUtils.getFile(beanNode.getLastStructuredDocumentRegion()));
	// assertEquals(file,
	// QuickfixUtils.getFile(beanNode.getEndStructuredDocumentRegion()));
	// }

}
