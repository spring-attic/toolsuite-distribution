/******************************************************************************************
 * Copyright (c) 2009 - 2010 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.ui.editors.batch.graph.model;

import java.util.Arrays;
import java.util.List;

import org.eclipse.wst.xml.core.internal.provisional.document.IDOMElement;

import com.springsource.sts.config.core.schemas.BatchSchemaConstants;
import com.springsource.sts.config.flow.model.AbstractConfigFlowDiagram;
import com.springsource.sts.config.flow.model.Activity;

/**
 * @author Leo Dos Santos
 * @author Christian Dupuis
 */
@SuppressWarnings("restriction")
public class StopModelElement extends Activity {

	public StopModelElement() {
		super();
	}

	public StopModelElement(IDOMElement input, AbstractConfigFlowDiagram diagram) {
		super(input, diagram);
	}

	@Override
	public String getInputName() {
		return BatchSchemaConstants.ELEM_STOP;
	}

	@Override
	public List<String> getPrimaryOutgoingAttributes() {
		return Arrays.asList(BatchSchemaConstants.ATTR_RESTART);
	}

	@Override
	protected void internalSetName() {
		String localName = getInput().getLocalName();
		String onAttr = BatchSchemaConstants.ATTR_ON;
		String onVal = getInput().getAttribute(onAttr);
		if (onVal == null) {
			onVal = ""; //$NON-NLS-1$
		}
		String name = localName + " " + onAttr + "=\"" + onVal + "\""; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		setName(name);
	}

}
