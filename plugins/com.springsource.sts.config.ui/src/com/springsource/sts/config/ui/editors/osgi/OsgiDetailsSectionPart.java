/******************************************************************************************
 * Copyright (c) 2010 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.ui.editors.osgi;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMElement;

import com.springsource.sts.config.core.contentassist.providers.BeanReferenceContentProposalProvider;
import com.springsource.sts.config.core.contentassist.providers.ClassContentProposalProvider;
import com.springsource.sts.config.core.contentassist.providers.ReferenceIdContentProposalProvider;
import com.springsource.sts.config.core.contentassist.providers.RegistrationMethodContentProposalProvider;
import com.springsource.sts.config.core.schemas.OsgiSchemaConstants;
import com.springsource.sts.config.ui.editors.AbstractConfigEditor;
import com.springsource.sts.config.ui.editors.SpringConfigDetailsSectionPart;
import com.springsource.sts.config.ui.widgets.TextAttribute;
import com.springsource.sts.config.ui.widgets.TextAttributeProposalAdapter;

/**
 * @author Leo Dos Santos
 */
@SuppressWarnings("restriction")
public class OsgiDetailsSectionPart extends SpringConfigDetailsSectionPart {

	public OsgiDetailsSectionPart(AbstractConfigEditor editor, IDOMElement input, Composite parent, FormToolkit toolkit) {
		super(editor, input, parent, toolkit);
	}

	@Override
	protected boolean addCustomAttribute(Composite client, String attr, boolean required) {
		// OsgiContentAssistProcessor and OsgiHyperlinkDetector
		String elem = getInput().getLocalName();
		if (OsgiSchemaConstants.ATTR_DEPENDS_ON.equals(attr) || OsgiSchemaConstants.ATTR_REF.equals(attr)) {
			TextAttribute attrControl = createBeanAttribute(client, attr, required);
			addWidget(attrControl);
			addAdapter(new TextAttributeProposalAdapter(attrControl, new BeanReferenceContentProposalProvider(
					getInput(), attr, true)));
			return true;
		}
		if (OsgiSchemaConstants.ATTR_INTERFACE.equals(attr)) {
			TextAttribute attrControl = createClassAttribute(client, attr, true, required);
			addWidget(attrControl);
			addAdapter(new TextAttributeProposalAdapter(attrControl, new ClassContentProposalProvider(getInput(), attr)));
			return true;
		}
		if (OsgiSchemaConstants.ATTR_REGISTRATION_METHOD.equals(attr)
				|| OsgiSchemaConstants.ATTR_UNREGISTRATION_METHOD.equals(attr)) {
			TextAttribute attrControl = createRegistrationMethodAttribute(client, attr, required);
			addWidget(attrControl);
			addAdapter(new TextAttributeProposalAdapter(attrControl, new RegistrationMethodContentProposalProvider(
					getInput(), attr)));
			return true;
		}
		if (OsgiSchemaConstants.ELEM_REFERENCE.equals(elem) && OsgiSchemaConstants.ATTR_ID.equals(attr)) {
			TextAttribute attrControl = createTextAttribute(client, attr, required);
			addWidget(attrControl);
			addAdapter(new TextAttributeProposalAdapter(attrControl, new ReferenceIdContentProposalProvider(getInput(),
					attr)));
			return true;
		}
		return super.addCustomAttribute(client, attr, required);
	}

}
