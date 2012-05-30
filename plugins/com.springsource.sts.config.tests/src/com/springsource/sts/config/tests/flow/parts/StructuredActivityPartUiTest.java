/******************************************************************************************
 * Copyright (c) 2009 - 2010 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.tests.flow.parts;

import java.util.List;

import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;
import org.eclipse.swtbot.swt.finder.finders.UIThreadRunnable;
import org.eclipse.swtbot.swt.finder.results.VoidResult;
import org.hamcrest.core.AllOf;

import com.springsource.sts.config.core.schemas.BatchSchemaConstants;
import com.springsource.sts.config.flow.AbstractConfigGraphicalEditor;
import com.springsource.sts.config.flow.parts.SequentialActivityPart;
import com.springsource.sts.config.flow.parts.StructuredActivityPart;
import com.springsource.sts.config.tests.AbstractConfigUiTestCase;
import com.springsource.sts.config.tests.util.StsBotConfigEditor;
import com.springsource.sts.config.tests.util.gef.EditPartMatcherFactory;
import com.springsource.sts.config.tests.util.gef.StsBotGefEditor;

/**
 * @author Leo Dos Santos
 */
public class StructuredActivityPartUiTest extends AbstractConfigUiTestCase {

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
				EditPartMatcherFactory.editPartOfType(StructuredActivityPart.class),
				EditPartMatcherFactory.withLabel("limitDecision")));
		assertFalse(parts.isEmpty());

		SWTBotGefEditPart containerPart = parts.get(0);
		assertEquals(2, containerPart.children().size());

		gEditor.activateTool(BatchSchemaConstants.ELEM_STEP);
		containerPart.click();

		parts = gEditor.editParts(AllOf.allOf(EditPartMatcherFactory.editPartOfType(StructuredActivityPart.class),
				EditPartMatcherFactory.withLabel("limitDecision")));
		containerPart = parts.get(0);
		assertEquals(2, containerPart.children().size());
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
				EditPartMatcherFactory.editPartOfType(StructuredActivityPart.class),
				EditPartMatcherFactory.withLabel("limitDecision")));
		assertFalse(parts.isEmpty());

		SWTBotGefEditPart containerPart = parts.get(0);
		assertEquals(2, containerPart.children().size());

		gEditor.activateTool(BatchSchemaConstants.ELEM_NEXT);
		containerPart.click();

		parts = gEditor.editParts(AllOf.allOf(EditPartMatcherFactory.editPartOfType(StructuredActivityPart.class),
				EditPartMatcherFactory.withLabel("limitDecision")));
		containerPart = parts.get(0);
		assertEquals(3, containerPart.children().size());
	}

	public void testPerformOpen() throws Exception {
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
		List<SWTBotGefEditPart> parts = gEditor.editParts(EditPartMatcherFactory
				.editPartOfType(SequentialActivityPart.class));
		assertFalse(parts.isEmpty());

		SWTBotGefEditPart editPart = parts.get(0);
		editPart.doubleClick();

		SWTBotView view = bot.viewById("org.eclipse.ui.views.PropertySheet");
		assertTrue(view.isActive());
		view.close();
	}

}
