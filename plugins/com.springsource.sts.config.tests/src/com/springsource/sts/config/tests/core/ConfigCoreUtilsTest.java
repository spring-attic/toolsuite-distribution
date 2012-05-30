/******************************************************************************************
 * Copyright (c) 2009 - 2010 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.tests.core;

import java.util.List;

import org.eclipse.wst.xml.core.internal.provisional.document.IDOMDocument;

import com.springsource.sts.config.core.ConfigCoreUtils;
import com.springsource.sts.config.core.schemas.AopSchemaConstants;
import com.springsource.sts.config.core.schemas.BatchSchemaConstants;
import com.springsource.sts.config.core.schemas.BeansSchemaConstants;
import com.springsource.sts.config.core.schemas.IntFileSchemaConstants;
import com.springsource.sts.config.core.schemas.IntJmsSchemaConstants;
import com.springsource.sts.config.core.schemas.IntStreamSchemaConstants;
import com.springsource.sts.config.core.schemas.IntegrationSchemaConstants;
import com.springsource.sts.config.core.schemas.TxSchemaConstants;
import com.springsource.sts.config.core.schemas.UtilSchemaConstants;
import com.springsource.sts.config.tests.AbstractConfigTestCase;

/**
 * @author Leo Dos Santos
 * @author Christian Dupuis
 */
@SuppressWarnings("restriction")
public class ConfigCoreUtilsTest extends AbstractConfigTestCase {

	public void testGetDefaultNamespaceUriAopFile() throws Exception {
		cEditor = openFileInEditor("src/aop-config.xml");
		assertNotNull("Could not open a configuration editor.", cEditor);
		IDOMDocument doc = cEditor.getDomDocument();
		assertEquals(AopSchemaConstants.URI, ConfigCoreUtils.getDefaultNamespaceUri(doc));
	}

	public void testGetDefaultNamespaceUriBeansFile() throws Exception {
		cEditor = openFileInEditor("src/beans-config.xml");
		assertNotNull("Could not open a configuration editor.", cEditor);
		IDOMDocument doc = cEditor.getDomDocument();
		assertEquals(BeansSchemaConstants.URI, ConfigCoreUtils.getDefaultNamespaceUri(doc));
	}

	public void testGetDefaultNamespaceUriIntegrationFile() throws Exception {
		cEditor = openFileInEditor("src/integration-config.xml");
		assertNotNull("Could not open a configuration editor.", cEditor);
		IDOMDocument doc = cEditor.getDomDocument();
		assertEquals(IntegrationSchemaConstants.URI, ConfigCoreUtils.getDefaultNamespaceUri(doc));
	}

	public void testGetDefaultNamespaceUriScopedFile() throws Exception {
		cEditor = openFileInEditor("src/scoped-config.xml");
		assertNotNull("Could not open a configuration editor.", cEditor);
		IDOMDocument doc = cEditor.getDomDocument();
		assertEquals(BeansSchemaConstants.URI, ConfigCoreUtils.getDefaultNamespaceUri(doc));
	}

	public void testGetPrefixForNamespaceUriAopFile() throws Exception {
		cEditor = openFileInEditor("src/aop-config.xml");
		assertNotNull("Could not open a configuration editor.", cEditor);
		IDOMDocument doc = cEditor.getDomDocument();
		assertEquals("", ConfigCoreUtils.getPrefixForNamespaceUri(doc, AopSchemaConstants.URI));
		assertEquals("beans", ConfigCoreUtils.getPrefixForNamespaceUri(doc, BeansSchemaConstants.URI));
		assertEquals("tx", ConfigCoreUtils.getPrefixForNamespaceUri(doc, TxSchemaConstants.URI));
		assertEquals(null, ConfigCoreUtils.getPrefixForNamespaceUri(doc, UtilSchemaConstants.URI));
	}

