/******************************************************************************************
 * Copyright (c) 2010 - 2011 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.ui.editors.integration.graph;

import java.util.Arrays;
import java.util.List;

import org.eclipse.ui.texteditor.ITextEditor;

import com.springsource.sts.config.core.schemas.IntegrationSchemaConstants;
import com.springsource.sts.config.flow.model.commands.RefactoringRenameCommand;

/**
 * @author Leo Dos Santos
 */
public class RenameChannelCommand extends RefactoringRenameCommand {

	public RenameChannelCommand(ITextEditor textEditor) {
		super(textEditor);
	}

	@Override
	protected List<String> getAttributesToCheck() {
		return Arrays.asList(new String[] { IntegrationSchemaConstants.ATTR_CHANNEL,
				IntegrationSchemaConstants.ATTR_DEFAULT_CHANNEL,
				IntegrationSchemaConstants.ATTR_DEFAULT_OUTPUT_CHANNEL,
				IntegrationSchemaConstants.ATTR_DEFAULT_REPLY_CHANNEL,
				IntegrationSchemaConstants.ATTR_DEFAULT_REQUEST_CHANNEL,
				IntegrationSchemaConstants.ATTR_DISCARD_CHANNEL, IntegrationSchemaConstants.ATTR_ERROR_CHANNEL,
				IntegrationSchemaConstants.ATTR_INPUT_CHANNEL, IntegrationSchemaConstants.ATTR_OUTPUT_CHANNEL,
				IntegrationSchemaConstants.ATTR_REPLY_CHANNEL, IntegrationSchemaConstants.ATTR_REQUEST_CHANNEL });
	}

}
