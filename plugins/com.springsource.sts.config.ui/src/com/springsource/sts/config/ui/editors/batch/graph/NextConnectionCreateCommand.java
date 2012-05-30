/******************************************************************************************
 * Copyright (c) 2009 - 2010 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.ui.editors.batch.graph;

import org.eclipse.ui.texteditor.ITextEditor;

import com.springsource.sts.config.core.schemas.BatchSchemaConstants;
import com.springsource.sts.config.flow.model.commands.AbstractConnectionCreateCommand;

/**
 * @author Leo Dos Santos
 */
public class NextConnectionCreateCommand extends AbstractConnectionCreateCommand {

	protected String oldTargetId;

	protected String targetId;

	public NextConnectionCreateCommand(ITextEditor textEditor, int lineStyle) {
		super(textEditor, lineStyle);
	}

	@Override
	public boolean canExecute() {
		if (super.canExecute()) {
			oldTargetId = sourceElement.getAttribute(BatchSchemaConstants.ATTR_TO);
			targetId = targetElement.getAttribute(BatchSchemaConstants.ATTR_ID);
			if (targetId != null && targetId.trim().length() != 0) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void execute() {
		sourceElement.setAttribute(BatchSchemaConstants.ATTR_TO, targetId);
	}

}
