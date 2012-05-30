/******************************************************************************************
 * Copyright (c) 2009 - 2010 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.flow.model.commands;

import org.eclipse.ui.texteditor.ITextEditor;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMAttr;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMElement;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMNode;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import com.springsource.sts.config.flow.model.Activity;
import com.springsource.sts.config.flow.model.Transition;

/**
 * Handles the deletion of connections between Activities.
 * @author Leo Dos Santos
 * @author Christian Dupuis
 */
@SuppressWarnings("restriction")
public class DeleteConnectionCommand extends AbstractTextCommand {

	@SuppressWarnings("unused")
	private Activity source;

	@SuppressWarnings("unused")
	private Activity target;

	private Transition transition;

	private IDOMNode transInput;

	public DeleteConnectionCommand(ITextEditor textEditor) {
		super(textEditor);
	}

	@Override
	public boolean canExecute() {
		transInput = transition.getInput();
		if (transInput == null) {
			return false;
		}
		return super.canExecute();
	}

	/**
	 * @see org.eclipse.gef.commands.Command#execute()
	 */
	@Override
	public void execute() {
		if (transInput instanceof IDOMAttr) {
			IDOMAttr attr = (IDOMAttr) transInput;
			Element elem = attr.getOwnerElement();
			if (elem != null) {
				elem.removeAttributeNode(attr);
			}
		}
		else if (transInput instanceof IDOMElement) {
			IDOMElement elem = (IDOMElement) transInput;
			Node parent = elem.getParentNode();
			if (parent != null) {
				parent.removeChild(elem);
			}
		}
	}

	/**
	 * Sets the source activity
	 * @param activity the source
	 */
	public void setSource(Activity activity) {
		source = activity;
	}

	/**
	 * Sets the target activity
	 * @param activity the target
	 */
	public void setTarget(Activity activity) {
		target = activity;
	}

	/**
	 * Sets the transition
	 * @param transition the transition
	 */
	public void setTransition(Transition transition) {
		this.transition = transition;
	}

}
