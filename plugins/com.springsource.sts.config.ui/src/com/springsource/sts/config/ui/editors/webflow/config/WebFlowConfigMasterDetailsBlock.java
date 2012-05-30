/******************************************************************************************
 * Copyright (c) 2008 - 2010 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.ui.editors.webflow.config;

import org.eclipse.swt.widgets.Composite;
import org.osgi.framework.Version;

import com.springsource.sts.config.ui.editors.AbstractConfigDetailsPart;
import com.springsource.sts.config.ui.editors.AbstractConfigFormPage;
import com.springsource.sts.config.ui.editors.AbstractConfigMasterPart;
import com.springsource.sts.config.ui.editors.AbstractNamespaceMasterDetailsBlock;

/**
 * @author Leo Dos Santos
 * @author Christian Dupuis
 */
public class WebFlowConfigMasterDetailsBlock extends AbstractNamespaceMasterDetailsBlock {

	public static final String DOCS_SPRINGWEBFLOW_10 = "http://static.springframework.org/spring-webflow/docs/1.0.x/reference/index.html"; //$NON-NLS-1$

	public static final String DOCS_SPRINGWEBFLOW_20 = "http://static.springframework.org/spring-webflow/docs/2.0.x/reference/html/index.html"; //$NON-NLS-1$

	public static final String DOCS_SPRINGWEBFLOW_21 = "http://static.springframework.org/spring-webflow/docs/2.1.x/reference/html/index.html"; //$NON-NLS-1$

	public WebFlowConfigMasterDetailsBlock(AbstractConfigFormPage page) {
		super(page);
	}

	@Override
	protected AbstractConfigMasterPart createMasterSectionPart(AbstractConfigFormPage page, Composite parent) {
		return new WebFlowConfigMasterPart(page, parent);
	}

	@Override
	public AbstractConfigDetailsPart getDetailsPage(Object key) {
		if (getFormPage().getSchemaVersion().compareTo(new Version("2.1")) >= 0) { //$NON-NLS-1$
			return new WebFlowConfigDetailsPart(getMasterPart(), DOCS_SPRINGWEBFLOW_21);
		}
		else if (getFormPage().getSchemaVersion().compareTo(new Version("2.0")) >= 0) { //$NON-NLS-1$
			return new WebFlowConfigDetailsPart(getMasterPart(), DOCS_SPRINGWEBFLOW_20);
		}
		else {
			return new WebFlowConfigDetailsPart(getMasterPart(), DOCS_SPRINGWEBFLOW_10);
		}
	}

}
