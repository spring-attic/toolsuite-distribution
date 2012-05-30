/******************************************************************************************
 * Copyright (c) 2010 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.ui.editors.webflow.graph;

import java.util.List;

import org.eclipse.ui.texteditor.ITextEditor;

import com.springsource.sts.config.core.schemas.WebFlowSchemaConstants;
import com.springsource.sts.config.flow.model.commands.RenameActivityCommand;

/**
 * @author Leo Dos Santos
 */
public class RenameTransitionCommand extends RenameActivityCommand {

	public RenameTransitionCommand(ITextEditor textEditor) {
		super(textEditor);
	}

	@Override
	public boolean canExecute() {
		input = source.getInput();
		if (input == null) {
			return false;
		}

		List<String> attrs = processor.getAttributeNames(input);
		return attrs.contains(WebFlowSchemaConstants.ATTR_ON);
	}

	@Override
	public void execute() {
		input.setAttribute(WebFlowSchemaConstants.ATTR_ON, name);
	}

}
