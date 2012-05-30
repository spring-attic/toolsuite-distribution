/******************************************************************************************
 * Copyright (c) 2010 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.flow.model.commands;

import org.eclipse.ui.texteditor.ITextEditor;

import com.springsource.sts.config.core.schemas.BeansSchemaConstants;
import com.springsource.sts.config.flow.parts.FixedConnectionAnchor;

/**
 * @author Leo Dos Santos
 */
public abstract class FixedConnectionCreateCommand extends AbstractConnectionCreateCommand {

	protected String oldId;

	protected String id;

	private String attr = null;

	protected FixedConnectionAnchor sourceAnchor = null;

	protected FixedConnectionAnchor targetAnchor = null;

	public FixedConnectionCreateCommand(ITextEditor textEditor, int lineStyle) {
		super(textEditor, lineStyle);
	}

	@Override
	public boolean canExecute() {
		if (super.canExecute()) {
			if (sourceAnchor == null && targetAnchor == null) {
				return false;
			}
			if (sourceAnchor != null && targetAnchor != null) {
				return doesCreateNewElement();
			}
			if (sourceAnchor != null) {
				attr = sourceAnchor.getConnectionLabel();
				oldId = sourceElement.getAttribute(attr);
				id = targetElement.getAttribute(BeansSchemaConstants.ATTR_ID);
			}
			else {
				attr = targetAnchor.getConnectionLabel();
				oldId = targetElement.getAttribute(attr);
				id = sourceElement.getAttribute(BeansSchemaConstants.ATTR_ID);
			}
			if (id != null && id.trim().length() != 0) {
				return true;
			}
		}
		return false;
	}

	protected abstract void createNewElement();

	protected abstract boolean doesCreateNewElement();

	@Override
	public void execute() {
		if (sourceAnchor != null && targetAnchor != null && doesCreateNewElement()) {
			createNewElement();
		}
		else if (sourceAnchor != null) {
			sourceElement.setAttribute(attr, id);
		}
		else {
			targetElement.setAttribute(attr, id);
		}
	}

	public void setSourceAnchor(FixedConnectionAnchor anchor) {
		sourceAnchor = anchor;
	}

	public void setTargetAnchor(FixedConnectionAnchor anchor) {
		targetAnchor = anchor;
	}

}
