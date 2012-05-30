/******************************************************************************************
 * Copyright (c) 2009 - 2010 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.tests.ui.editors;

import com.springsource.sts.config.core.schemas.AopSchemaConstants;
import com.springsource.sts.config.core.schemas.BatchSchemaConstants;
import com.springsource.sts.config.core.schemas.BeansSchemaConstants;
import com.springsource.sts.config.core.schemas.IntStreamSchemaConstants;
import com.springsource.sts.config.core.schemas.IntegrationSchemaConstants;
import com.springsource.sts.config.core.schemas.UtilSchemaConstants;
import com.springsource.sts.config.tests.AbstractConfigTestCase;
import com.springsource.sts.config.ui.editors.AbstractConfigFormPage;
import com.springsource.sts.config.ui.editors.overview.OverviewFormPage;

/**
 * @author Leo Dos Santos
 * @author Christian Dupuis
 */
public class AbstractConfigFormPageTest extends AbstractConfigTestCase {

	public void testAopFile() throws Exception {
		cEditor = openFileInEditor("src/aop-config.xml");
		assertNotNull("Could not open a configuration editor.", cEditor);

		AbstractConfigFormPage overview = cEditor.getFormPage(OverviewFormPage.ID);
		assertEquals(OverviewFormPage.ID, overview.getId());
		assertNull(overview.getNamespaceUri());
		assertEquals(null, overview.getPrefixForNamespaceUri());

		AbstractConfigFormPage aop = cEditor.getFormPageForUri(AopSchemaConstants.URI);
		assertEquals("com.springsource.sts.config.ui.editors.aop", aop.getId());
		assertEquals(AopSchemaConstants.URI, aop.getNamespaceUri());
		assertEquals("", aop.getPrefixForNamespaceUri());

		AbstractConfigFormPage beans = cEditor.getFormPageForUri(BeansSchemaConstants.URI);
		assertEquals("com.springsource.sts.config.ui.editors.beans", beans.getId());
		assertEquals(BeansSchemaConstants.URI, beans.getNamespaceUri());
		assertEquals("beans", beans.getPrefixForNamespaceUri());

		AbstractConfigFormPage util = cEditor.getFormPageForUri(UtilSchemaConstants.URI);
		assertNull(util);
	}

	public void testBeansFile() throws Exception {
		cEditor = openFileInEditor("src/beans-config.xml");
		assertNotNull("Could not open a configuration editor.", cEditor);

		AbstractConfigFormPage overview = cEditor.getFormPage(OverviewFormPage.ID);
		assertEquals(OverviewFormPage.ID, overview.getId());
		assertNull(overview.getNamespaceUri());
		assertEquals(null, overview.getPrefixForNamespaceUri());

		AbstractConfigFormPage aop = cEditor.getFormPageForUri(AopSchemaConstants.URI);
		assertEquals("com.springsource.sts.config.ui.editors.aop", aop.getId());
		assertEquals(AopSchemaConstants.URI, aop.getNamespaceUri());
		assertEquals("aop", aop.getPrefixForNamespaceUri());

		AbstractConfigFormPage beans = cEditor.getFormPageForUri(BeansSchemaConstants.URI);
		assertEquals("com.springsource.sts.config.ui.editors.beans", beans.getId());
		assertEquals(BeansSchemaConstants.URI, beans.getNamespaceUri());
		assertEquals("", beans.getPrefixForNamespaceUri());

		AbstractConfigFormPage util = cEditor.getFormPageForUri(UtilSchemaConstants.URI);
		assertNull(util);
	}

	public void testIntegrationFile() throws Exception {
		cEditor = openFileInEditor("src/integration-config.xml");
		assertNotNull("Could not open a configuration editor.", cEditor);

		AbstractConfigFormPage overview = cEditor.getFormPage(OverviewFormPage.ID);
		assertEquals(OverviewFormPage.ID, overview.getId());
		assertNull(overview.getNamespaceUri());
		assertEquals(null, overview.getPrefixForNamespaceUri());

		AbstractConfigFormPage aop = cEditor.getFormPageForUri(AopSchemaConstants.URI);
		assertNull(aop);

		AbstractConfigFormPage beans = cEditor.getFormPageForUri(BeansSchemaConstants.URI);
		assertEquals("com.springsource.sts.config.ui.editors.beans", beans.getId());
		assertEquals(BeansSchemaConstants.URI, beans.getNamespaceUri());
		assertEquals("beans", beans.getPrefixForNamespaceUri());

		AbstractConfigFormPage integration = cEditor.getFormPageForUri(IntegrationSchemaConstants.URI);
		assertEquals("com.springsource.sts.config.ui.editors.integration", integration.getId());
		assertEquals(IntegrationSchemaConstants.URI, integration.getNamespaceUri());
		assertEquals("", integration.getPrefixForNamespaceUri());

		AbstractConfigFormPage stream = cEditor.getFormPage(IntStreamSchemaConstants.URI);
		assertNull(stream);

		AbstractConfigFormPage util = cEditor.getFormPageForUri(UtilSchemaConstants.URI);
		assertNull(util);
	}

	public void testScopedFile() throws Exception {
		cEditor = openFileInEditor("src/scoped-config.xml");
		assertNotNull("Could not open a configuration editor.", cEditor);

		AbstractConfigFormPage overview = cEditor.getFormPage(OverviewFormPage.ID);
		assertEquals(OverviewFormPage.ID, overview.getId());
		assertNull(overview.getNamespaceUri());
		assertEquals(null, overview.getPrefixForNamespaceUri());

		AbstractConfigFormPage aop = cEditor.getFormPageForUri(AopSchemaConstants.URI);
		assertNull(aop);

		AbstractConfigFormPage batch = cEditor.getFormPageForUri(BatchSchemaConstants.URI);
		assertEquals("com.springsource.sts.config.ui.editors.batch", batch.getId());
		assertEquals(BatchSchemaConstants.URI, batch.getNamespaceUri());
		assertEquals(null, batch.getPrefixForNamespaceUri());

		AbstractConfigFormPage beans = cEditor.getFormPageForUri(BeansSchemaConstants.URI);
		assertEquals("com.springsource.sts.config.ui.editors.beans", beans.getId());
		assertEquals(BeansSchemaConstants.URI, beans.getNamespaceUri());
		assertEquals("", beans.getPrefixForNamespaceUri());

		AbstractConfigFormPage util = cEditor.getFormPageForUri(UtilSchemaConstants.URI);
		assertNull(util);
	}

}
