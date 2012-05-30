/******************************************************************************************
 * Copyright (c) 2010 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.ui.editors.jee;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMElement;

import com.springsource.sts.config.core.contentassist.providers.ClassContentProposalProvider;
import com.springsource.sts.config.core.schemas.JeeSchemaConstants;
import com.springsource.sts.config.ui.editors.AbstractConfigEditor;
import com.springsource.sts.config.ui.editors.SpringConfigDetailsSectionPart;
import com.springsource.sts.config.ui.widgets.TextAttribute;
import com.springsource.sts.config.ui.widgets.TextAttributeProposalAdapter;

/**
 * @author Leo Dos Santos
 */
@SuppressWarnings("restriction")
public class JeeDetailsSectionPart extends SpringConfigDetailsSectionPart {

	public JeeDetailsSectionPart(AbstractConfigEditor editor, IDOMElement input, Composite parent, FormToolkit toolkit) {
		super(editor, input, parent, toolkit);
	}

	@Override
	protected boolean addCustomAttribute(Composite client, String attr, boolean required) {
		// JeeContentAssistProcessor and JeeHyperlinkDetector
		if (JeeSchemaConstants.ATTR_EXPECTED_TYPE.equals(attr) || JeeSchemaConstants.ATTR_PROXY_INTERFACE.equals(attr)
				|| JeeSchemaConstants.ATTR_BUSINESS_INTERFACE.equals(attr)
				|| JeeSchemaConstants.ATTR_HOME_INTERFACE.equals(attr)) {
			TextAttribute attrControl = createClassAttribute(client, attr, true, required);
			addWidget(attrControl);
			addAdapter(new TextAttributeProposalAdapter(attrControl, new ClassContentProposalProvider(getInput(), attr,
					true)));
			return true;
		}
		return super.addCustomAttribute(client, attr, required);
	}

}
