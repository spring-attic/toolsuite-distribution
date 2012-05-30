/******************************************************************************************
 * Copyright (c) 2008 - 2010 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.ui.actions;

import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.mylyn.commons.ui.CommonImages;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMElement;

import com.springsource.sts.config.core.contentassist.SpringConfigContentAssistProcessor;

/**
 * @author Leo Dos Santos
 * @author Christian Dupuis
 */
@SuppressWarnings("restriction")
public class ExpandNodeAction extends TreeViewerNodeAction {

	public ExpandNodeAction(TreeViewer treeViewer, SpringConfigContentAssistProcessor processor) {
		super(treeViewer, processor);
		setImageDescriptor(CommonImages.EXPAND_ALL);
	}

	@Override
	public void run() {
		super.run();
		IDOMElement element = getElementFromTreeItem(getSelectedTreeItem());
		if (treeViewer != null && element != null) {
			treeViewer.expandToLevel(element, TreeViewer.ALL_LEVELS);
		}
	}

}
