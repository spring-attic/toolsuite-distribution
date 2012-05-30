/******************************************************************************************
 * Copyright (c) 2008 - 2009 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.core.formatting;

import org.eclipse.wst.sse.core.internal.format.IStructuredFormatter;
import org.eclipse.wst.xml.core.internal.provisional.format.FormatProcessorXML;
import org.w3c.dom.Node;

/**
 * @author Leo Dos Santos
 * @author Christian Dupuis
 */
@SuppressWarnings("restriction")
public class ShallowFormatProcessorXML extends FormatProcessorXML {

	@Override
	protected IStructuredFormatter getFormatter(Node node) {
		IStructuredFormatter formatter = null;
		if (node != null) {
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				formatter = new ShallowElementNodeFormatter();
			}
			if (node.getNodeType() == Node.DOCUMENT_NODE) {
				formatter = new ShallowDocumentNodeFormatter();
			}
		}

		if (formatter != null) {
			formatter.setFormatPreferences(getFormatPreferences());
			formatter.setProgressMonitor(fProgressMonitor);
			return formatter;
		}
		return super.getFormatter(node);
	}

}
