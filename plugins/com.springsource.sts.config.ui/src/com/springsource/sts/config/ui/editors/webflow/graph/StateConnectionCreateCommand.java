/******************************************************************************************
 * Copyright (c) 2010 - 2011 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.ui.editors.webflow.graph;

import org.eclipse.ui.texteditor.ITextEditor;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMDocument;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMElement;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMModel;

import com.springsource.sts.config.core.ConfigCoreUtils;
import com.springsource.sts.config.core.formatting.ShallowFormatProcessorXML;
import com.springsource.sts.config.core.schemas.WebFlowSchemaConstants;
import com.springsource.sts.config.flow.model.commands.AbstractConnectionCreateCommand;

/**
 * @author Leo Dos Santos
 */
@SuppressWarnings("restriction")
public class StateConnectionCreateCommand extends AbstractConnectionCreateCommand {

	private final ShallowFormatProcessorXML formatter;

	protected String oldTargetId;

	protected String targetId;

	public StateConnectionCreateCommand(ITextEditor textEditor, int lineStyle) {
		super(textEditor, lineStyle);
		this.formatter = new ShallowFormatProcessorXML();
	}

	@Override
	public boolean canExecute() {
		if (super.canExecute()) {
			oldTargetId = sourceElement.getAttribute(WebFlowSchemaConstants.ATTR_TO);
			targetId = targetElement.getAttribute(WebFlowSchemaConstants.ATTR_ID);
			if (targetId != null && targetId.trim().length() != 0) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void execute() {
		IDOMDocument document = (IDOMDocument) sourceElement.getOwnerDocument();
		IDOMModel model = document.getModel();
		if (model != null) {
			model.beginRecording(this);
			IDOMElement transition = (IDOMElement) document.createElement(WebFlowSchemaConstants.ELEM_TRANSITION);
			transition.setPrefix(ConfigCoreUtils.getPrefixForNamespaceUri(document, WebFlowSchemaConstants.URI));
			sourceElement.appendChild(transition);
			processor.insertDefaultAttributes(transition);
			formatter.formatNode(transition);
			formatter.formatNode(sourceElement);
			transition.setAttribute(WebFlowSchemaConstants.ATTR_TO, targetId);
			model.endRecording(this);
		}
	}

}
