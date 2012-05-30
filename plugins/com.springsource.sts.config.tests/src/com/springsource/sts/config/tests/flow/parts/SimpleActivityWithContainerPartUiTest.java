/******************************************************************************************
 * Copyright (c) 2009 - 2010 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.tests.flow.parts;

import java.util.Collections;
import java.util.List;

import org.eclipse.jface.bindings.keys.KeyStroke;
import org.eclipse.swt.SWT;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefConnectionEditPart;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;
import org.eclipse.swtbot.swt.finder.finders.UIThreadRunnable;
import org.eclipse.swtbot.swt.finder.results.VoidResult;
import org.hamcrest.Matcher;
import org.hamcrest.core.AllOf;

import com.springsource.sts.config.core.schemas.BatchSchemaConstants;
import com.springsource.sts.config.flow.AbstractConfigGraphicalEditor;
import com.springsource.sts.config.flow.parts.SimpleActivityWithContainerPart;
import com.springsource.sts.config.flow.parts.StructuredActivityPart;
import com.springsource.sts.config.tests.AbstractConfigUiTestCase;
import com.springsource.sts.config.tests.util.StsBotConfigEditor;
import com.springsource.sts.config.tests.util.gef.EditPartMatcherFactory;
import com.springsource.sts.config.tests.util.gef.StsBotGefEditor;

/**
 * @author Leo Dos Santos
 */
public class SimpleActivityWithContainerPartUiTest extends AbstractConfigUiTestCase {

	@SuppressWarnings("unchecked")
	public void testDeleteActivityPart() throws Exception {
		cEditor = openFileInEditor("src/batch-config.xml");
		assertNotNull("Could not open a configuration editor.", cEditor);

		UIThreadRunnable.syncExec(new VoidResult() {
			public void run() {
				AbstractConfigGraphicalEditor page = cEditor.getGraphicalEditorForUri(BatchSchemaConstants.URI);
				assertNotNull("Could not load batch-graph page.", page);
				cEditor.setActiveEditor(page);
			}
		});

		Matcher matcher = AllOf.allOf(EditPartMatcherFactory.editPartOfType(SimpleActivityWithContainerPart.class),
				EditPartMatcherFactory.withLabel("limitDecision"));

		StsBotConfigEditor editor = getBot().activeConfigEditor();
		StsBotGefEditor gEditor = editor.toGefEditorFromUri(BatchSchemaConstants.URI);
		List<SWTBotGefEditPart> parts = gEditor.editParts(matcher);
		assertFalse(parts.isEmpty());

		SWTBotGefEditPart activityPart = parts.get(0);
		activityPart.select();
		gEditor.pressShortcut(KeyStroke.getInstance(SWT.DEL));

		for (int i = 0; i < 3 && gEditor.editParts(matcher).size() > 0; i++) {
			Thread.sleep(1000);
		}
		parts = gEditor.editParts(matcher);
		assertEquals(Collections.emptyList(), parts);
	}

	@SuppressWarnings("unchecked")
	public void testDeleteContainerPart() throws Exception {
		cEditor = openFileInEditor("src/batch-config.xml");
		assertNotNull("Could not open a configuration editor.", cEditor);

		UIThreadRunnable.syncExec(new VoidResult() {
			public void run() {
				AbstractConfigGraphicalEditor page = cEditor.getGraphicalEditorForUri(BatchSchemaConstants.URI);
				assertNotNull("Could not load batch-graph page.", page);
				cEditor.setActiveEditor(page);
			}
		});

		StsBotConfigEditor editor = getBot().activeConfigEditor();
		StsBotGefEditor gEditor = editor.toGefEditorFromUri(BatchSchemaConstants.URI);
		List<SWTBotGefEditPart> parts = gEditor.editParts(AllOf.allOf(
				EditPartMatcherFactory.editPartOfType(SimpleActivityWithContainerPart.class),
				EditPartMatcherFactory.withLabel("limitDecision")));
		assertFalse(parts.isEmpty());

		SWTBotGefEditPart activityPart = parts.get(0);
		SWTBotGefConnectionEditPart transitionPart = activityPart.sourceConnections().get(0);
		SWTBotGefEditPart containerPart = transitionPart.target();
		containerPart.select();
		gEditor.pressShortcut(KeyStroke.getInstance(SWT.DEL));

		parts = gEditor.editParts(AllOf.allOf(
				EditPartMatcherFactory.editPartOfType(SimpleActivityWithContainerPart.class),
				EditPartMatcherFactory.withLabel("limitDecision")));
		assertEquals(Collections.emptyList(), parts);
	}

