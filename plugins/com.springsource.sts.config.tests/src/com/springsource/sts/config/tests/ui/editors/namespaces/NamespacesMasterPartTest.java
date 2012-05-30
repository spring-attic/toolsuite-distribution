/******************************************************************************************
 * Copyright (c) 2009 - 2011 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.tests.ui.editors.namespaces;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.jface.dialogs.MessageDialogWithToggle;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.viewers.CheckboxTableViewer;
import org.eclipse.swt.widgets.TableItem;
import org.springframework.ide.eclipse.beans.core.BeansCorePlugin;
import org.springframework.ide.eclipse.beans.ui.namespaces.INamespaceDefinition;
import org.springsource.ide.eclipse.commons.tests.util.StsTestUtil;

import com.springsource.sts.config.core.extensions.FormPagesExtensionPointConstants;
import com.springsource.sts.config.core.preferences.SpringConfigPreferenceConstants;
import com.springsource.sts.config.core.schemas.AopSchemaConstants;
import com.springsource.sts.config.core.schemas.BatchSchemaConstants;
import com.springsource.sts.config.core.schemas.BeansSchemaConstants;
import com.springsource.sts.config.core.schemas.IntFileSchemaConstants;
import com.springsource.sts.config.core.schemas.IntStreamSchemaConstants;
import com.springsource.sts.config.core.schemas.IntegrationSchemaConstants;
import com.springsource.sts.config.core.schemas.TxSchemaConstants;
import com.springsource.sts.config.core.schemas.UtilSchemaConstants;
import com.springsource.sts.config.tests.AbstractConfigTestCase;
import com.springsource.sts.config.ui.ConfigUiPlugin;
import com.springsource.sts.config.ui.editors.AbstractConfigFormPage;
import com.springsource.sts.config.ui.editors.namespaces.NamespacesFormPage;
import com.springsource.sts.config.ui.editors.namespaces.NamespacesMasterPart;
import com.springsource.sts.config.ui.extensions.ConfigUiExtensionPointReader;

/**
 * @author Leo Dos Santos
 * @author Christian Dupuis
 */
public class NamespacesMasterPartTest extends AbstractConfigTestCase {

	@Override
	protected void setUp() throws Exception {
		IPreferenceStore prefs = BeansCorePlugin.getDefault().getPreferenceStore();
		prefs.setValue(BeansCorePlugin.LOAD_NAMESPACEHANDLER_FROM_CLASSPATH_ID, true);
	}

	public void testAddThenRemoveNamespaceBeansFile() throws Exception {
		cEditor = openFileInEditor("src/beans-config.xml");
		assertNotNull("Could not open a configuration editor.", cEditor);

		ConfigUiPlugin.getDefault().getPreferenceStore()
				.setValue(SpringConfigPreferenceConstants.PREF_DISPLAY_TABS_DIALOG, MessageDialogWithToggle.NEVER);
		AbstractConfigFormPage page = cEditor.getFormPage(NamespacesFormPage.ID);
		cEditor.setActivePage(page.getId());
		assertNotNull("Could not load namespaces page.", page.getMasterPart());

		CheckboxTableViewer checkViewer = (CheckboxTableViewer) page.getMasterPart().getViewer();
		assertNull(cEditor.getFormPageForUri(UtilSchemaConstants.URI));
		assertNotNull(cEditor.getFormPageForUri(BeansSchemaConstants.URI));

		CountDownLatch latch = ((NamespacesMasterPart) page.getMasterPart()).getLazyInitializationLatch();
		assertTrue("Table initialization did not complete before timeout.", latch.await(30, TimeUnit.SECONDS));
		StsTestUtil.waitForDisplay();

		boolean foundItem = false;
		List<String> uris = new ArrayList<String>();
		for (TableItem item : checkViewer.getTable().getItems()) {
			INamespaceDefinition def = (INamespaceDefinition) item.getData();
			String currentUri = def.getNamespaceURI();
			uris.add(currentUri);
			if (UtilSchemaConstants.URI.equals(currentUri)) {
				foundItem = true;
				assertFalse("Expected table item " + currentUri + "to be unchecked.", item.getChecked());
				item.setChecked(true);
				((NamespacesMasterPart) page.getMasterPart()).updateDefinitionFromCheckState(def, item.getChecked());
				assertNotNull(cEditor.getFormPageForUri(UtilSchemaConstants.URI));
				assertNotNull(cEditor.getFormPageForUri(BeansSchemaConstants.URI));
				item.setChecked(false);
				((NamespacesMasterPart) page.getMasterPart()).updateDefinitionFromCheckState(def, item.getChecked());
				assertNull(cEditor.getFormPageForUri(UtilSchemaConstants.URI));
				assertNotNull(cEditor.getFormPageForUri(BeansSchemaConstants.URI));
			}
		}
		assertTrue("Expected to find " + UtilSchemaConstants.URI + " but found " + uris + " instead.", foundItem);
	}

