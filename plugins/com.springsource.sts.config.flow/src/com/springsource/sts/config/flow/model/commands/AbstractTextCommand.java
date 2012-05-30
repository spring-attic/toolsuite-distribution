/******************************************************************************************
 * Copyright (c) 2010 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.flow.model.commands;

import org.eclipse.gef.commands.Command;
import org.eclipse.jface.action.IAction;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.texteditor.ITextEditor;

import com.springsource.sts.config.core.contentassist.SpringConfigContentAssistProcessor;

/**
 * @author Leo Dos Santos
 */
@SuppressWarnings("restriction")
public abstract class AbstractTextCommand extends Command {

	protected SpringConfigContentAssistProcessor processor;

	private final ITextEditor textEditor;

	public AbstractTextCommand(ITextEditor textEditor) {
		this.textEditor = textEditor;
		processor = new SpringConfigContentAssistProcessor();
	}

	@Override
	public void dispose() {
		processor.release();
		super.dispose();
	}

	@Override
	public void redo() {
		IAction action = textEditor.getAction(ActionFactory.REDO.getId());
		action.run();
	}

	@Override
	public void undo() {
		IAction action = textEditor.getAction(ActionFactory.UNDO.getId());
		action.run();
	}

}
