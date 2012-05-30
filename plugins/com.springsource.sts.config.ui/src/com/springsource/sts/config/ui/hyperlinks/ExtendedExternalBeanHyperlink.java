/******************************************************************************************
 * Copyright (c) 2008 - 2010 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.ui.hyperlinks;

import org.eclipse.core.resources.IFile;
import org.eclipse.jface.text.IRegion;
import org.eclipse.ui.IEditorPart;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMDocument;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMNode;
import org.springframework.ide.eclipse.beans.core.internal.model.BeansModelUtils;
import org.springframework.ide.eclipse.beans.core.model.IBean;
import org.springframework.ide.eclipse.beans.ui.BeansUIUtils;
import org.springframework.ide.eclipse.beans.ui.editor.hyperlink.ExternalBeanHyperlink;
import org.springframework.ide.eclipse.core.model.IModelElement;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.springsource.sts.config.ui.editors.AbstractConfigEditor;

/**
 * @author Leo Dos Santos
 * @author Christian Dupuis
 */
@SuppressWarnings("restriction")
public class ExtendedExternalBeanHyperlink extends ExternalBeanHyperlink {

	private final IBean bean;

	private AbstractConfigEditor cEditor;

	private IFile file;

	private IDOMDocument doc;

	public ExtendedExternalBeanHyperlink(IBean bean, IRegion region) {
		super(bean, region);
		this.bean = bean;
	}

	@Override
	public void open() {
		IEditorPart editor = BeansUIUtils.openInEditor(bean);
		if (editor instanceof AbstractConfigEditor) {
			cEditor = (AbstractConfigEditor) editor;
			file = cEditor.getResourceFile();
			doc = cEditor.getDomDocument();
			traverseNode(doc);
		}
	}

	private void traverseNode(Node node) {
		int startOffset = ((IDOMNode) node).getStartOffset();
		int endOffset = ((IDOMNode) node).getEndOffset();
		int start = doc.getStructuredDocument().getLineOfOffset(startOffset);
		int end = doc.getStructuredDocument().getLineOfOffset(endOffset);
		IModelElement modelElement = BeansModelUtils.getMostSpecificModelElement(start, end, file, null);
		if (bean.equals(modelElement)) {
			cEditor.revealElement(node);
		}
		else if (node.hasChildNodes()) {
			NodeList children = node.getChildNodes();
			for (int i = 0; i < children.getLength(); i++) {
				traverseNode(children.item(i));
			}
		}
	}

}