	public void testAddThenRemoveNamespaceIntegrationFile() throws Exception {
		cEditor = openFileInEditor("src/integration-config.xml");
		assertNotNull("Could not open a configuration editor.", cEditor);

		ConfigUiPlugin.getDefault().getPreferenceStore()
				.setValue(SpringConfigPreferenceConstants.PREF_DISPLAY_TABS_DIALOG, MessageDialogWithToggle.NEVER);
		AbstractConfigFormPage page = cEditor.getFormPage(NamespacesFormPage.ID);
		cEditor.setActivePage(page.getId());
		assertNotNull("Could not load namespaces page.", page.getMasterPart());

		// CheckboxTableViewer checkViewer = (CheckboxTableViewer)
		// page.getMasterPart().getViewer();
		assertNull(cEditor.getFormPageForUri(IntFileSchemaConstants.URI));
		assertNotNull(cEditor.getFormPageForAdapterUri(IntFileSchemaConstants.URI));
		assertNotNull(cEditor.getFormPageForUri(IntegrationSchemaConstants.URI));

		// boolean foundItem = false;
		// for (TableItem item : checkViewer.getTable().getItems()) {
		// INamespaceDefinition def = (INamespaceDefinition) item.getData();
		// if (def.getNamespaceURI().equals(IntFileSchemaConstants.URI)) {
		// foundItem = true;
		// assertFalse(item.getChecked());
		// item.setChecked(true);
		// ((NamespacesMasterPart)
		// page.getMasterPart()).updateDefinitionFromCheckState(def,
		// item.getChecked());
		// assertNull(cEditor.getFormPageForUri(IntFileSchemaConstants.URI));
		// assertNotNull(cEditor.getFormPageForAdapterUri(IntFileSchemaConstants.URI));
		// assertNotNull(cEditor.getFormPageForUri(IntegrationSchemaConstants.URI));
		// item.setChecked(false);
		// ((NamespacesMasterPart)
		// page.getMasterPart()).updateDefinitionFromCheckState(def,
		// item.getChecked());
		// assertNull(cEditor.getFormPageForUri(IntFileSchemaConstants.URI));
		// assertNotNull(cEditor.getFormPageForAdapterUri(IntFileSchemaConstants.URI));
		// assertNotNull(cEditor.getFormPageForUri(IntegrationSchemaConstants.URI));
		// break;
		// }
		// }
		// assertTrue(foundItem);
	}

	public void testAddThenRemoveNamespaceScopedFile() throws Exception {
		cEditor = openFileInEditor("src/scoped-config.xml");
		assertNotNull("Could not open a configuration editor.", cEditor);

		ConfigUiPlugin.getDefault().getPreferenceStore()
				.setValue(SpringConfigPreferenceConstants.PREF_DISPLAY_TABS_DIALOG, MessageDialogWithToggle.NEVER);
		AbstractConfigFormPage page = cEditor.getFormPage(NamespacesFormPage.ID);
		cEditor.setActivePage(page.getId());
		assertNotNull("Could not load namespaces page.", page.getMasterPart());

		CheckboxTableViewer checkViewer = (CheckboxTableViewer) page.getMasterPart().getViewer();
		assertNull(cEditor.getFormPageForUri(AopSchemaConstants.URI));
		assertNotNull(cEditor.getFormPageForUri(BatchSchemaConstants.URI));

		CountDownLatch latch = ((NamespacesMasterPart) page.getMasterPart()).getLazyInitializationLatch();
		assertTrue("Table initialization did not complete before timeout.", latch.await(30, TimeUnit.SECONDS));
		StsTestUtil.waitForDisplay();

		boolean foundItem = false;
		List<String> uris = new ArrayList<String>();
		for (TableItem item : checkViewer.getTable().getItems()) {
			INamespaceDefinition def = (INamespaceDefinition) item.getData();
			String currentUri = def.getNamespaceURI();
			uris.add(currentUri);
			if (AopSchemaConstants.URI.equals(currentUri)) {
				foundItem = true;
				assertFalse("Expected table item " + currentUri + "to be unchecked.", item.getChecked());
				item.setChecked(true);
				((NamespacesMasterPart) page.getMasterPart()).updateDefinitionFromCheckState(def, item.getChecked());
				assertNotNull(cEditor.getFormPageForUri(AopSchemaConstants.URI));
				assertNotNull(cEditor.getFormPageForUri(BatchSchemaConstants.URI));
				item.setChecked(false);
				((NamespacesMasterPart) page.getMasterPart()).updateDefinitionFromCheckState(def, item.getChecked());
				assertNull(cEditor.getFormPageForUri(AopSchemaConstants.URI));
				assertNotNull(cEditor.getFormPageForUri(BatchSchemaConstants.URI));
			}
		}
		assertTrue("Expected to find " + AopSchemaConstants.URI + " but found " + uris + " instead.", foundItem);
	}

