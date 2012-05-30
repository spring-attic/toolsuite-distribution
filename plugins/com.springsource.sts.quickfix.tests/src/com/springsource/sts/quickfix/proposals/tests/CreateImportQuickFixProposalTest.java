/******************************************************************************************
 * Copyright (c) 2010 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.quickfix.proposals.tests;

import org.eclipse.core.resources.IFile;
import org.eclipse.wst.sse.core.internal.provisional.text.ITextRegion;
import org.eclipse.wst.xml.core.internal.document.AttrImpl;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMNode;
import org.springframework.ide.eclipse.beans.core.BeansCorePlugin;
import org.springframework.ide.eclipse.beans.core.internal.model.BeansModelUtils;
import org.springframework.ide.eclipse.beans.core.model.IBean;
import org.springframework.ide.eclipse.beans.core.model.IBeansConfig;
import org.springframework.ide.eclipse.beans.core.model.IBeansProject;

import com.springsource.sts.config.core.schemas.BeansSchemaConstants;
import com.springsource.sts.quickfix.proposals.CreateImportQuickFixProposal;
import com.springsource.sts.quickfix.tests.QuickfixTestUtil;

/**
 * @author Terry Denney
 */
@SuppressWarnings("restriction")
public class CreateImportQuickFixProposalTest extends AbstractBeanFileQuickfixTestCase {

	private void applyProposal(String importFilePath) {
		IDOMNode beanNode = QuickfixTestUtil.getNode(BeansSchemaConstants.ELEM_BEAN, "addImportTest",
				beansNode.getChildNodes());
		AttrImpl parentAttr = (AttrImpl) beanNode.getAttributes().getNamedItem(BeansSchemaConstants.ATTR_PARENT);
		ITextRegion valueRegion = parentAttr.getValueRegion();

		int offset = getOffset(valueRegion, beanNode);
		int length = getLength(valueRegion, false);

		IFile configFile = (IFile) project.findMember(importFilePath);
		IBeansConfig config = BeansCorePlugin.getModel().getConfig(configFile);
		IBean importBean = BeansModelUtils.getBean(parentAttr.getValue(), config);

		IBeansProject beanProject = BeansCorePlugin.getModel().getProject(project);

		CreateImportQuickFixProposal proposal = new CreateImportQuickFixProposal(offset, length, false, importBean,
				beanNode, beanProject, file);
		proposal.apply(document);
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();

		openBeanEditor("src/import-proposal-test.xml");
	}

	public void testAddImportInDifferentFolder() {
		IDOMNode importNode = QuickfixTestUtil.getNode(BeansSchemaConstants.ELEM_IMPORT, 0, beansNode.getChildNodes());
		assertNull("Expects no import", importNode);

		applyProposal("src2/import-test.xml");

		importNode = QuickfixTestUtil.getNode(BeansSchemaConstants.ELEM_IMPORT, 0, beansNode.getChildNodes());
		assertNotNull("Expects import added", importNode);

		AttrImpl resourceAttr = (AttrImpl) importNode.getAttributes().getNamedItem(BeansSchemaConstants.ATTR_RESOURCE);
		assertNotNull("Expects resource attribute added to import", resourceAttr);

		assertEquals("Expects resource attribute to be ../src2/import-test.xml", resourceAttr.getValue(),
				"../src2/import-test.xml");
	}

	public void testAddImportInSameFolder() {
		IDOMNode importNode = QuickfixTestUtil.getNode(BeansSchemaConstants.ELEM_IMPORT, 0, beansNode.getChildNodes());
		assertNull("Expects no import", importNode);

		applyProposal("src/import-test.xml");

		importNode = QuickfixTestUtil.getNode(BeansSchemaConstants.ELEM_IMPORT, 0, beansNode.getChildNodes());
		assertNotNull("Expects import added", importNode);

		AttrImpl resourceAttr = (AttrImpl) importNode.getAttributes().getNamedItem(BeansSchemaConstants.ATTR_RESOURCE);
		assertNotNull("Expects resource attribute added to import", resourceAttr);

		assertEquals("Expects resource attribute to be import-test.xml", resourceAttr.getValue(), "import-test.xml");
	}
}
