/******************************************************************************************
 * Copyright (c) 2010 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.ui.editors.jdbc;

import com.springsource.sts.config.ui.editors.AbstractConfigFormPage;
import com.springsource.sts.config.ui.editors.AbstractConfigMasterDetailsBlock;

/**
 * @author Leo Dos Santos
 */
public class JdbcFormPage extends AbstractConfigFormPage {

	@Override
	protected AbstractConfigMasterDetailsBlock createMasterDetailsBlock() {
		return new JdbcMasterDetailsBlock(this);
	}

}
