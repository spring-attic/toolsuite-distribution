/******************************************************************************************
 * Copyright (c) 2009 - 2010 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.tests.ui.editors;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;
import org.eclipse.wst.sse.ui.internal.StructuredTextViewer;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMModel;
import org.springsource.ide.eclipse.commons.tests.util.StsTestUtil;

import com.springsource.sts.config.core.preferences.SpringConfigPreferenceConstants;
import com.springsource.sts.config.core.schemas.BatchSchemaConstants;
import com.springsource.sts.config.core.schemas.BeansSchemaConstants;
import com.springsource.sts.config.flow.AbstractConfigGraphicalEditor;
import com.springsource.sts.config.tests.AbstractConfigTestCase;
import com.springsource.sts.config.ui.ConfigUiPlugin;
import com.springsource.sts.config.ui.actions.LowerNodeAction;
import com.springsource.sts.config.ui.editors.AbstractConfigEditor;
import com.springsource.sts.config.ui.editors.AbstractConfigFormPage;
import com.springsource.sts.config.ui.editors.SpringConfigEditor;
import com.springsource.sts.config.ui.editors.namespaces.NamespacesFormPage;
import com.springsource.sts.config.ui.editors.overview.OverviewFormPage;

/**
 * @author Leo Dos Santos
 */
@SuppressWarnings("restriction")
public class SpringConfigEditorTest extends AbstractConfigTestCase {

	public void testActivePagePreference() throws Exception {
		IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
		cEditor = openFileInEditor("src/batch-config.xml");
		assertNotNull("Could not open a configuration editor.", cEditor);

		IEditorInput input = cEditor.getEditorInput();
		cEditor.setActiveEditor(cEditor.getSourcePage());
		page.closeEditor(cEditor, false);

		cEditor = (AbstractConfigEditor) page.openEditor(input, SpringConfigEditor.ID_EDITOR);
		assertTrue(cEditor.getActiveEditor().equals(cEditor.getSourcePage()));
		cEditor.setActivePage(cEditor.getFormPageForUri(BatchSchemaConstants.URI).getId());
		page.closeEditor(cEditor, false);

		cEditor = (AbstractConfigEditor) page.openEditor(input, SpringConfigEditor.ID_EDITOR);
		assertTrue(cEditor.getActivePageInstance().equals(cEditor.getFormPageForUri(BatchSchemaConstants.URI)));
		cEditor.setActiveEditor(cEditor.getGraphicalEditorForUri(BatchSchemaConstants.URI));
		page.closeEditor(cEditor, false);

		cEditor = (AbstractConfigEditor) page.openEditor(input, SpringConfigEditor.ID_EDITOR);
		assertTrue(cEditor.getActiveEditor().equals(cEditor.getGraphicalEditorForUri(BatchSchemaConstants.URI)));
		cEditor.setActivePage(OverviewFormPage.ID);
		page.closeEditor(cEditor, false);

		cEditor = (AbstractConfigEditor) page.openEditor(input, SpringConfigEditor.ID_EDITOR);
		assertTrue(cEditor.getActivePageInstance().equals(cEditor.getFormPage(OverviewFormPage.ID)));
		cEditor.setActivePage(NamespacesFormPage.ID);
		page.closeEditor(cEditor, false);

		cEditor = (AbstractConfigEditor) page.openEditor(input, SpringConfigEditor.ID_EDITOR);
		assertTrue(cEditor.getActivePageInstance().equals(cEditor.getFormPage(NamespacesFormPage.ID)));
	}

