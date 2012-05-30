/******************************************************************************************
 * Copyright (c) 2009 - 2010 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.tests.flow.parts;

import java.util.List;

import org.eclipse.jface.bindings.keys.KeyStroke;
import org.eclipse.swt.SWT;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefConnectionEditPart;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;
import org.eclipse.swtbot.swt.finder.finders.UIThreadRunnable;
import org.eclipse.swtbot.swt.finder.results.VoidResult;
import org.hamcrest.core.AllOf;

import com.springsource.sts.config.core.schemas.BatchSchemaConstants;
import com.springsource.sts.config.flow.AbstractConfigGraphicalEditor;
import com.springsource.sts.config.flow.parts.SimpleActivityPart;
import com.springsource.sts.config.flow.parts.StructuredActivityPart;
import com.springsource.sts.config.tests.AbstractConfigUiTestCase;
import com.springsource.sts.config.tests.util.StsBotConfigEditor;
import com.springsource.sts.config.tests.util.gef.EditPartMatcherFactory;
import com.springsource.sts.config.tests.util.gef.StsBotGefEditor;

/**
 * @author Leo Dos Santos
 */
public class TransitionPartUiTest extends AbstractConfigUiTestCase {

	@SuppressWarnings("unchecked")
	public void testDeleteBetweenSimpleActivityParts() throws Exception {
		cEditor = openFileInEditor("src/split-batch.xml");
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
				EditPartMatcherFactory.editPartOfType(SimpleActivityPart.class),
				EditPartMatcherFactory.withLabel("step1")));
		assertFalse(parts.isEmpty());

		SWTBotGefEditPart step1 = parts.get(0);
		SWTBotGefConnectionEditPart transitionPart = step1.sourceConnections().get(0);
		SWTBotGefEditPart step2 = transitionPart.target();
		assertTrue(step2.part() instanceof SimpleActivityPart);

		transitionPart.select();
		gEditor.pressShortcut(KeyStroke.getInstance(SWT.DEL));

		parts = gEditor.editParts(AllOf.allOf(EditPartMatcherFactory.editPartOfType(SimpleActivityPart.class),
				EditPartMatcherFactory.withLabel("step1")));
		assertFalse(parts.isEmpty());
		assertTrue(parts.get(0).sourceConnections().isEmpty());
	}

	@SuppressWarnings("unchecked")
	public void testDeleteBetweenSimpleAndStructuredActivityParts() throws Exception {
		cEditor = openFileInEditor("src/split-batch.xml");
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
				EditPartMatcherFactory.withLabel("split")));
		assertFalse(parts.isEmpty());

		SWTBotGefEditPart split = parts.get(0);
		SWTBotGefConnectionEditPart transitionPart = split.sourceConnections().get(0);
		SWTBotGefEditPart step3 = transitionPart.target();
		assertTrue(step3.part() instanceof SimpleActivityPart);

		transitionPart.select();
		gEditor.pressShortcut(KeyStroke.getInstance(SWT.DEL));

		parts = gEditor.editParts(AllOf.allOf(EditPartMatcherFactory.editPartOfType(StructuredActivityPart.class),
				EditPartMatcherFactory.withLabel("split")));
		assertFalse(parts.isEmpty());
		assertTrue(parts.get(0).sourceConnections().isEmpty());
	}

}
