/******************************************************************************************
 * Copyright (c) 2009 - 2011 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.tests.ui.editors.namespaces;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.eclipse.jface.viewers.CheckboxTableViewer;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.ui.forms.IDetailsPage;
import org.springsource.ide.eclipse.commons.tests.util.StsTestUtil;

import com.springsource.sts.config.tests.AbstractConfigTestCase;
import com.springsource.sts.config.ui.editors.AbstractConfigFormPage;
import com.springsource.sts.config.ui.editors.AbstractConfigMasterDetailsBlock;
import com.springsource.sts.config.ui.editors.namespaces.NamespacesDetailsPart;
import com.springsource.sts.config.ui.editors.namespaces.NamespacesFormPage;
import com.springsource.sts.config.ui.editors.namespaces.NamespacesMasterPart;

/**
 * @author Leo Dos Santos
 * @author Christian Dupuis
 */
public class NamespacesDetailsPartTest extends AbstractConfigTestCase {

	public void testViewerEnablement() throws Exception {
		cEditor = openFileInEditor("src/beans-config.xml");
		assertNotNull("Could not open a configuration editor.", cEditor);

		AbstractConfigFormPage page = cEditor.getFormPage(NamespacesFormPage.ID);
		cEditor.setActivePage(page.getId());
		assertNotNull("Could not load namespaces page.", page.getMasterPart());

		CountDownLatch latch = ((NamespacesMasterPart) page.getMasterPart()).getLazyInitializationLatch();
		assertTrue("Table initialization did not complete before timeout.", latch.await(30, TimeUnit.SECONDS));
		StsTestUtil.waitForDisplay();

		AbstractConfigMasterDetailsBlock block = page.getMasterDetailsBlock();
		CheckboxTableViewer checkViewer = (CheckboxTableViewer) page.getMasterPart().getViewer();
		assertTrue(checkViewer.getTable().getItemCount() > 0);

		for (TableItem item : checkViewer.getTable().getItems()) {
			page.setSelection(new StructuredSelection(item.getData()));
			IDetailsPage details = block.getDetailsPart().getCurrentPage();
			assertTrue("Could not load details part.", details instanceof NamespacesDetailsPart);

			NamespacesDetailsPart detailsPart = (NamespacesDetailsPart) details;
			ColumnViewer versionViewer = detailsPart.getVersionViewer();
			assertEquals(item.getChecked(), versionViewer.getControl().getEnabled());
		}
	}

}
