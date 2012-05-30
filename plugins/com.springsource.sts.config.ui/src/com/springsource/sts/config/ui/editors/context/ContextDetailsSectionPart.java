/******************************************************************************************
 * Copyright (c) 2010 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.ui.editors.context;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMElement;

import com.springsource.sts.config.core.contentassist.providers.BeanReferenceContentProposalProvider;
import com.springsource.sts.config.core.contentassist.providers.ClassContentProposalProvider;
import com.springsource.sts.config.core.contentassist.providers.ClassHierarchyContentProposalProvider;
import com.springsource.sts.config.core.contentassist.providers.PackageContentProposalProvider;
import com.springsource.sts.config.core.schemas.ContextSchemaConstants;
import com.springsource.sts.config.ui.editors.AbstractConfigEditor;
import com.springsource.sts.config.ui.editors.SpringConfigDetailsSectionPart;
import com.springsource.sts.config.ui.widgets.TextAttribute;
import com.springsource.sts.config.ui.widgets.TextAttributeProposalAdapter;

/**
 * @author Leo Dos Santos
 */
@SuppressWarnings("restriction")
public class ContextDetailsSectionPart extends SpringConfigDetailsSectionPart {

	public ContextDetailsSectionPart(AbstractConfigEditor editor, IDOMElement input, Composite parent,
			FormToolkit toolkit) {
		super(editor, input, parent, toolkit);
	}

	@Override
	protected boolean addCustomAttribute(Composite client, String attr, boolean required) {
		// ContextContentAssistProcessor and ContextHyperlinkDetector
		String elem = getInput().getLocalName();
		if (ContextSchemaConstants.ELEM_COMPONENT_SCAN.equals(elem)
				&& (ContextSchemaConstants.ATTR_NAME_GENERATOR.equals(attr) || ContextSchemaConstants.ATTR_SCOPE_RESOLVER
						.equals(attr))) {
			TextAttribute attrControl = createBeanAttribute(client, attr, required);
			addWidget(attrControl);
			addAdapter(new TextAttributeProposalAdapter(attrControl, new BeanReferenceContentProposalProvider(
					getInput(), attr, true)));
			return true;
		}
		if (ContextSchemaConstants.ELEM_LOAD_TIME_WEAVER.equals(elem)
				&& ContextSchemaConstants.ATTR_WEAVER_CLASS.equals(attr)) {
			TextAttribute attrControl = createClassAttribute(client, attr, true, required);
			addWidget(attrControl);
			addAdapter(new TextAttributeProposalAdapter(attrControl, new ClassHierarchyContentProposalProvider(
					getInput(), attr, "org.springframework.instrument.classloading.LoadTimeWeaver"))); //$NON-NLS-1$
			return true;
		}
		if (ContextSchemaConstants.ELEM_COMPONENT_SCAN.equals(elem)
				&& ContextSchemaConstants.ATTR_BASE_PACKAGE.equals(attr)) {
			TextAttribute attrControl = createTextAttribute(client, attr, required);
			addWidget(attrControl);
			addAdapter(new TextAttributeProposalAdapter(attrControl, new PackageContentProposalProvider(getInput(),
					attr)));
			return true;
		}
		if ((ContextSchemaConstants.ELEM_INCLUDE_FILTER.equals(elem) || ContextSchemaConstants.ELEM_EXCLUDE_FILTER
				.equals(elem)) && ContextSchemaConstants.ATTR_EXPRESSION.equals(attr)) {
			TextAttribute attrControl = createClassAttribute(client, attr, false, required);
			addWidget(attrControl);
			addAdapter(new TextAttributeProposalAdapter(attrControl, new ClassContentProposalProvider(getInput(), attr,
					false)));
			return true;
		}
		return super.addCustomAttribute(client, attr, required);
	}

}
