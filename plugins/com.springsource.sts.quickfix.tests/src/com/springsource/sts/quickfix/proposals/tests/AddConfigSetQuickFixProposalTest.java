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
import org.springframework.ide.eclipse.beans.core.internal.model.BeansProject;
import org.springframework.ide.eclipse.beans.core.model.IBean;
import org.springframework.ide.eclipse.beans.core.model.IBeansConfig;
import org.springframework.ide.eclipse.beans.core.model.IBeansConfigSet;

import com.springsource.sts.config.core.schemas.BeansSchemaConstants;
import com.springsource.sts.quickfix.proposals.AddConfigSetQuickFixProposal;
import com.springsource.sts.quickfix.tests.QuickfixTestUtil;

/**
 * @author Terry Denney
 */
@SuppressWarnings("restriction")
public class AddConfigSetQuickFixProposalTest extends AbstractBeanFileQuickfixTestCase {

	@Override
	protected void setUp() throws Exception {
		super.setUp();

		openBeanEditor("src/config-set-proposal-test.xml");
	}

	public void testAddConfigSet() {
		IDOMNode beanNode = QuickfixTestUtil.getNode(BeansSchemaConstants.ELEM_BEAN, "addToConfigSetTest",
				beansNode.getChildNodes());
		AttrImpl parentAttr = (AttrImpl) beanNode.getAttributes().getNamedItem(BeansSchemaConstants.ATTR_PARENT);
		ITextRegion valueRegion = parentAttr.getValueRegion();

		int offset = getOffset(valueRegion, beanNode);
		int length = getLength(valueRegion, false);

		IFile configFile = (IFile) project.findMember("src/import-test.xml");
		IBeansConfig config = BeansCorePlugin.getModel().getConfig(configFile);
		IBean importBean = BeansModelUtils.getBean(parentAttr.getValue(), config);

		BeansProject beanProject = (BeansProject) BeansCorePlugin.getModel().getProject(project);
		IBeansConfigSet configSet = beanProject.getConfigSet("AddConfigSetTest");
		assertNull("Expects no config set", configSet);

		AddConfigSetQuickFixProposal proposal = new AddConfigSetQuickFixProposal(offset, length, false, importBean,
				file, "AddConfigSetTest");
		proposal.apply(document);

		configSet = beanProject.getConfigSet("AddConfigSetTest");
		assertNotNull("Expects config set to be created", configSet);
		assertTrue("Expects config-set-proposal-test.xml to be added to config set",
				configSet.getConfigs().contains(beanProject.getConfig(file)));

		IFile importFile = project.getFile("src/import-test.xml");
		assertTrue("Expects import-test.xml to be added to config set",
				configSet.getConfigs().contains(beanProject.getConfig(importFile)));
	}

}