	public void testExistingPagesForNamespaceSelectionBeansFile() throws Exception {
		cEditor = openFileInEditor("src/beans-config.xml");
		assertNotNull("Could not open a configuration editor.", cEditor);

		AbstractConfigFormPage page = cEditor.getFormPage(NamespacesFormPage.ID);
		cEditor.setActivePage(page.getId());
		assertNotNull("Could not load namespaces page.", page.getMasterPart());

		CountDownLatch latch = ((NamespacesMasterPart) page.getMasterPart()).getLazyInitializationLatch();
		assertTrue("Table initialization did not complete before timeout.", latch.await(30, TimeUnit.SECONDS));
		StsTestUtil.waitForDisplay();

		CheckboxTableViewer checkViewer = (CheckboxTableViewer) page.getMasterPart().getViewer();
		Set<IConfigurationElement> definitions = ConfigUiExtensionPointReader.getPageDefinitions();
		for (IConfigurationElement def : definitions) {
			String uri = def.getAttribute(FormPagesExtensionPointConstants.ATTR_NAMESPACE_URI);
			AbstractConfigFormPage formPage = cEditor.getFormPageForUri(uri);
			if (formPage != null) {
				boolean foundItem = false;
				List<String> uris = new ArrayList<String>();
				for (TableItem item : checkViewer.getTable().getItems()) {
					INamespaceDefinition nsDef = (INamespaceDefinition) item.getData();
					String currentUri = nsDef.getNamespaceURI();
					uris.add(currentUri);
					if (item.getChecked() && currentUri.equals(formPage.getNamespaceUri())) {
						foundItem = true;
					}
				}
				assertTrue("Expected to find " + uri + " but found " + uris + " instead.", foundItem);
			}
		}
	}

	public void testExistingPagesForNamespaceSelectionIntegrationFile() throws Exception {
		cEditor = openFileInEditor("src/integration-config.xml");
		assertNotNull("Could not open a configuration editor.", cEditor);

		AbstractConfigFormPage page = cEditor.getFormPage(NamespacesFormPage.ID);
		cEditor.setActivePage(page.getId());
		assertNotNull("Could not load namespaces page.", page.getMasterPart());

		CountDownLatch latch = ((NamespacesMasterPart) page.getMasterPart()).getLazyInitializationLatch();
		assertTrue("Table initialization did not complete before timeout.", latch.await(30, TimeUnit.SECONDS));
		StsTestUtil.waitForDisplay();

		CheckboxTableViewer checkViewer = (CheckboxTableViewer) page.getMasterPart().getViewer();
		Set<IConfigurationElement> definitions = ConfigUiExtensionPointReader.getPageDefinitions();
		for (IConfigurationElement def : definitions) {
			String uri = def.getAttribute(FormPagesExtensionPointConstants.ATTR_NAMESPACE_URI);
			AbstractConfigFormPage formPage = cEditor.getFormPageForUri(uri);
			if (formPage != null) {
				boolean foundItem = false;
				List<String> uris = new ArrayList<String>();
				for (TableItem item : checkViewer.getTable().getItems()) {
					INamespaceDefinition nsDef = (INamespaceDefinition) item.getData();
					String currentUri = nsDef.getNamespaceURI();
					uris.add(currentUri);
					if (item.getChecked() && currentUri.equals(formPage.getNamespaceUri())) {
						foundItem = true;
					}
				}
				assertTrue("Expected to find " + uri + " but found " + uris + " instead.", foundItem);
			}
		}
	}

