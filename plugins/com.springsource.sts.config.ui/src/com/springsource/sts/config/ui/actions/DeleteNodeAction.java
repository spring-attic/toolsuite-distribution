/******************************************************************************************
 * Copyright (c) 2008 - 2010 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.ui.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.wst.sse.ui.internal.StructuredTextViewer;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMModel;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMNode;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import com.springsource.sts.config.core.formatting.ShallowFormatProcessorXML;

/**
 * @author Leo Dos Santos
 * @author Christian Dupuis
 */
@SuppressWarnings("restriction")
public class DeleteNodeAction extends Action {

	private final StructuredTextViewer textView;

	private final IDOMNode node;

	private final ShallowFormatProcessorXML formatter;

	public DeleteNodeAction(StructuredTextViewer textView, IDOMNode node) {
		super();
		this.textView = textView;
		this.node = node;
		formatter = new ShallowFormatProcessorXML();
		setText(Messages.getString("DeleteNodeAction.DELETE_ELEMENT_PREFIX") + node.getNodeName() + Messages.getString("DeleteNodeAction.DELETE_ELEMENT_SUFFIX")); //$NON-NLS-1$ //$NON-NLS-2$
	}

	@Override
	public boolean isEnabled() {
		if (node == null) {
			return false;
		}
		Element root = node.getOwnerDocument().getDocumentElement();
		if (node.equals(root)) {
			return false;
		}
		return super.isEnabled();
	}

	@Override
	public void run() {
		IDOMModel model = node.getModel();
		Node parent = node.getParentNode();
		if (textView != null && model != null && parent != null) {
			model.beginRecording(textView);
			parent.removeChild(node);
			formatter.formatNode(parent);
			model.endRecording(textView);
		}
	}

}
