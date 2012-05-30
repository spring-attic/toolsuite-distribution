/******************************************************************************************
 * Copyright (c) 2010 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.ui.editors.integration;

import org.eclipse.swt.widgets.Composite;

import com.springsource.sts.config.ui.editors.AbstractConfigDetailsPart;
import com.springsource.sts.config.ui.editors.AbstractConfigFormPage;
import com.springsource.sts.config.ui.editors.AbstractConfigMasterPart;
import com.springsource.sts.config.ui.editors.AbstractNamespaceDetailsPart;
import com.springsource.sts.config.ui.editors.AbstractNamespaceMasterDetailsBlock;

/**
 * @author Leo Dos Santos
 */
public class IntAdapterMasterDetailsBlock extends AbstractNamespaceMasterDetailsBlock {

	@Override
	protected AbstractConfigMasterPart createMasterSectionPart(AbstractConfigFormPage page, Composite parent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AbstractConfigDetailsPart getDetailsPage(Object key) {
		return new AbstractNamespaceDetailsPart(getMasterPart(), IntegrationMasterDetailsBlock.DOCS_SPRINGINT);
	}

}