	public void testExistingPagesForNamespaceSelectionScopedFile() throws Exception {
		cEditor = openFileInEditor("src/scoped-config.xml");
		assertNotNull("Could not open a configuration editor.", cEditor);

		AbstractConfigFormPage page = cEditor.getFormPage(NamespacesFormPage.ID);
		cEditor.setActivePage(page.getId());
		assertNotNull("Could not load namespaces page.", page.getMasterPart());

		CountDownLatch latch = ((NamespacesMasterPart) page.getMasterPart()).getLazyInitializationLatch();
		assertTrue("Table initialization did not complete before timeout.", latch.await(30, TimeUnit.SECONDS));
		StsTestUtil.waitForDisplay();

		CheckboxTableViewer checkViewer = (CheckboxTableViewer) page.getMasterPart().getViewer();
		Set<IConfigurationElement> definitions = ConfigUiExtensionPointReader.getPageDefinitions();
		for (IConfigurationElement def : definitions) {
			String uri = def.getAttribute(FormPagesExtensionPointConstants.ATTR_NAMESPACE_URI);
			AbstractConfigFormPage formPage = cEditor.getFormPageForUri(uri);
			if (formPage != null) {
				boolean foundItem = false;
				List<String> uris = new ArrayList<String>();
				for (TableItem item : checkViewer.getTable().getItems()) {
					INamespaceDefinition nsDef = (INamespaceDefinition) item.getData();
					String currentUri = nsDef.getNamespaceURI();
					uris.add(currentUri);
					if (item.getChecked() && nsDef.getNamespaceURI().equals(formPage.getNamespaceUri())) {
						foundItem = true;
					}
				}
				assertTrue("Expected to find " + uri + " but found " + uris + " instead.", foundItem);
			}
		}
	}

	public void testRemoveThenAddNamespaceBeansFile() throws Exception {
		cEditor = openFileInEditor("src/beans-config.xml");
		assertNotNull("Could not open a configuration editor.", cEditor);

		ConfigUiPlugin.getDefault().getPreferenceStore()
				.setValue(SpringConfigPreferenceConstants.PREF_DISPLAY_TABS_DIALOG, MessageDialogWithToggle.NEVER);
		AbstractConfigFormPage page = cEditor.getFormPage(NamespacesFormPage.ID);
		cEditor.setActivePage(page.getId());
		assertNotNull("Could not load namespaces page.", page.getMasterPart());

		CheckboxTableViewer checkViewer = (CheckboxTableViewer) page.getMasterPart().getViewer();
		assertNotNull(cEditor.getFormPageForUri(AopSchemaConstants.URI));
		assertNotNull(cEditor.getFormPageForUri(BeansSchemaConstants.URI));

		CountDownLatch latch = ((NamespacesMasterPart) page.getMasterPart()).getLazyInitializationLatch();
		assertTrue("Table initialization did not complete before timeout.", latch.await(30, TimeUnit.SECONDS));
		StsTestUtil.waitForDisplay();

		boolean foundItem = false;
		List<String> uris = new ArrayList<String>();
		for (TableItem item : checkViewer.getTable().getItems()) {
			INamespaceDefinition def = (INamespaceDefinition) item.getData();
			String currentUri = def.getNamespaceURI();
			uris.add(currentUri);
			if (AopSchemaConstants.URI.equals(currentUri)) {
				foundItem = true;
				assertTrue("Expected table item " + currentUri + "to be checked.", item.getChecked());
				item.setChecked(false);
				((NamespacesMasterPart) page.getMasterPart()).updateDefinitionFromCheckState(def, item.getChecked());
				assertNull(cEditor.getFormPageForUri(AopSchemaConstants.URI));
				assertNotNull(cEditor.getFormPageForUri(BeansSchemaConstants.URI));
				item.setChecked(true);
				((NamespacesMasterPart) page.getMasterPart()).updateDefinitionFromCheckState(def, item.getChecked());
				assertNotNull(cEditor.getFormPageForUri(AopSchemaConstants.URI));
				assertNotNull(cEditor.getFormPageForUri(BeansSchemaConstants.URI));
			}
		}
		assertTrue("Expected to find " + AopSchemaConstants.URI + " but found " + uris + " instead.", foundItem);
	}

