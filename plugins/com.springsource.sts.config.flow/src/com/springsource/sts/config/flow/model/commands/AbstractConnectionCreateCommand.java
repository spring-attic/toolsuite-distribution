/******************************************************************************************
 * Copyright (c) 2009 - 2010 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.flow.model.commands;

import org.eclipse.ui.texteditor.ITextEditor;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMElement;

import com.springsource.sts.config.flow.model.Activity;

/**
 * @author Leo Dos Santos
 * @author Christian Dupuis
 */
@SuppressWarnings("restriction")
public abstract class AbstractConnectionCreateCommand extends AbstractTextCommand {

	protected Activity source;

	protected Activity target;

	protected IDOMElement sourceElement;

	protected IDOMElement targetElement;

	protected int lineStyle;

	public AbstractConnectionCreateCommand(ITextEditor textEditor, int lineStyle) {
		super(textEditor);
		this.lineStyle = lineStyle;
	}

	@Override
	public boolean canExecute() {
		if (source == null || target == null || source.equals(target)) {
			return false;
		}
		sourceElement = source.getInput();
		targetElement = target.getInput();
		if (sourceElement == null || targetElement == null) {
			return false;
		}
		return true;
	}

	public Activity getSource() {
		return source;
	}

	public Activity getTarget() {
		return target;
	}

	public void setSource(Activity activity) {
		source = activity;
	}

	public void setTarget(Activity activity) {
		target = activity;
	}

}
