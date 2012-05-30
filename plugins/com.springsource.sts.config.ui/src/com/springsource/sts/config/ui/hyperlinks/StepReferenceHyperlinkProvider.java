/******************************************************************************************
 * Copyright (c) 2009 - 2010 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.ui.hyperlinks;

import org.eclipse.jface.text.ITextViewer;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMElement;
import org.springframework.ide.eclipse.batch.ui.editor.hyperlink.batch.StepReferenceHyperlinkCalculator;
import org.springframework.ide.eclipse.beans.ui.editor.hyperlink.IHyperlinkCalculator;

/**
 * An {@link XmlBackedHyperlinkProvider} that uses
 * {@link StepReferenceHyperlinkCalculator} as its hyperlink calculator.
 * @author Leo Dos Santos
 * @since 2.1.0
 */
@SuppressWarnings("restriction")
public class StepReferenceHyperlinkProvider extends XmlBackedHyperlinkProvider {

	/**
	 * Constructs a hyperlink provider for an XML attribute.
	 * 
	 * @param textViewer the text viewer containing the XML source
	 * @param input the XML element to serve as the model for this hyperlink
	 * @param attrName the name of the attribute to compute a hyperlink action
	 */
	public StepReferenceHyperlinkProvider(ITextViewer textViewer, IDOMElement input, String attrName) {
		super(textViewer, input, attrName);
	}

	@Override
	protected IHyperlinkCalculator createHyperlinkCalculator() {
		return new StepReferenceHyperlinkCalculator();
	}

}
