/******************************************************************************************
 * Copyright (c) 2010 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.flow.model.commands;

import org.eclipse.ui.texteditor.ITextEditor;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMAttr;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMDocument;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMElement;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMModel;

import com.springsource.sts.config.core.schemas.BeansSchemaConstants;
import com.springsource.sts.config.flow.model.Activity;
import com.springsource.sts.config.flow.model.Transition;
import com.springsource.sts.config.flow.parts.FixedConnectionAnchor;

/**
 * @author Leo Dos Santos
 */
@SuppressWarnings("restriction")
public class ReconnectFixedTargetCommand extends AbstractTextCommand {

	private String id;

	private String attr;

	private Activity source;

	private Activity target;

	private Transition transition;

	private IDOMElement sourceElement;

	private IDOMElement targetElement;

	private FixedConnectionAnchor targetAnchor;

	private Activity oldTarget;

	private IDOMElement oldTargetElement;

	private IDOMAttr transitionAttr;

	public ReconnectFixedTargetCommand(ITextEditor textEditor) {
		super(textEditor);
	}

	@Override
	public boolean canExecute() {
		if (source == null || target == null || source.equals(target)) {
			return false;
		}

		sourceElement = source.getInput();
		targetElement = target.getInput();
		oldTargetElement = oldTarget.getInput();
		if (sourceElement == null || targetElement == null || oldTargetElement == null) {
			return false;
		}

		if (!(transition.getInput() instanceof IDOMAttr)) {
			return false;
		}
		transitionAttr = (IDOMAttr) transition.getInput();

		if (targetAnchor != null && transitionAttr.getOwnerElement().equals(sourceElement)) {
			return false;
		}
		if (targetAnchor == null && transitionAttr.getOwnerElement().equals(oldTargetElement)) {
			return false;
		}

		if (targetAnchor != null) {
			attr = targetAnchor.getConnectionLabel();
			id = transitionAttr.getValue();
		}
		else {
			attr = transitionAttr.getName();
			id = targetElement.getAttribute(BeansSchemaConstants.ATTR_ID);
		}

		if (id != null && id.trim().length() != 0) {
			return true;
		}
		return false;
	}

	@Override
	public void execute() {
		if (targetAnchor != null) {
			IDOMDocument document = (IDOMDocument) transitionAttr.getOwnerDocument();
			IDOMModel model = document.getModel();
			model.beginRecording(this);
			oldTargetElement.removeAttribute(transitionAttr.getName());
			targetElement.setAttribute(attr, id);
			model.endRecording(this);
		}
		else {
			sourceElement.setAttribute(attr, id);
		}
	}

	public void setTarget(Activity activity) {
		target = activity;
	}

	public void setTargetAnchor(FixedConnectionAnchor anchor) {
		targetAnchor = anchor;
	}

	public void setTransition(Transition trans) {
		transition = trans;
		source = trans.source;
		oldTarget = trans.target;
	}

}
