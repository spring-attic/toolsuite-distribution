/******************************************************************************************
 * Copyright (c) 2010 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.ui.editors.integration.graph;

import org.eclipse.draw2d.Graphics;
import org.eclipse.ui.texteditor.ITextEditor;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMDocument;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMElement;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMModel;

import com.springsource.sts.config.core.ConfigCoreUtils;
import com.springsource.sts.config.core.formatting.ShallowFormatProcessorXML;
import com.springsource.sts.config.core.schemas.IntegrationSchemaConstants;
import com.springsource.sts.config.flow.model.commands.AbstractConnectionCreateCommand;

/**
 * @author Leo Dos Santos
 */
@SuppressWarnings("restriction")
public class RecipientConnectionCreateCommand extends AbstractConnectionCreateCommand {

	private final ShallowFormatProcessorXML formatter;

	protected String oldTargetId;

	protected String targetId;

	public RecipientConnectionCreateCommand(ITextEditor textEditor) {
		super(textEditor, Graphics.LINE_DASH);
		this.formatter = new ShallowFormatProcessorXML();
	}

	@Override
	public boolean canExecute() {
		if (super.canExecute()) {
			oldTargetId = sourceElement.getAttribute(IntegrationSchemaConstants.ATTR_CHANNEL);
			targetId = targetElement.getAttribute(IntegrationSchemaConstants.ATTR_ID);
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
			IDOMElement recipient = (IDOMElement) document.createElement(IntegrationSchemaConstants.ELEM_RECIPIENT);
			recipient.setPrefix(ConfigCoreUtils.getPrefixForNamespaceUri(document, IntegrationSchemaConstants.URI));
			sourceElement.appendChild(recipient);
			processor.insertDefaultAttributes(recipient);
			formatter.formatNode(recipient);
			formatter.formatNode(sourceElement);
			recipient.setAttribute(IntegrationSchemaConstants.ATTR_CHANNEL, targetId);
			model.endRecording(this);
		}
	}

}
