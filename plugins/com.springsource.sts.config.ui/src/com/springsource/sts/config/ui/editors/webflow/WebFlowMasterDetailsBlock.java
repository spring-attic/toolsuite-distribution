/******************************************************************************************
 * Copyright (c) 2010 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.ui.editors.webflow;

import org.eclipse.swt.widgets.Composite;
import org.osgi.framework.Version;

import com.springsource.sts.config.ui.editors.AbstractConfigDetailsPart;
import com.springsource.sts.config.ui.editors.AbstractConfigFormPage;
import com.springsource.sts.config.ui.editors.AbstractConfigMasterPart;
import com.springsource.sts.config.ui.editors.AbstractNamespaceMasterDetailsBlock;
import com.springsource.sts.config.ui.editors.webflow.config.WebFlowConfigMasterDetailsBlock;

/**
 * @author Leo Dos Santos
 */
public class WebFlowMasterDetailsBlock extends AbstractNamespaceMasterDetailsBlock {

	public WebFlowMasterDetailsBlock(AbstractConfigFormPage page) {
		super(page);
	}

	@Override
	protected AbstractConfigMasterPart createMasterSectionPart(AbstractConfigFormPage page, Composite parent) {
		return new WebFlowMasterPart(page, parent);
	}

	@Override
	public AbstractConfigDetailsPart getDetailsPage(Object key) {
		if (getFormPage().getSchemaVersion().compareTo(new Version("2.1")) >= 0) { //$NON-NLS-1$
			return new WebFlowDetailsPart(getMasterPart(), WebFlowConfigMasterDetailsBlock.DOCS_SPRINGWEBFLOW_21);
		}
		else if (getFormPage().getSchemaVersion().compareTo(new Version("2.0")) >= 0) { //$NON-NLS-1$
			return new WebFlowDetailsPart(getMasterPart(), WebFlowConfigMasterDetailsBlock.DOCS_SPRINGWEBFLOW_20);
		}
		else {
			return new WebFlowDetailsPart(getMasterPart(), WebFlowConfigMasterDetailsBlock.DOCS_SPRINGWEBFLOW_10);
		}
	}

}
