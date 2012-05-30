/******************************************************************************************
 * Copyright (c) 2010 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.ui.editors.integration.graph;

import java.util.List;

import org.eclipse.ui.texteditor.ITextEditor;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMDocument;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMElement;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMModel;
import org.w3c.dom.Node;

import com.springsource.sts.config.core.ConfigCoreUtils;
import com.springsource.sts.config.core.formatting.ShallowFormatProcessorXML;
import com.springsource.sts.config.core.schemas.IntegrationSchemaConstants;
import com.springsource.sts.config.flow.model.commands.FixedConnectionCreateCommand;
import com.springsource.sts.config.ui.editors.integration.graph.model.IntegrationDiagram;

/**
 * @author Leo Dos Santos
 */
@SuppressWarnings("restriction")
public class FixedConnectionChannelCreateCommand extends FixedConnectionCreateCommand {

	private final ShallowFormatProcessorXML formatter;

	private IDOMElement parentElement;

	private String inputName;

	public FixedConnectionChannelCreateCommand(ITextEditor textEditor, int lineStyle) {
		super(textEditor, lineStyle);
		this.formatter = new ShallowFormatProcessorXML();
	}

	@Override
	protected void createNewElement() {
		if (parentElement != null) {
			IDOMDocument document = (IDOMDocument) parentElement.getOwnerDocument();
			IDOMElement childElement = (IDOMElement) document.createElement(inputName);
			IDOMModel model = document.getModel();
			if (model != null) {
				model.beginRecording(this);
				parentElement.appendChild(childElement);
				processor.insertDefaultAttributes(childElement);
				id = IntegrationSchemaConstants.ELEM_CHANNEL
						+ ((IntegrationDiagram) source.getDiagram()).getNewChannelId();
				childElement.setAttribute(IntegrationSchemaConstants.ATTR_ID, id);
				formatter.formatNode(childElement);
				formatter.formatNode(childElement.getParentNode());
				sourceElement.setAttribute(sourceAnchor.getConnectionLabel(), id);
				targetElement.setAttribute(targetAnchor.getConnectionLabel(), id);
				model.endRecording(this);
			}
		}
	}

	@Override
	protected boolean doesCreateNewElement() {
		Node parent = sourceElement.getParentNode();
		if (parent instanceof IDOMElement) {
			parentElement = (IDOMElement) parent;
			inputName = IntegrationSchemaConstants.ELEM_CHANNEL;
			String uri = source.getDiagram().getNamespaceUri();
			String prefix = ConfigCoreUtils.getPrefixForNamespaceUri((IDOMDocument) sourceElement.getOwnerDocument(),
					uri);
			if (prefix != null && prefix.length() > 0) {
				inputName = prefix + ":" + inputName; //$NON-NLS-1$
			}
			List<String> children = processor.getChildNames(parentElement);
			if (children.contains(inputName)) {
				return true;
			}
		}
		return false;
	}

}
