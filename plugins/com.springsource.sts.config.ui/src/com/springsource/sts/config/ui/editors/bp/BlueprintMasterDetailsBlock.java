/******************************************************************************************
 * Copyright (c) 2010 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.ui.editors.bp;

import org.eclipse.swt.widgets.Composite;

import com.springsource.sts.config.ui.editors.AbstractConfigDetailsPart;
import com.springsource.sts.config.ui.editors.AbstractConfigFormPage;
import com.springsource.sts.config.ui.editors.AbstractConfigMasterPart;
import com.springsource.sts.config.ui.editors.AbstractNamespaceMasterDetailsBlock;
import com.springsource.sts.config.ui.editors.osgi.OsgiMasterDetailsBlock;

/**
 * @author Leo Dos Santos
 */
public class BlueprintMasterDetailsBlock extends AbstractNamespaceMasterDetailsBlock {

	public BlueprintMasterDetailsBlock(AbstractConfigFormPage page) {
		super(page);
	}

	@Override
	protected AbstractConfigMasterPart createMasterSectionPart(AbstractConfigFormPage page, Composite parent) {
		return new BlueprintMasterPart(page, parent);
	}

	@Override
	public AbstractConfigDetailsPart getDetailsPage(Object key) {
		return new BlueprintDetailsPart(getMasterPart(), OsgiMasterDetailsBlock.DOCS_SPRINGOSGI_20);
	}

}
