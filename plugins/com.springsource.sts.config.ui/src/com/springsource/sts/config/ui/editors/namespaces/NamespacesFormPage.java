/******************************************************************************************
 * Copyright (c) 2008 - 2010 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.ui.editors.namespaces;

import com.springsource.sts.config.ui.editors.AbstractConfigEditor;
import com.springsource.sts.config.ui.editors.AbstractConfigFormPage;
import com.springsource.sts.config.ui.editors.AbstractConfigMasterDetailsBlock;

/**
 * @author Leo Dos Santos
 * @author Christian Dupuis
 */
public class NamespacesFormPage extends AbstractConfigFormPage {

	public final static String ID = "com.springsource.sts.config.ui.editors.namespaces"; //$NON-NLS-1$

	public NamespacesFormPage(AbstractConfigEditor editor) {
		super(editor, ID, Messages.getString("NamespacesFormPage.FORM_TITLE")); //$NON-NLS-1$
	}

	@Override
	protected AbstractConfigMasterDetailsBlock createMasterDetailsBlock() {
		return new NamespacesMasterDetailsBlock(this);
	}

}
