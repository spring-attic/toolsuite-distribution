/******************************************************************************************
 * Copyright (c) 2009 - 2010 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.tests.flow;

import com.springsource.sts.config.core.schemas.BatchSchemaConstants;
import com.springsource.sts.config.core.schemas.BeansSchemaConstants;
import com.springsource.sts.config.core.schemas.IntegrationSchemaConstants;
import com.springsource.sts.config.core.schemas.UtilSchemaConstants;
import com.springsource.sts.config.flow.AbstractConfigGraphicalEditor;
import com.springsource.sts.config.tests.AbstractConfigTestCase;

/**
 * @author Leo Dos Santos
 */
public class AbstractConfigGraphicalEditorTest extends AbstractConfigTestCase {

	public void testBatchFile() throws Exception {
		cEditor = openFileInEditor("src/batch-config.xml");
		assertNotNull("Could not open a configuration editor.", cEditor);

		AbstractConfigGraphicalEditor batch = cEditor.getGraphicalEditorForUri(BatchSchemaConstants.URI);
		assertEquals(BatchSchemaConstants.URI, batch.getNamespaceUri());

		AbstractConfigGraphicalEditor beans = cEditor.getGraphicalEditorForUri(BeansSchemaConstants.URI);
		assertNull(beans);

		AbstractConfigGraphicalEditor util = cEditor.getGraphicalEditorForUri(UtilSchemaConstants.URI);
		assertNull(util);
	}

	public void testBeansFile() throws Exception {
		cEditor = openFileInEditor("src/beans-config.xml");
		assertNotNull("Could not open a configuration editor.", cEditor);

		AbstractConfigGraphicalEditor batch = cEditor.getGraphicalEditorForUri(BatchSchemaConstants.URI);
		assertNull(batch);

		AbstractConfigGraphicalEditor beans = cEditor.getGraphicalEditorForUri(BeansSchemaConstants.URI);
		assertNull(beans);

		AbstractConfigGraphicalEditor util = cEditor.getGraphicalEditorForUri(UtilSchemaConstants.URI);
		assertNull(util);
	}

	public void testIntegrationFile() throws Exception {
		cEditor = openFileInEditor("src/integration-config.xml");
		assertNotNull("Could not open a configuration editor.", cEditor);

		AbstractConfigGraphicalEditor batch = cEditor.getGraphicalEditorForUri(BatchSchemaConstants.URI);
		assertNull(batch);

		AbstractConfigGraphicalEditor beans = cEditor.getGraphicalEditorForUri(BeansSchemaConstants.URI);
		assertNull(beans);

		AbstractConfigGraphicalEditor integration = cEditor.getGraphicalEditorForUri(IntegrationSchemaConstants.URI);
		assertEquals(IntegrationSchemaConstants.URI, integration.getNamespaceUri());

		AbstractConfigGraphicalEditor util = cEditor.getGraphicalEditorForUri(UtilSchemaConstants.URI);
		assertNull(util);
	}

	public void testScopedFile() throws Exception {
		cEditor = openFileInEditor("src/scoped-config.xml");
		assertNotNull("Could not open a configuration editor.", cEditor);

		AbstractConfigGraphicalEditor batch = cEditor.getGraphicalEditorForUri(BatchSchemaConstants.URI);
		assertEquals(BatchSchemaConstants.URI, batch.getNamespaceUri());

		AbstractConfigGraphicalEditor beans = cEditor.getGraphicalEditorForUri(BeansSchemaConstants.URI);
		assertNull(beans);

		AbstractConfigGraphicalEditor util = cEditor.getGraphicalEditorForUri(UtilSchemaConstants.URI);
		assertNull(util);
	}

}
