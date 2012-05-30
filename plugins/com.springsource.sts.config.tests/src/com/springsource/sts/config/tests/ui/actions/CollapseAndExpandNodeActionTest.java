/******************************************************************************************
 * Copyright (c) 2009 - 2010 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.tests.ui.actions;

import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMElement;
import org.springsource.ide.eclipse.commons.tests.util.StsTestUtil;

import com.springsource.sts.config.core.contentassist.SpringConfigContentAssistProcessor;
import com.springsource.sts.config.tests.AbstractConfigTestCase;
import com.springsource.sts.config.ui.actions.CollapseNodeAction;
import com.springsource.sts.config.ui.actions.ExpandNodeAction;
import com.springsource.sts.config.ui.editors.AbstractConfigFormPage;
import com.springsource.sts.config.ui.editors.overview.OverviewFormPage;

/**
 * These tests call Thread.sleep() to get around a bug in
 * {@link SpringConfigContentAssistProcessor#getChildNames(IDOMElement)} but may
 * still fail randomly.}
 * @author Leo Dos Santos
 * @author Terry Denney
 * @author Christian Dupuis
 * @author Steffen Pingel
 */
@SuppressWarnings("restriction")
public class CollapseAndExpandNodeActionTest extends AbstractConfigTestCase {

	public void testInnerNode() throws Exception {
		cEditor = openFileInEditor("src/beans-config.xml");
		assertNotNull("Could not open a configuration editor.", cEditor);

		AbstractConfigFormPage page = cEditor.getFormPage(OverviewFormPage.ID);
		Thread.sleep(StsTestUtil.WAIT_TIME);
		cEditor.setActivePage(page.getId());
		assertNotNull("Could not load overview page.", page.getMasterPart());

		TreeViewer treeViewer = (TreeViewer) page.getMasterPart().getViewer();
		TreeItem root = treeViewer.getTree().getItem(0);
		TreeItem innerNode = root.getItem(3);
		root.setExpanded(true);
		assertFalse(innerNode.getExpanded());

		ExpandNodeAction expand = new ExpandNodeAction(treeViewer, cEditor.getXmlProcessor());
		treeViewer.getTree().setSelection(innerNode);
		expand.run();
		assertTrue(innerNode.getExpanded());

		CollapseNodeAction collapse = new CollapseNodeAction(treeViewer, cEditor.getXmlProcessor());
		treeViewer.getTree().setSelection(innerNode);
		collapse.run();
		assertFalse(innerNode.getExpanded());
		assertTrue(root.getExpanded());
	}

	public void testRootNode() throws Exception {
		cEditor = openFileInEditor("src/many-beans.xml");
		assertNotNull("Could not open a configuration editor.", cEditor);

		AbstractConfigFormPage page = cEditor.getFormPage(OverviewFormPage.ID);
		Thread.sleep(StsTestUtil.WAIT_TIME);
		cEditor.setActivePage(page.getId());
		assertNotNull("Could not load overview page.", page.getMasterPart());

		TreeViewer treeViewer = (TreeViewer) page.getMasterPart().getViewer();
		TreeItem root = treeViewer.getTree().getItem(0);
		root.setExpanded(true);
		assertTrue(root.getExpanded());

		CollapseNodeAction collapse = new CollapseNodeAction(treeViewer, cEditor.getXmlProcessor());
		treeViewer.getTree().setSelection(root);
		collapse.run();
		assertFalse(root.getExpanded());

		ExpandNodeAction expand = new ExpandNodeAction(treeViewer, cEditor.getXmlProcessor());
		treeViewer.getTree().setSelection(root);
		expand.run();
		assertTrue(root.getExpanded());
	}

}
