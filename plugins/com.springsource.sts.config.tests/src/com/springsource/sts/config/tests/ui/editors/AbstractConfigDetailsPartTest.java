/******************************************************************************************
 * Copyright (c) 2009 - 2010 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.tests.ui.editors;

import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.ui.forms.IDetailsPage;
import org.eclipse.ui.forms.widgets.Section;
import org.springsource.ide.eclipse.commons.tests.util.StsTestUtil;

import com.springsource.sts.config.core.schemas.BeansSchemaConstants;
import com.springsource.sts.config.tests.AbstractConfigTestCase;
import com.springsource.sts.config.ui.editors.AbstractConfigDetailsPart;
import com.springsource.sts.config.ui.editors.AbstractConfigFormPage;
import com.springsource.sts.config.ui.editors.AbstractConfigMasterDetailsBlock;

/**
 * @author Leo Dos Santos
 * @author Terry Denney
 * @author Christian Dupuis
 * @author Steffen Pingel
 */
public class AbstractConfigDetailsPartTest extends AbstractConfigTestCase {

	public void testDetailsSection() throws Exception {
		cEditor = openFileInEditor("src/beans-config.xml");
		assertNotNull("Could not open a configuration editor.", cEditor);

		AbstractConfigFormPage page = cEditor.getFormPageForUri(BeansSchemaConstants.URI);
		Thread.sleep(StsTestUtil.WAIT_TIME);
		cEditor.setActivePage(page.getId());
		assertNotNull("Could not load beans page.", page.getMasterPart());

		TreeViewer treeViewer = (TreeViewer) page.getMasterPart().getViewer();
		TreeItem root = treeViewer.getTree().getItem(0);
		TreeItem beanItem = root.getItem(0);
		page.setSelection(new StructuredSelection(beanItem.getData()));

		AbstractConfigMasterDetailsBlock block = page.getMasterDetailsBlock();
		IDetailsPage details = block.getDetailsPart().getCurrentPage();
		assertTrue("Could not load details part.", details instanceof AbstractConfigDetailsPart);

		AbstractConfigDetailsPart detailsPart = (AbstractConfigDetailsPart) details;
		Section detailsSection = detailsPart.getDetailsSection().getSection();
		assertNotNull(detailsSection);
		assertTrue(detailsSection.isExpanded());
		assertTrue(detailsSection.getChildren().length > 0);
	}

}
