/******************************************************************************************
 * Copyright (c) 2010 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.tests.ui.editors;

import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.ui.forms.IDetailsPage;
import org.springsource.ide.eclipse.commons.tests.util.StsTestUtil;

import com.springsource.sts.config.core.schemas.BeansSchemaConstants;
import com.springsource.sts.config.tests.AbstractConfigTestCase;
import com.springsource.sts.config.ui.editors.AbstractConfigFormPage;
import com.springsource.sts.config.ui.editors.AbstractConfigMasterDetailsBlock;
import com.springsource.sts.config.ui.editors.AbstractNamespaceDetailsPart;
import com.springsource.sts.config.ui.editors.SpringConfigInputAccessor;

/**
 * @author Leo Dos Santos
 */
public class SpringConfigInputAccessorTest extends AbstractConfigTestCase {

	public void testEditAttribute() throws Exception {
		cEditor = openFileInEditor("src/beans-config.xml");
		assertNotNull("Could not open a configuration editor.", cEditor);

		AbstractConfigFormPage page = cEditor.getFormPageForUri(BeansSchemaConstants.URI);
		Thread.sleep(StsTestUtil.WAIT_TIME);
		cEditor.setActivePage(page.getId());
		assertNotNull("Could not load beans page.", page.getMasterPart());

		TreeViewer treeViewer = (TreeViewer) page.getMasterPart().getViewer();
		TreeItem root = treeViewer.getTree().getItem(0);
		TreeItem subItem = root.getItem(1);
		page.setSelection(new StructuredSelection(subItem.getData()));

		AbstractConfigMasterDetailsBlock block = page.getMasterDetailsBlock();
		IDetailsPage details = block.getDetailsPart().getCurrentPage();
		assertTrue("Could not load details part.", details instanceof AbstractNamespaceDetailsPart);

		AbstractNamespaceDetailsPart detailsPart = (AbstractNamespaceDetailsPart) details;
		SpringConfigInputAccessor accessor = new SpringConfigInputAccessor(cEditor, detailsPart.getInput());

		assertEquals("myConcreteClass", accessor.getAttributeValue(BeansSchemaConstants.ATTR_ID));
		assertEquals(detailsPart.getInput().getAttribute(BeansSchemaConstants.ATTR_ID),
				accessor.getAttributeValue(BeansSchemaConstants.ATTR_ID));

		accessor.editAttribute(BeansSchemaConstants.ATTR_ID, "foo");
		assertEquals("foo", accessor.getAttributeValue(BeansSchemaConstants.ATTR_ID));
		assertEquals(detailsPart.getInput().getAttribute(BeansSchemaConstants.ATTR_ID),
				accessor.getAttributeValue(BeansSchemaConstants.ATTR_ID));

		accessor.editAttribute(BeansSchemaConstants.ATTR_ID, "");
		assertEquals("", accessor.getAttributeValue(BeansSchemaConstants.ATTR_ID));
		assertNull(detailsPart.getInput().getAttributeNode(BeansSchemaConstants.ATTR_ID));

		accessor.editAttribute(BeansSchemaConstants.ATTR_ID, "myConcreteClass");
		assertEquals("myConcreteClass", accessor.getAttributeValue(BeansSchemaConstants.ATTR_ID));
		assertEquals(detailsPart.getInput().getAttribute(BeansSchemaConstants.ATTR_ID),
				accessor.getAttributeValue(BeansSchemaConstants.ATTR_ID));
	}

	public void testEditElement() throws Exception {
		cEditor = openFileInEditor("src/beans-config.xml");
		assertNotNull("Could not open a configuration editor.", cEditor);

		AbstractConfigFormPage page = cEditor.getFormPageForUri(BeansSchemaConstants.URI);
		Thread.sleep(StsTestUtil.WAIT_TIME);
		cEditor.setActivePage(page.getId());
		assertNotNull("Could not load beans page.", page.getMasterPart());

		TreeViewer treeViewer = (TreeViewer) page.getMasterPart().getViewer();
		TreeItem root = treeViewer.getTree().getItem(0);
		TreeItem subItem = root.getItem(0);
		page.setSelection(new StructuredSelection(subItem.getData()));

		AbstractConfigMasterDetailsBlock block = page.getMasterDetailsBlock();
		IDetailsPage details = block.getDetailsPart().getCurrentPage();
		assertTrue("Could not load details part.", details instanceof AbstractNamespaceDetailsPart);

		AbstractNamespaceDetailsPart detailsPart = (AbstractNamespaceDetailsPart) details;
		SpringConfigInputAccessor accessor = new SpringConfigInputAccessor(cEditor, detailsPart.getInput());

		assertEquals("A sample configuration file.", accessor.getElementValue());

		accessor.editElement("foo");
		assertEquals("foo", accessor.getElementValue());

		accessor.editElement("");
		assertEquals("", accessor.getElementValue());

		accessor.editElement("A sample configuration file.");
		assertEquals("A sample configuration file.", accessor.getElementValue());
	}

}
