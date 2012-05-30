/******************************************************************************************
 * Copyright (c) 2009 - 2010 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.tests.flow.parts;

import java.util.List;

import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;
import org.eclipse.swtbot.swt.finder.finders.UIThreadRunnable;
import org.eclipse.swtbot.swt.finder.results.VoidResult;

import com.springsource.sts.config.core.schemas.BatchSchemaConstants;
import com.springsource.sts.config.flow.AbstractConfigGraphicalEditor;
import com.springsource.sts.config.flow.parts.ActivityDiagramPart;
import com.springsource.sts.config.tests.AbstractConfigUiTestCase;
import com.springsource.sts.config.tests.util.StsBotConfigEditor;
import com.springsource.sts.config.tests.util.gef.EditPartMatcherFactory;
import com.springsource.sts.config.tests.util.gef.StsBotGefEditor;

/**
 * @author Leo Dos Santos
 */
public class ActivityDiagramPartUiTest extends AbstractConfigUiTestCase {

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
		List<SWTBotGefEditPart> parts = gEditor.editParts(EditPartMatcherFactory
				.editPartOfType(ActivityDiagramPart.class));
		assertFalse(parts.isEmpty());

		SWTBotGefEditPart diagramPart = parts.get(0);
		assertEquals(2, diagramPart.children().size());

		gEditor.activateTool(BatchSchemaConstants.ELEM_SPLIT);
		diagramPart.click();

		gEditor.editParts(EditPartMatcherFactory.editPartOfType(ActivityDiagramPart.class));
		diagramPart = parts.get(0);
		assertEquals(2, diagramPart.children().size());

	}

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
		List<SWTBotGefEditPart> parts = gEditor.editParts(EditPartMatcherFactory
				.editPartOfType(ActivityDiagramPart.class));
		assertFalse(parts.isEmpty());

		SWTBotGefEditPart diagramPart = parts.get(0);
		assertEquals(2, diagramPart.children().size());

		gEditor.activateTool(BatchSchemaConstants.ELEM_JOB);
		diagramPart.click();

		gEditor.editParts(EditPartMatcherFactory.editPartOfType(ActivityDiagramPart.class));
		diagramPart = parts.get(0);
		assertEquals(3, diagramPart.children().size());
	}

}
