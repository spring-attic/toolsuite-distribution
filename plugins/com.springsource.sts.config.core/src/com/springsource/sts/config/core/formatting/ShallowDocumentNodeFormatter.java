/******************************************************************************************
 * Copyright (c) 2008 - 2009 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.core.formatting;

import org.eclipse.wst.sse.core.internal.format.IStructuredFormatContraints;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMNode;
import org.eclipse.wst.xml.core.internal.provisional.format.DocumentNodeFormatter;

/**
 * @author Leo Dos Santos
 * @author Christian Dupuis
 */
@SuppressWarnings("restriction")
public class ShallowDocumentNodeFormatter extends DocumentNodeFormatter {

	@Override
	protected void formatChildren(IDOMNode node, IStructuredFormatContraints formatContraints) {
		// no-op
	}

}