	@SuppressWarnings("unchecked")
	public void testDeleteTransitionPart() throws Exception {
		cEditor = openFileInEditor("src/batch-config.xml");
		assertNotNull("Could not open a configuration editor.", cEditor);

		UIThreadRunnable.syncExec(new VoidResult() {
			public void run() {
				AbstractConfigGraphicalEditor page = cEditor.getGraphicalEditorForUri(BatchSchemaConstants.URI);
				assertNotNull("Could not load batch-graph page.", page);
				cEditor.setActiveEditor(page);
			}
		});

		StsBotConfigEditor editor = getBot().activeConfigEditor();
		StsBotGefEditor gEditor = editor.toGefEditorFromUri(BatchSchemaConstants.URI);
		List<SWTBotGefEditPart> parts = gEditor.editParts(AllOf.allOf(
				EditPartMatcherFactory.editPartOfType(SimpleActivityWithContainerPart.class),
				EditPartMatcherFactory.withLabel("limitDecision")));
		assertFalse(parts.isEmpty());

		SWTBotGefEditPart activityPart = parts.get(0);
		SWTBotGefConnectionEditPart transitionPart = activityPart.sourceConnections().get(0);
		transitionPart.select();
		gEditor.pressShortcut(KeyStroke.getInstance(SWT.DEL));

		parts = gEditor.editParts(AllOf.allOf(
				EditPartMatcherFactory.editPartOfType(SimpleActivityWithContainerPart.class),
				EditPartMatcherFactory.withLabel("limitDecision")));
		assertEquals(Collections.emptyList(), parts);
	}

	@SuppressWarnings("unchecked")
	public void testDropInvalidPart() throws Exception {
		cEditor = openFileInEditor("src/batch-config.xml");
		assertNotNull("Could not open a configuration editor.", cEditor);

		UIThreadRunnable.syncExec(new VoidResult() {
			public void run() {
				AbstractConfigGraphicalEditor page = cEditor.getGraphicalEditorForUri(BatchSchemaConstants.URI);
				assertNotNull("Could not load batch-graph page.", page);
				cEditor.setActiveEditor(page);
			}
		});

		StsBotConfigEditor editor = getBot().activeConfigEditor();
		StsBotGefEditor gEditor = editor.toGefEditorFromUri(BatchSchemaConstants.URI);

		List<SWTBotGefEditPart> parts = gEditor.editParts(AllOf.allOf(
				EditPartMatcherFactory.editPartOfType(SimpleActivityWithContainerPart.class),
				EditPartMatcherFactory.withLabel("step1")));
		assertFalse(parts.isEmpty());
		SWTBotGefEditPart activityPart = parts.get(0);

		parts = gEditor.editParts(AllOf.allOf(EditPartMatcherFactory.editPartOfType(StructuredActivityPart.class),
				EditPartMatcherFactory.withLabel("step1")));
		assertEquals(Collections.emptyList(), parts);

		gEditor.activateTool(BatchSchemaConstants.ELEM_STEP);
		activityPart.click();

		parts = gEditor.editParts(AllOf.allOf(EditPartMatcherFactory.editPartOfType(StructuredActivityPart.class),
				EditPartMatcherFactory.withLabel("step1")));
		assertEquals(Collections.emptyList(), parts);
	}

	@SuppressWarnings("unchecked")
	public void testDropValidPart() throws Exception {
		cEditor = openFileInEditor("src/batch-config.xml");
		assertNotNull("Could not open a configuration editor.", cEditor);

		UIThreadRunnable.syncExec(new VoidResult() {
			public void run() {
				AbstractConfigGraphicalEditor page = cEditor.getGraphicalEditorForUri(BatchSchemaConstants.URI);
				assertNotNull("Could not load batch-graph page.", page);
				cEditor.setActiveEditor(page);
			}
		});

		StsBotConfigEditor editor = getBot().activeConfigEditor();
		StsBotGefEditor gEditor = editor.toGefEditorFromUri(BatchSchemaConstants.URI);

		List<SWTBotGefEditPart> parts = gEditor.editParts(AllOf.allOf(
				EditPartMatcherFactory.editPartOfType(SimpleActivityWithContainerPart.class),
				EditPartMatcherFactory.withLabel("step1")));
		assertFalse(parts.isEmpty());
		SWTBotGefEditPart activityPart = parts.get(0);

		parts = gEditor.editParts(AllOf.allOf(EditPartMatcherFactory.editPartOfType(StructuredActivityPart.class),
				EditPartMatcherFactory.withLabel("step1")));
		assertEquals(Collections.emptyList(), parts);

		gEditor.activateTool(BatchSchemaConstants.ELEM_NEXT);
		activityPart.click();

		parts = gEditor.editParts(AllOf.allOf(EditPartMatcherFactory.editPartOfType(StructuredActivityPart.class),
				EditPartMatcherFactory.withLabel("step1")));
		assertFalse(parts.isEmpty());

		SWTBotGefEditPart containerPart = parts.get(0);
		assertFalse(containerPart.children().isEmpty());
	}
}
