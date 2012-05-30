/******************************************************************************************
 * Copyright (c) 2010 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.flow.actions;

import java.util.List;

import org.eclipse.gef.ui.actions.SelectionAction;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMElement;

import com.springsource.sts.config.flow.AbstractConfigGraphicalEditor;
import com.springsource.sts.config.flow.parts.ActivityPart;

/**
 * @author Leo Dos Santos
 */
@SuppressWarnings("restriction")
public class ShowSourceAction extends SelectionAction {

	public static String SHOW_SOURCE_ID = "ShowSource"; //$NON-NLS-1$

	public ShowSourceAction(IWorkbenchPart part) {
		super(part);
		setId(SHOW_SOURCE_ID);
		setText(Messages.ShowSourceAction_SHOW_SOURCE_ACTION_LABEL);
	}

	@Override
	protected boolean calculateEnabled() {
		List parts = getSelectedObjects();
		if (parts.isEmpty()) {
			return false;
		}
		return (parts.get(0) instanceof ActivityPart);
	}

	@Override
	public void run() {
		IWorkbenchPart editor = getWorkbenchPart();
		ActivityPart part = (ActivityPart) getSelectedObjects().get(0);
		if (editor instanceof AbstractConfigGraphicalEditor) {
			AbstractConfigGraphicalEditor graph = (AbstractConfigGraphicalEditor) editor;
			IDOMElement element = part.getModelElement().getInput();
			int start = element.getStartOffset();
			int length = element.getLength();
			graph.getEditor().getSourcePage().selectAndReveal(start, length);
		}
	}

}
