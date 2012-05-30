/******************************************************************************************
 * Copyright (c) 2010 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.ui.editors.integration;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMElement;

import com.springsource.sts.config.core.contentassist.providers.ClassContentProposalProvider;
import com.springsource.sts.config.core.schemas.IntegrationSchemaConstants;
import com.springsource.sts.config.ui.editors.AbstractConfigEditor;
import com.springsource.sts.config.ui.editors.SpringConfigDetailsSectionPart;
import com.springsource.sts.config.ui.widgets.TextAttribute;
import com.springsource.sts.config.ui.widgets.TextAttributeProposalAdapter;

/**
 * @author Leo Dos Santos
 */
@SuppressWarnings("restriction")
public class IntegrationDetailsSectionPart extends SpringConfigDetailsSectionPart {

	public IntegrationDetailsSectionPart(AbstractConfigEditor editor, IDOMElement input, Composite parent,
			FormToolkit toolkit) {
		super(editor, input, parent, toolkit);
	}

	@Override
	protected boolean addCustomAttribute(Composite client, String attr, boolean required) {
		if (IntegrationSchemaConstants.ATTR_DATATYPE.equals(attr)) {
			TextAttribute attrControl = createClassAttribute(client, attr, true, required);
			addWidget(attrControl);
			addAdapter(new TextAttributeProposalAdapter(attrControl, new ClassContentProposalProvider(getInput(), attr)));
			return true;
		}
		return super.addCustomAttribute(client, attr, required);
	}

}
