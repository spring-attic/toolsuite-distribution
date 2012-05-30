/******************************************************************************************
 * Copyright (c) 2008 - 2009 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.quickfix.proposals;

import org.eclipse.jdt.internal.ui.JavaPluginImages;
import org.eclipse.jface.text.IDocument;
import org.eclipse.swt.graphics.Image;
import org.eclipse.wst.sse.core.StructuredModelManager;
import org.eclipse.wst.sse.core.internal.provisional.IStructuredModel;
import org.eclipse.wst.sse.core.internal.provisional.text.IStructuredDocument;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMNode;
import org.eclipse.wst.xml.core.internal.provisional.format.FormatProcessorXML;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.springsource.sts.config.core.schemas.BeansSchemaConstants;

/**
 * Quick fix proposal for removing <constructor-arg> from a bean
 * @author Terry Denney
 * @author Leo Dos Santos
 * @author Christian Dupuis
 * @since 2.0
 */
public class RemoveConstructorArgQuickFixProposal extends BeanAttributeQuickFixProposal {

	private final int numAdditionalParams;

	private final IDOMNode beanNode;

	private final String label;

	public RemoveConstructorArgQuickFixProposal(int offset, int length, boolean missingEndQuote,
			int numAdditionalParams, IDOMNode beanNode, String label) {
		super(offset, length, missingEndQuote);
		this.numAdditionalParams = numAdditionalParams;
		this.beanNode = beanNode;
		this.label = label;
	}

	@Override
	public void applyQuickFix(IDocument document) {
		IStructuredModel model = null;
		try {
			if (document instanceof IStructuredDocument) {
				model = StructuredModelManager.getModelManager().getModelForEdit((IStructuredDocument) document);
				model.beginRecording(this);
			}

			NodeList childNodes = beanNode.getChildNodes();
			int numRemoved = 0;
			for (int i = childNodes.getLength() - 1; i >= 0; i--) {
				Node child = childNodes.item(i);
				String nodeName = child.getNodeName();
				if (nodeName != null && nodeName.equals(BeansSchemaConstants.ELEM_CONSTRUCTOR_ARG)) {
					beanNode.removeChild(child);
					numRemoved++;
					if (numRemoved >= numAdditionalParams) {
						break;
					}
				}
			}

			new FormatProcessorXML().formatNode(beanNode);
		}

		finally {
			if (model != null) {
				model.endRecording(this);
				model.releaseFromEdit();
				model = null;
			}
		}
	}

	public String getDisplayString() {
		return label;
	}

	public Image getImage() {
		return JavaPluginImages.get(JavaPluginImages.IMG_CORRECTION_REMOVE);
	}

}
