/******************************************************************************************
 * Copyright (c) 2009 - 2010 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.tests.ui.actions;

import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.wst.sse.ui.internal.StructuredTextViewer;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMElement;
import org.springsource.ide.eclipse.commons.tests.util.StsTestUtil;

import com.springsource.sts.config.core.contentassist.SpringConfigContentAssistProcessor;
import com.springsource.sts.config.tests.AbstractConfigTestCase;
import com.springsource.sts.config.ui.actions.DeleteNodeAction;
import com.springsource.sts.config.ui.actions.InsertNodeAction;
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
public class InsertAndDeleteNodeActionTest extends AbstractConfigTestCase {

	public void testEmptyFile() throws Exception {
		cEditor = openFileInEditor("src/empty-beans.xml");
		assertNotNull("Could not open a configuration editor.", cEditor);

		AbstractConfigFormPage page = cEditor.getFormPage(OverviewFormPage.ID);
		StructuredTextViewer textView = cEditor.getTextViewer();
		Thread.sleep(StsTestUtil.WAIT_TIME);
		cEditor.setActivePage(page.getId());
		assertNotNull("Could not load overview page.", page.getMasterPart());

		TreeViewer treeViewer = (TreeViewer) page.getMasterPart().getViewer();
		TreeItem root = treeViewer.getTree().getItem(0);
		assertEquals(0, root.getItemCount());

		InsertNodeAction insert = new InsertNodeAction(treeViewer, cEditor.getXmlProcessor(), textView, "bean");
		treeViewer.getTree().setSelection(root);
		insert.run();
		assertEquals(1, root.getItemCount());

		IDOMElement node = (IDOMElement) root.getItem(0).getData();
		DeleteNodeAction delete = new DeleteNodeAction(textView, node);
		delete.run();
		assertEquals(0, root.getItemCount());
	}

	public void testLargeFileDeletion() throws Exception {
		cEditor = openFileInEditor("src/many-beans.xml");
		assertNotNull("Could not open a configuration editor.", cEditor);

		AbstractConfigFormPage page = cEditor.getFormPage(OverviewFormPage.ID);
		StructuredTextViewer textView = cEditor.getTextViewer();
		Thread.sleep(StsTestUtil.WAIT_TIME);
		cEditor.setActivePage(page.getId());
		assertNotNull("Could not load overview page.", page.getMasterPart());

		TreeViewer treeViewer = (TreeViewer) page.getMasterPart().getViewer();
		TreeItem root = treeViewer.getTree().getItem(0);
		IDOMElement node = (IDOMElement) root.getItem(0).getData();
		assertEquals(20, root.getItemCount());

		DeleteNodeAction action = new DeleteNodeAction(textView, node);
		action.run();
		assertEquals(19, root.getItemCount());
	}

	public void testLargeFileInsertion() throws Exception {
		cEditor = openFileInEditor("src/many-beans.xml");
		assertNotNull("Could not open a configuration editor.", cEditor);

		AbstractConfigFormPage page = cEditor.getFormPage(OverviewFormPage.ID);
		StructuredTextViewer textView = cEditor.getTextViewer();
		Thread.sleep(StsTestUtil.WAIT_TIME);
		cEditor.setActivePage(page.getId());
		assertNotNull("Could not load overview page.", page.getMasterPart());

		TreeViewer treeViewer = (TreeViewer) page.getMasterPart().getViewer();
		TreeItem root = treeViewer.getTree().getItem(0);
		assertEquals(20, root.getItemCount());

		InsertNodeAction action = new InsertNodeAction(treeViewer, cEditor.getXmlProcessor(), textView, "bean");
		treeViewer.getTree().setSelection(root);
		action.run();
		assertEquals(21, root.getItemCount());
	}
}
