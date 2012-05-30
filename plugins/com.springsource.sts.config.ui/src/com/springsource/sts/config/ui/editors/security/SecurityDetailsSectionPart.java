/******************************************************************************************
 * Copyright (c) 2010 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.ui.editors.security;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMElement;

import com.springsource.sts.config.core.contentassist.providers.BeanReferenceContentProposalProvider;
import com.springsource.sts.config.core.schemas.SecuritySchemaConstants;
import com.springsource.sts.config.ui.editors.AbstractConfigEditor;
import com.springsource.sts.config.ui.editors.SpringConfigDetailsSectionPart;
import com.springsource.sts.config.ui.widgets.TextAttribute;
import com.springsource.sts.config.ui.widgets.TextAttributeProposalAdapter;

/**
 * @author Leo Dos Santos
 */
@SuppressWarnings("restriction")
public class SecurityDetailsSectionPart extends SpringConfigDetailsSectionPart {

	public SecurityDetailsSectionPart(AbstractConfigEditor editor, IDOMElement input, Composite parent,
			FormToolkit toolkit) {
		super(editor, input, parent, toolkit);
	}

	@Override
	protected boolean addCustomAttribute(Composite client, String attr, boolean required) {
		// SecurityContentAssistProcessor and SecurityHyperlinkDetector
		if (SecuritySchemaConstants.ATTR_ACCESS_DECISION_MANAGER_REF.equals(attr)
				|| SecuritySchemaConstants.ATTR_ENTRY_POINT_REF.equals(attr)
				|| SecuritySchemaConstants.ATTR_USER_SERVICE_REF.equals(attr)
				|| SecuritySchemaConstants.ATTR_TOKEN_REPOSITORY_REF.equals(attr)
				|| SecuritySchemaConstants.ATTR_DATA_SOURCE_REF.equals(attr)
				|| SecuritySchemaConstants.ATTR_SERVER_REF.equals(attr)
				|| SecuritySchemaConstants.ATTR_CACHE_REF.equals(attr) || SecuritySchemaConstants.ATTR_REF.equals(attr)) {
			TextAttribute attrControl = createBeanAttribute(client, attr, required);
			addWidget(attrControl);
			addAdapter(new TextAttributeProposalAdapter(attrControl, new BeanReferenceContentProposalProvider(
					getInput(), attr, true)));
			return true;
		}
		return super.addCustomAttribute(client, attr, required);
	}

}
