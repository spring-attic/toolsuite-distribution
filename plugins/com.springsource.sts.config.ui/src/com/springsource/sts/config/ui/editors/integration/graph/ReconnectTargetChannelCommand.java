/******************************************************************************************
 * Copyright (c) 2010 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.ui.editors.integration.graph;

import org.eclipse.ui.texteditor.ITextEditor;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMElement;

import com.springsource.sts.config.core.schemas.IntegrationSchemaConstants;
import com.springsource.sts.config.flow.model.Activity;
import com.springsource.sts.config.flow.model.Transition;
import com.springsource.sts.config.flow.model.commands.AbstractTextCommand;

/**
 * @author Leo Dos Santos
 */
@SuppressWarnings("restriction")
public class ReconnectTargetChannelCommand extends AbstractTextCommand {

	private Activity source;

	private Activity target;

	private Transition transition;

	private IDOMElement sourceElement;

	private IDOMElement targetElement;

	private IDOMElement transitionElement;

	private Activity oldTarget;

	private IDOMElement oldTargetElement;

	private String oldTargetId;

	private String targetId;

	public ReconnectTargetChannelCommand(ITextEditor textEditor) {
		super(textEditor);
	}

	@Override
	public boolean canExecute() {
		if (source == null || target == null || source.equals(target) || oldTarget.equals(target)) {
			return false;
		}

		sourceElement = source.getInput();
		targetElement = target.getInput();
		oldTargetElement = oldTarget.getInput();
		transitionElement = (IDOMElement) transition.getInput();
		if (sourceElement == null || targetElement == null || oldTargetElement == null) {
			return false;
		}
		if (!(transition.getInput() instanceof IDOMElement)) {
			return false;
		}

		oldTargetId = transitionElement.getAttribute(IntegrationSchemaConstants.ATTR_CHANNEL);
		targetId = targetElement.getAttribute(IntegrationSchemaConstants.ATTR_ID);
		if (targetId != null && targetId.trim().length() > 0) {
			return true;
		}
		return false;
	}

	@Override
	public void execute() {
		transitionElement.setAttribute(IntegrationSchemaConstants.ATTR_CHANNEL, targetId);
	}

	public void setTarget(Activity activity) {
		target = activity;
	}

	public void setTransition(Transition trans) {
		transition = trans;
		source = trans.source;
		oldTarget = trans.target;
	}

}