	public void testRemoveThenAddNamespaceIntegrationFile() throws Exception {
		cEditor = openFileInEditor("src/integration-config.xml");
		assertNotNull("Could not open a configuration editor.", cEditor);

		ConfigUiPlugin.getDefault().getPreferenceStore()
				.setValue(SpringConfigPreferenceConstants.PREF_DISPLAY_TABS_DIALOG, MessageDialogWithToggle.NEVER);
		AbstractConfigFormPage page = cEditor.getFormPage(NamespacesFormPage.ID);
		cEditor.setActivePage(page.getId());
		assertNotNull("Could not load namespaces page.", page.getMasterPart());

		CheckboxTableViewer checkViewer = (CheckboxTableViewer) page.getMasterPart().getViewer();
		assertNull(cEditor.getFormPageForUri(IntStreamSchemaConstants.URI));
		assertNotNull(cEditor.getFormPageForAdapterUri(IntStreamSchemaConstants.URI));
		assertNotNull(cEditor.getFormPageForUri(IntegrationSchemaConstants.URI));

		CountDownLatch latch = ((NamespacesMasterPart) page.getMasterPart()).getLazyInitializationLatch();
		assertTrue("Table initialization did not complete before timeout.", latch.await(30, TimeUnit.SECONDS));
		StsTestUtil.waitForDisplay();

		boolean foundItem = false;
		List<String> uris = new ArrayList<String>();
		for (TableItem item : checkViewer.getTable().getItems()) {
			INamespaceDefinition def = (INamespaceDefinition) item.getData();
			String currentUri = def.getNamespaceURI();
			uris.add(currentUri);
			if (IntStreamSchemaConstants.URI.equals(currentUri)) {
				foundItem = true;
				assertTrue("Expected table item " + currentUri + "to be checked.", item.getChecked());
				item.setChecked(false);
				((NamespacesMasterPart) page.getMasterPart()).updateDefinitionFromCheckState(def, item.getChecked());
				assertNull(cEditor.getFormPageForUri(IntStreamSchemaConstants.URI));
				assertNotNull(cEditor.getFormPageForAdapterUri(IntStreamSchemaConstants.URI));
				assertNotNull(cEditor.getFormPageForUri(IntegrationSchemaConstants.URI));
				item.setChecked(true);
				((NamespacesMasterPart) page.getMasterPart()).updateDefinitionFromCheckState(def, item.getChecked());
				assertNull(cEditor.getFormPageForUri(IntStreamSchemaConstants.URI));
				assertNotNull(cEditor.getFormPageForAdapterUri(IntStreamSchemaConstants.URI));
				assertNotNull(cEditor.getFormPageForUri(IntegrationSchemaConstants.URI));
			}
		}
		assertTrue("Expected to find " + IntStreamSchemaConstants.URI + " but found " + uris + " instead.", foundItem);
	}

	public void testRemoveThenAddNamespaceScopedFile() throws Exception {
		cEditor = openFileInEditor("src/scoped-config.xml");
		assertNotNull("Could not open a configuration editor.", cEditor);

		ConfigUiPlugin.getDefault().getPreferenceStore()
				.setValue(SpringConfigPreferenceConstants.PREF_DISPLAY_TABS_DIALOG, MessageDialogWithToggle.NEVER);
		AbstractConfigFormPage page = cEditor.getFormPage(NamespacesFormPage.ID);
		cEditor.setActivePage(page.getId());
		assertNotNull("Could not load namespaces page.", page.getMasterPart());

		CheckboxTableViewer checkViewer = (CheckboxTableViewer) page.getMasterPart().getViewer();
		assertNotNull(cEditor.getFormPageForUri(TxSchemaConstants.URI));
		assertNotNull(cEditor.getFormPageForUri(BatchSchemaConstants.URI));

		CountDownLatch latch = ((NamespacesMasterPart) page.getMasterPart()).getLazyInitializationLatch();
		assertTrue("Table initialization did not complete before timeout.", latch.await(30, TimeUnit.SECONDS));
		StsTestUtil.waitForDisplay();

		boolean foundItem = false;
		List<String> uris = new ArrayList<String>();
		for (TableItem item : checkViewer.getTable().getItems()) {
			INamespaceDefinition def = (INamespaceDefinition) item.getData();
			String currentUri = def.getNamespaceURI();
			uris.add(currentUri);
			if (TxSchemaConstants.URI.equals(currentUri)) {
				foundItem = true;
				assertTrue("Expected table item " + currentUri + "to be checked.", item.getChecked());
				item.setChecked(false);
				((NamespacesMasterPart) page.getMasterPart()).updateDefinitionFromCheckState(def, item.getChecked());
				assertNull(cEditor.getFormPageForUri(TxSchemaConstants.URI));
				assertNotNull(cEditor.getFormPageForUri(BatchSchemaConstants.URI));
				item.setChecked(true);
				((NamespacesMasterPart) page.getMasterPart()).updateDefinitionFromCheckState(def, item.getChecked());
				assertNotNull(cEditor.getFormPageForUri(TxSchemaConstants.URI));
				assertNotNull(cEditor.getFormPageForUri(BatchSchemaConstants.URI));
			}
		}
		assertTrue("Expected to find " + IntegrationSchemaConstants.URI + " but found " + uris + " instead.", foundItem);
	}

