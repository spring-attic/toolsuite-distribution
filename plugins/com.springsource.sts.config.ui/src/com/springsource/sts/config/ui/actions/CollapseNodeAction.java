/******************************************************************************************
 * Copyright (c) 2008 - 2010 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.ui.actions;

import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.mylyn.commons.ui.CommonImages;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMElement;

import com.springsource.sts.config.core.contentassist.SpringConfigContentAssistProcessor;

/**
 * @author Leo Dos Santos
 * @author Christian Dupuis
 */
@SuppressWarnings("restriction")
public class CollapseNodeAction extends TreeViewerNodeAction {

	public CollapseNodeAction(TreeViewer treeViewer, SpringConfigContentAssistProcessor processor) {
		super(treeViewer, processor);
		setImageDescriptor(CommonImages.COLLAPSE_ALL);
	}

	@Override
	public void run() {
		super.run();
		TreeItem selection = getSelectedTreeItem();
		IDOMElement element = getElementFromTreeItem(selection);
		if (treeViewer != null && element != null) {
			treeViewer.collapseToLevel(element, TreeViewer.ALL_LEVELS);
		}
	}

}
