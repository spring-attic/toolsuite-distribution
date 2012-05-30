/******************************************************************************************
 * Copyright (c) 2008 - 2010 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.ui.hyperlinks;

import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PlatformUI;
import org.springframework.ide.eclipse.beans.ui.editor.hyperlink.NodeElementHyperlink;
import org.w3c.dom.Node;

import com.springsource.sts.config.ui.editors.AbstractConfigEditor;

/**
 * @author Leo Dos Santos
 * @author Christian Dupuis
 */
public class ExtendedNodeElementHyperlink extends NodeElementHyperlink {

	private final Node node;

	public ExtendedNodeElementHyperlink(Node node, IRegion region, IRegion targetRegion, ITextViewer viewer) {
		super(region, targetRegion, viewer);
		this.node = node;
	}

	@Override
	public void open() {
		super.open();
		IEditorPart editor = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
		if (editor instanceof AbstractConfigEditor) {
			AbstractConfigEditor cEditor = (AbstractConfigEditor) editor;
			cEditor.revealElement(node);
		}
	}

}
