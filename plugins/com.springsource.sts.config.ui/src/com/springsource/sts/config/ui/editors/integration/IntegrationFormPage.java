/******************************************************************************************
 * Copyright (c) 2009 - 2010 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.ui.editors.integration;

import com.springsource.sts.config.ui.editors.AbstractConfigFormPage;
import com.springsource.sts.config.ui.editors.AbstractConfigMasterDetailsBlock;

/**
 * @author Leo Dos Santos
 * @author Christian Dupuis
 */
public class IntegrationFormPage extends AbstractConfigFormPage {

	@Override
	protected AbstractConfigMasterDetailsBlock createMasterDetailsBlock() {
		return new IntegrationMasterDetailsBlock(this);
	}

}
