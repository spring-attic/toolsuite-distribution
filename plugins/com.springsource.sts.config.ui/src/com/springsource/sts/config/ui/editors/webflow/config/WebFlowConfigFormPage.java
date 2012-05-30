/******************************************************************************************
 * Copyright (c) 2008 - 2010 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.ui.editors.webflow.config;

import com.springsource.sts.config.ui.editors.AbstractConfigFormPage;
import com.springsource.sts.config.ui.editors.AbstractConfigMasterDetailsBlock;

/**
 * @author Leo Dos Santos
 * @author Christian Dupuis
 */
public class WebFlowConfigFormPage extends AbstractConfigFormPage {

	@Override
	protected AbstractConfigMasterDetailsBlock createMasterDetailsBlock() {
		return new WebFlowConfigMasterDetailsBlock(this);
	}

}
