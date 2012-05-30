/******************************************************************************************
 * Copyright (c) 2008 - 2010 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.ui.actions;

import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.wst.sse.ui.internal.StructuredTextViewer;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMElement;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMModel;
import org.w3c.dom.Node;

import com.springsource.sts.config.core.contentassist.SpringConfigContentAssistProcessor;

/**
 * @author Leo Dos Santos
 * @author Christian Dupuis
 */
@SuppressWarnings("restriction")
public class LowerNodeAction extends TreeViewerNodeAction {

	private final StructuredTextViewer textView;

	public LowerNodeAction(TreeViewer treeViewer, SpringConfigContentAssistProcessor processor,
			StructuredTextViewer textView) {
		super(treeViewer, processor);
		this.textView = textView;
	}

	@Override
	public void run() {
		super.run();
		IDOMElement selection = getElementFromTreeItem(getSelectedTreeItem());
		IDOMElement nextSib = getElementFromTreeItem(getNextTreeItem());
		if (textView != null && selection != null) {
			IDOMModel model = selection.getModel();
			Node parent = selection.getParentNode();
			if (model != null && nextSib != null && parent != null) {
				model.beginRecording(textView);
				parent.insertBefore(nextSib, selection);
				formatter.formatNode(nextSib);
				formatter.formatNode(nextSib.getParentNode());
				model.endRecording(textView);
			}
		}
	}

}
