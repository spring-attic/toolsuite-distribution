/******************************************************************************************
 * Copyright (c) 2009 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.tests.ui.editors;

import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.swtbot.swt.finder.finders.UIThreadRunnable;
import org.eclipse.swtbot.swt.finder.results.VoidResult;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotButton;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;
import org.springsource.ide.eclipse.commons.tests.util.StsTestUtil;

import com.springsource.sts.config.core.schemas.BeansSchemaConstants;
import com.springsource.sts.config.tests.AbstractConfigUiTestCase;
import com.springsource.sts.config.ui.editors.AbstractConfigFormPage;

/**
 * @author Leo Dos Santos
 */
public class AbstractNamespaceMasterPartUiTest extends AbstractConfigUiTestCase {

	public void testCreateButtons() throws Exception {
		cEditor = openFileInEditor("src/beans-config.xml");
		assertNotNull("Could not open a configuration editor.", cEditor);
		final AbstractConfigFormPage page = cEditor.getFormPageForUri(BeansSchemaConstants.URI);
		Thread.sleep(StsTestUtil.WAIT_TIME);

		UIThreadRunnable.syncExec(new VoidResult() {
			public void run() {
				cEditor.setActivePage(page.getId());
				assertNotNull("Could not load beans page.", page.getMasterPart());
			}
		});

		SWTBotButton newBeanButton = bot.flatButton("New Bean...");
		SWTBotButton upButton = bot.flatButton("Up");
		SWTBotButton downButton = bot.flatButton("Down");
		assertFalse(upButton.isEnabled());
		assertFalse(downButton.isEnabled());

		UIThreadRunnable.syncExec(new VoidResult() {
			public void run() {
				TreeViewer treeViewer = (TreeViewer) page.getMasterPart().getViewer();
				TreeItem root = treeViewer.getTree().getItem(0);
				TreeItem beanItem = root.getItem(1);
				page.setSelection(new StructuredSelection(beanItem.getData()));
			}
		});

		assertTrue(upButton.isEnabled());
		assertTrue(downButton.isEnabled());
		newBeanButton.click();

		SWTBotShell newBeanDialog = bot.shell("Create New Bean");
		assertTrue(newBeanDialog.isOpen());
		newBeanDialog.close();
	}

}
