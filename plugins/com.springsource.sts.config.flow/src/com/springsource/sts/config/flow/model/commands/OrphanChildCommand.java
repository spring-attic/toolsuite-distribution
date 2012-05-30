/******************************************************************************************
 * Copyright (c) 2009 - 2010 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.flow.model.commands;

import org.eclipse.ui.texteditor.ITextEditor;

import com.springsource.sts.config.flow.model.Activity;
import com.springsource.sts.config.flow.model.StructuredActivity;

/**
 * OrphanChildCommand
 * @author Leo Dos Santos
 * @author Christian Dupuis
 */
public class OrphanChildCommand extends AbstractTextCommand {

	private StructuredActivity parent;

	private Activity child;

	public OrphanChildCommand(ITextEditor textEditor) {
		super(textEditor);
	}

	/**
	 * @see org.eclipse.gef.commands.Command#execute()
	 */
	@Override
	public void execute() {
		parent.removeChild(child);
	}

	/**
	 * Sets the child to the passed Activity
	 * @param child the child
	 */
	public void setChild(Activity child) {
		this.child = child;
	}

	/**
	 * Sets the parent to the passed StructuredActivity
	 * @param parent the parent
	 */
	public void setParent(StructuredActivity parent) {
		this.parent = parent;
	}

}