	public void testGetPrefixForNamespaceUriBeansFile() throws Exception {
		cEditor = openFileInEditor("src/beans-config.xml");
		assertNotNull("Could not open a configuration editor.", cEditor);
		IDOMDocument doc = cEditor.getDomDocument();
		assertEquals("aop", ConfigCoreUtils.getPrefixForNamespaceUri(doc, AopSchemaConstants.URI));
		assertEquals("", ConfigCoreUtils.getPrefixForNamespaceUri(doc, BeansSchemaConstants.URI));
		assertEquals("tx", ConfigCoreUtils.getPrefixForNamespaceUri(doc, TxSchemaConstants.URI));
		assertEquals(null, ConfigCoreUtils.getPrefixForNamespaceUri(doc, UtilSchemaConstants.URI));
	}

	public void testGetPrefixForNamespaceUriIntegrationFile() throws Exception {
		cEditor = openFileInEditor("src/integration-config.xml");
		assertNotNull("Could not open a configuration editor.", cEditor);
		IDOMDocument doc = cEditor.getDomDocument();
		assertEquals("beans", ConfigCoreUtils.getPrefixForNamespaceUri(doc, BeansSchemaConstants.URI));
		assertEquals("", ConfigCoreUtils.getPrefixForNamespaceUri(doc, IntegrationSchemaConstants.URI));
		assertEquals(null, ConfigCoreUtils.getPrefixForNamespaceUri(doc, IntFileSchemaConstants.URI));
		assertEquals("int-jms", ConfigCoreUtils.getPrefixForNamespaceUri(doc, IntJmsSchemaConstants.URI));
		assertEquals("stream", ConfigCoreUtils.getPrefixForNamespaceUri(doc, IntStreamSchemaConstants.URI));
	}

	public void testGetPrefixForNamespaceUriScopedFile() throws Exception {
		cEditor = openFileInEditor("src/scoped-config.xml");
		assertNotNull("Could not open a configuration editor.", cEditor);
		IDOMDocument doc = cEditor.getDomDocument();
		assertEquals(null, ConfigCoreUtils.getPrefixForNamespaceUri(doc, AopSchemaConstants.URI));
		assertEquals("", ConfigCoreUtils.getPrefixForNamespaceUri(doc, BeansSchemaConstants.URI));
		assertEquals(null, ConfigCoreUtils.getPrefixForNamespaceUri(doc, BatchSchemaConstants.URI));
		assertEquals(null, ConfigCoreUtils.getPrefixForNamespaceUri(doc, UtilSchemaConstants.URI));
	}

	public void testParseSchemaLocationAttrBeansFile() throws Exception {
		cEditor = openFileInEditor("src/beans-config.xml");
		assertNotNull("Could not open a configuration editor.", cEditor);
		IDOMDocument doc = cEditor.getDomDocument();
		List<String> schemaInfo = ConfigCoreUtils.parseSchemaLocationAttr(doc);
		assertNotNull("Could not parse schema version information.", schemaInfo);
		assertTrue(schemaInfo.contains(AopSchemaConstants.URI));
		assertTrue(schemaInfo.contains(BeansSchemaConstants.URI));
		assertTrue(schemaInfo.contains(TxSchemaConstants.URI));
		assertFalse(schemaInfo.contains(UtilSchemaConstants.URI));
	}

	public void testParseSchemaLocationAttrScopedFile() throws Exception {
		cEditor = openFileInEditor("src/scoped-config.xml");
		assertNotNull("Could not open a configuration editor.", cEditor);
		IDOMDocument doc = cEditor.getDomDocument();
		List<String> schemaInfo = ConfigCoreUtils.parseSchemaLocationAttr(doc);
		assertNotNull("Could not parse schema version information.", schemaInfo);
		assertFalse(schemaInfo.contains(AopSchemaConstants.URI));
		assertTrue(schemaInfo.contains(BeansSchemaConstants.URI));
		assertTrue(schemaInfo.contains(BatchSchemaConstants.URI));
		assertFalse(schemaInfo.contains(UtilSchemaConstants.URI));
	}

}