	public void testUnselectedNamespacesForPageExistenceBeansFile() throws Exception {
		cEditor = openFileInEditor("src/beans-config.xml");
		assertNotNull("Could not open a configuration editor.", cEditor);

		AbstractConfigFormPage page = cEditor.getFormPage(NamespacesFormPage.ID);
		cEditor.setActivePage(page.getId());
		assertNotNull("Could not load namespaces page.", page.getMasterPart());

		CountDownLatch latch = ((NamespacesMasterPart) page.getMasterPart()).getLazyInitializationLatch();
		assertTrue("Table initialization did not complete before timeout.", latch.await(30, TimeUnit.SECONDS));
		StsTestUtil.waitForDisplay();

		CheckboxTableViewer checkViewer = (CheckboxTableViewer) page.getMasterPart().getViewer();
		for (TableItem item : checkViewer.getTable().getItems()) {
			INamespaceDefinition def = (INamespaceDefinition) item.getData();
			if (!item.getChecked()) {
				assertNull(cEditor.getFormPageForUri(def.getNamespaceURI()));
			}
		}
	}

	public void testUnselectedNamespacesForPageExistenceIntegrationFile() throws Exception {
		cEditor = openFileInEditor("src/integration-config.xml");
		assertNotNull("Could not open a configuration editor.", cEditor);

		AbstractConfigFormPage page = cEditor.getFormPage(NamespacesFormPage.ID);
		cEditor.setActivePage(page.getId());
		assertNotNull("Could not load namespaces page.", page.getMasterPart());

		CountDownLatch latch = ((NamespacesMasterPart) page.getMasterPart()).getLazyInitializationLatch();
		assertTrue("Table initialization did not complete before timeout.", latch.await(30, TimeUnit.SECONDS));
		StsTestUtil.waitForDisplay();

		CheckboxTableViewer checkViewer = (CheckboxTableViewer) page.getMasterPart().getViewer();
		for (TableItem item : checkViewer.getTable().getItems()) {
			INamespaceDefinition def = (INamespaceDefinition) item.getData();
			if (!item.getChecked()) {
				assertNull(cEditor.getFormPageForUri(def.getNamespaceURI()));
			}
		}
	}

	public void testUnselectedNamespacesForPageExistenceScopedFile() throws Exception {
		cEditor = openFileInEditor("src/scoped-config.xml");
		assertNotNull("Could not open a configuration editor.", cEditor);

		AbstractConfigFormPage page = cEditor.getFormPage(NamespacesFormPage.ID);
		cEditor.setActivePage(page.getId());
		assertNotNull("Could not load namespaces page.", page.getMasterPart());

		CountDownLatch latch = ((NamespacesMasterPart) page.getMasterPart()).getLazyInitializationLatch();
		assertTrue("Table initialization did not complete before timeout.", latch.await(30, TimeUnit.SECONDS));
		StsTestUtil.waitForDisplay();

		CheckboxTableViewer checkViewer = (CheckboxTableViewer) page.getMasterPart().getViewer();
		for (TableItem item : checkViewer.getTable().getItems()) {
			INamespaceDefinition def = (INamespaceDefinition) item.getData();
			if (!item.getChecked()) {
				assertNull(cEditor.getFormPageForUri(def.getNamespaceURI()));
			}
		}
	}

}
