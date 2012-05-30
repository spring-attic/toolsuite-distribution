/******************************************************************************************
 * Copyright (c) 2009 - 2010 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.flow.model.commands;

import org.eclipse.ui.texteditor.ITextEditor;

import com.springsource.sts.config.flow.model.Activity;
import com.springsource.sts.config.flow.model.StructuredActivity;
import com.springsource.sts.config.flow.model.Transition;

/**
 * @author Leo Dos Santos
 * @author Christian Dupuis
 */
public class CreateAndAssignSourceCommand extends AbstractTextCommand {

	private StructuredActivity parent;

	private Activity child;

	private Activity source;

	public CreateAndAssignSourceCommand(ITextEditor textEditor) {
		super(textEditor);
	}

	/**
	 * @see org.eclipse.gef.commands.Command#execute()
	 */
	@Override
	public void execute() {
		parent.addChild(child);
		new Transition(source, child);
	}

	/**
	 * Sets the Activity to create
	 * @param activity the Activity to create
	 */
	public void setChild(Activity activity) {
		child = activity;
	}

	/**
	 * Sets the parent ActivityDiagram
	 * @param sa the parent
	 */
	public void setParent(StructuredActivity sa) {
		parent = sa;
	}

	/**
	 * Sets the source to the passed activity
	 * @param activity the source
	 */
	public void setSource(Activity activity) {
		source = activity;
	}

}
