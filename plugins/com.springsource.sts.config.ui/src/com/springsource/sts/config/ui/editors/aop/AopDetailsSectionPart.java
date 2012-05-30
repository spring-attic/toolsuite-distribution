/******************************************************************************************
 * Copyright (c) 2010 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.ui.editors.aop;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMElement;

import com.springsource.sts.config.core.contentassist.providers.AdviceMethodContentProposalProvider;
import com.springsource.sts.config.core.contentassist.providers.BeanReferenceContentProposalProvider;
import com.springsource.sts.config.core.contentassist.providers.ClassContentProposalProvider;
import com.springsource.sts.config.core.contentassist.providers.DefaultImplContentProposalProvider;
import com.springsource.sts.config.core.contentassist.providers.PointcutReferenceContentProposalProvider;
import com.springsource.sts.config.core.schemas.AopSchemaConstants;
import com.springsource.sts.config.ui.editors.AbstractConfigEditor;
import com.springsource.sts.config.ui.editors.SpringConfigDetailsSectionPart;
import com.springsource.sts.config.ui.widgets.TextAttribute;
import com.springsource.sts.config.ui.widgets.TextAttributeProposalAdapter;

/**
 * @author Leo Dos Santos
 */
@SuppressWarnings("restriction")
public class AopDetailsSectionPart extends SpringConfigDetailsSectionPart {

	public AopDetailsSectionPart(AbstractConfigEditor editor, IDOMElement input, Composite parent, FormToolkit toolkit) {
		super(editor, input, parent, toolkit);
	}

	@Override
	protected boolean addCustomAttribute(Composite client, String attr, boolean required) {
		// AopContentAssistProcessor and AopHyperlinkDetector
		String elem = getInput().getLocalName();
		if ((AopSchemaConstants.ELEM_ASPECT.equals(elem) && AopSchemaConstants.ATTR_REF.equals(attr))
				|| (AopSchemaConstants.ELEM_ADVISOR.equals(elem) && AopSchemaConstants.ATTR_ADVICE_REF.equals(attr))
				|| (AopSchemaConstants.ELEM_INCLUDE.equals(elem) && AopSchemaConstants.ATTR_NAME.equals(attr))
				|| AopSchemaConstants.ATTR_DELEGATE_REF.equals(attr)) {
			TextAttribute attrControl = createBeanAttribute(client, attr, required);
			addWidget(attrControl);
			addAdapter(new TextAttributeProposalAdapter(attrControl, new BeanReferenceContentProposalProvider(
					getInput(), attr)));
			return true;
		}
		if (AopSchemaConstants.ATTR_IMPLEMENT_INTERFACE.equals(attr)) {
			TextAttribute attrControl = createClassAttribute(client, attr, true, required);
			addWidget(attrControl);
			addAdapter(new TextAttributeProposalAdapter(attrControl, new ClassContentProposalProvider(getInput(), attr,
					true)));
			return true;
		}
		if (AopSchemaConstants.ATTR_POINTCUT_REF.equals(attr)) {
			TextAttribute attrControl = createPointcutAttribute(client, attr, required);
			addWidget(attrControl);
			addAdapter(new TextAttributeProposalAdapter(attrControl, new PointcutReferenceContentProposalProvider(
					getInput(), attr)));
			return true;
		}
		if (AopSchemaConstants.ATTR_DEFAULT_IMPL.equals(attr)) {
			TextAttribute attrControl = createClassAttribute(client, attr, false, required);
			addWidget(attrControl);
			addAdapter(new TextAttributeProposalAdapter(attrControl, new DefaultImplContentProposalProvider(getInput(),
					attr)));
			return true;
		}
		if (AopSchemaConstants.ATTR_METHOD.equals(attr)) {
			TextAttribute attrControl = createAdviceMethodAttribute(client, attr, required);
			addWidget(attrControl);
			addAdapter(new TextAttributeProposalAdapter(attrControl, new AdviceMethodContentProposalProvider(
					getInput(), attr)));
			return true;
		}
		return super.addCustomAttribute(client, attr, required);
	}

}
