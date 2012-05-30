/******************************************************************************************
 * Copyright (c) 2008 - 2010 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.ui.hyperlinks;

import java.util.Iterator;

import org.eclipse.core.resources.IFile;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.hyperlink.IHyperlink;
import org.springframework.ide.eclipse.beans.core.model.IBean;
import org.springframework.ide.eclipse.beans.ui.editor.hyperlink.BeanHyperlinkCalculator;
import org.springframework.ide.eclipse.beans.ui.editor.util.BeansEditorUtils;
import org.w3c.dom.Node;

/**
 * @author Leo Dos Santos
 * @author Christian Dupuis
 * @deprecated Use {@link BeanHyperlinkCalculator} instead.
 */
@Deprecated
public class ExtendedBeanHyperlinkCalculator extends BeanHyperlinkCalculator {

	@Override
	public IHyperlink createHyperlink(String name, String target, Node node, Node parentNode, IDocument document,
			ITextViewer textViewer, IRegion hyperlinkRegion, IRegion cursor) {
		IFile file = BeansEditorUtils.getFile(document);
		Node bean = BeansEditorUtils.getFirstReferenceableNodeById(node.getOwnerDocument(), target, file);
		if (bean != null) {
			IRegion region = getHyperlinkRegion(bean);
			return new ExtendedNodeElementHyperlink(bean, hyperlinkRegion, region, textViewer);
		}
		else {
			// assume this is an external reference
			Iterator<?> beans = BeansEditorUtils.getBeansFromConfigSets(file).iterator();
			while (beans.hasNext()) {
				IBean modelBean = (IBean) beans.next();
				if (modelBean.getElementName().equals(target)) {
					return new ExtendedExternalBeanHyperlink(modelBean, hyperlinkRegion);
				}
			}
		}
		return null;
	}

}
