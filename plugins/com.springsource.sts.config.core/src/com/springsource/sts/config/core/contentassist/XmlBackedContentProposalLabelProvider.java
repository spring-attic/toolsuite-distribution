/******************************************************************************************
 * Copyright (c) 2008 - 2009 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.core.contentassist;

import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

/**
 * Label provider for all content proposal providers extending
 * {@link XmlBackedContentProposalProvider}.
 * @author Leo Dos Santos
 * @author Christian Dupuis
 * @since 2.0.0
 */
public class XmlBackedContentProposalLabelProvider extends LabelProvider {

	@Override
	public Image getImage(Object element) {
		if (element instanceof XmlBackedContentProposal) {
			return ((XmlBackedContentProposal) element).getImage();
		}
		return null;
	}

	@Override
	public String getText(Object element) {
		if (element instanceof XmlBackedContentProposal) {
			return ((XmlBackedContentProposal) element).getLabel();
		}
		return super.getText(element);
	}

}
