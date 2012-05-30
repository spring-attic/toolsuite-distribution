/******************************************************************************************
 * Copyright (c) 2011 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.ui.editors.webflow.graph;

import java.util.Arrays;
import java.util.List;

import org.eclipse.ui.texteditor.ITextEditor;

import com.springsource.sts.config.core.schemas.WebFlowSchemaConstants;
import com.springsource.sts.config.flow.model.commands.RefactoringRenameCommand;

/**
 * @author Leo Dos Santos
 */
public class RenameStateCommand extends RefactoringRenameCommand {

	public RenameStateCommand(ITextEditor textEditor) {
		super(textEditor);
	}

	@Override
	protected List<String> getAttributesToCheck() {
		return Arrays.asList(new String[] { WebFlowSchemaConstants.ATTR_ELSE, WebFlowSchemaConstants.ATTR_THEN,
				WebFlowSchemaConstants.ATTR_TO });
	}
}
