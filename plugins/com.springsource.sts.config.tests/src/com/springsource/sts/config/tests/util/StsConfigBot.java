/******************************************************************************************
 * Copyright (c) 2012 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.tests.util;

import static org.eclipse.swtbot.eclipse.finder.matchers.WidgetMatcherFactory.withPartId;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swtbot.eclipse.finder.finders.WorkbenchContentsFinder;
import org.eclipse.swtbot.swt.finder.exceptions.WidgetNotFoundException;
import org.eclipse.ui.IEditorReference;
import org.hamcrest.Matcher;
import org.springsource.ide.eclipse.commons.tests.util.swtbot.StsBot;

import com.springsource.sts.config.ui.editors.SpringConfigEditor;

/**
 * @author Leo Dos Santos
 */
public class StsConfigBot extends StsBot {

	private final WorkbenchContentsFinder workbenchContentsFinder = new WorkbenchContentsFinder();

	public StsBotConfigEditor activeConfigEditor() {
		IEditorReference editor = workbenchContentsFinder.findActiveEditor();
		if (!(editor.getEditor(false) instanceof SpringConfigEditor)) {
			throw new WidgetNotFoundException("There is no active editor");
		}
		return new StsBotConfigEditor(editor, this);
	}

	public List<StsBotConfigEditor> configEditors() {
		Matcher<?> matcher = withPartId(SpringConfigEditor.ID_EDITOR);
		return configEditors(matcher);
	}

	public List<StsBotConfigEditor> configEditors(Matcher<?> matcher) {
		List<IEditorReference> editorReferences = workbenchContentsFinder.findEditors(matcher);

		List<StsBotConfigEditor> editorBots = new ArrayList<StsBotConfigEditor>();
		for (IEditorReference editorReference : editorReferences) {
			editorBots.add(new StsBotConfigEditor(editorReference, this));
		}

		return editorBots;
	}

}
