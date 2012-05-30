/******************************************************************************************
 * Copyright (c) 2008 - 2010 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.ui.editors.overview;

import com.springsource.sts.config.ui.editors.AbstractConfigEditor;
import com.springsource.sts.config.ui.editors.AbstractConfigFormPage;
import com.springsource.sts.config.ui.editors.AbstractConfigMasterDetailsBlock;

/**
 * @author Leo Dos Santos
 * @author Christian Dupuis
 */
public class OverviewFormPage extends AbstractConfigFormPage {

	public final static String ID = "com.springsource.sts.config.ui.editors.overview"; //$NON-NLS-1$

	public OverviewFormPage(AbstractConfigEditor editor) {
		super(editor, ID, Messages.getString("OverviewFormPage.FORM_TITLE")); //$NON-NLS-1$
	}

	@Override
	protected AbstractConfigMasterDetailsBlock createMasterDetailsBlock() {
		return new OverviewMasterDetailsBlock(this);
	}

}