	public void testGraphicalPagesPreference() throws Exception {
		IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
		cEditor = openFileInEditor("src/batch-config.xml");
		assertNotNull("Could not open a configuration editor.", cEditor);

		IEditorInput input = cEditor.getEditorInput();
		AbstractConfigGraphicalEditor gEditor = cEditor.getGraphicalEditorForUri(BatchSchemaConstants.URI);
		assertNotNull(gEditor);
		page.closeEditor(cEditor, false);

		ConfigUiPlugin.getDefault().getPreferenceStore()
				.setValue(SpringConfigPreferenceConstants.PREF_ENABLE_GEF_PAGES, false);
		cEditor = (AbstractConfigEditor) page.openEditor(input, SpringConfigEditor.ID_EDITOR);
		gEditor = cEditor.getGraphicalEditorForUri(BatchSchemaConstants.URI);
		assertNull(gEditor);
		page.closeEditor(cEditor, false);

		ConfigUiPlugin.getDefault().getPreferenceStore()
				.setValue(SpringConfigPreferenceConstants.PREF_ENABLE_GEF_PAGES, true);
		cEditor = (AbstractConfigEditor) page.openEditor(input, SpringConfigEditor.ID_EDITOR);
		gEditor = cEditor.getGraphicalEditorForUri(BatchSchemaConstants.URI);
		assertNotNull(gEditor);

		ConfigUiPlugin.getDefault().getPreferenceStore()
				.setValue(SpringConfigPreferenceConstants.PREF_ENABLE_GEF_PAGES, false);
		gEditor = cEditor.getGraphicalEditorForUri(BatchSchemaConstants.URI);
		assertNull(gEditor);
	}

	public void testModelLeaksEditAndSave() throws Exception {
		cEditor = openFileInEditor("src/batch-config.xml");
		assertNotNull("Could not open a configuration editor.", cEditor);

		// the config editor holds onto one model reference for edit and its
		// source page holds onto another reference
		IDOMModel model = cEditor.getDomDocument().getModel();
		int references = model.getReferenceCountForEdit();
		assertEquals(2, references);

		AbstractConfigFormPage page = cEditor.getFormPage(OverviewFormPage.ID);
		StructuredTextViewer textView = cEditor.getTextViewer();
		cEditor.setActivePage(page.getId());
		assertNotNull("Could not load overview page.", page.getMasterPart());

		TreeViewer treeViewer = (TreeViewer) page.getMasterPart().getViewer();
		TreeItem root = treeViewer.getTree().getItem(0);

		LowerNodeAction lower = new LowerNodeAction(treeViewer, cEditor.getXmlProcessor(), textView);
		treeViewer.getTree().setSelection(root.getItem(0));
		lower.run();
		assertEquals(references, model.getReferenceCountForEdit());

		treeViewer.getTree().setSelection(root.getItem(0));
		lower.run();
		assertEquals(references, model.getReferenceCountForEdit());

		cEditor.doSave(new NullProgressMonitor());
		StsTestUtil.waitForEditor(cEditor);
		assertEquals(references, model.getReferenceCountForEdit());

		// calling cEditor.close(false) here will fail the following assertion
		// due to an asynchronous call in that method
		PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().closeEditor(cEditor, false);
		StsTestUtil.waitForDisplay();
		assertEquals(0, model.getReferenceCountForEdit());

		// wait for background threads to free the model
		for (int i = 0; i < 3 && model.getReferenceCount() > 0; i++) {
			Thread.sleep(1000);
		}
		assertEquals(0, model.getReferenceCount());
	}

	public void testModelLeaksVisitSeveralPages() throws Exception {
		cEditor = openFileInEditor("src/batch-config.xml");
		assertNotNull("Could not open a configuration editor.", cEditor);

		// config editor holds onto one model reference for edit and its source
		// page holds onto another reference
		final IDOMModel model = cEditor.getDomDocument().getModel();
		int references = model.getReferenceCountForEdit();
		assertEquals(2, references);

		cEditor.setActiveEditor(cEditor.getFormPageForUri(BatchSchemaConstants.URI));
		assertEquals(references, model.getReferenceCountForEdit());
		cEditor.setActiveEditor(cEditor.getGraphicalEditorForUri(BatchSchemaConstants.URI));
		assertEquals(references, model.getReferenceCountForEdit());
		cEditor.setActiveEditor(cEditor.getFormPageForUri(BeansSchemaConstants.URI));
		assertEquals(references, model.getReferenceCountForEdit());
		cEditor.setActiveEditor(cEditor.getSourcePage());
		assertEquals(references, model.getReferenceCountForEdit());

		// calling cEditor.close(false) here will fail the following assertion
		// due to an asynchronous call in that method
		PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().closeEditor(cEditor, false);
		StsTestUtil.waitForDisplay();
		assertEquals(0, model.getReferenceCountForEdit());

		// wait for background threads to free the model
		for (int i = 0; i < 3 && model.getReferenceCount() > 0; i++) {
			Thread.sleep(1000);
		}
		assertEquals(0, model.getReferenceCount());
	}

}
