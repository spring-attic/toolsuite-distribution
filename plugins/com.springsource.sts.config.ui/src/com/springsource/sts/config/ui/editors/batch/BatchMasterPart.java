/******************************************************************************************
 * Copyright (c) 2009 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.ui.editors.batch;

import org.eclipse.jface.action.IMenuManager;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMDocument;

import com.springsource.sts.config.ui.editors.AbstractConfigFormPage;
import com.springsource.sts.config.ui.editors.AbstractNamespaceMasterPart;

/**
 * @author Leo Dos Santos
 * @author Christian Dupuis
 */
@SuppressWarnings("restriction")
public class BatchMasterPart extends AbstractNamespaceMasterPart {

	public BatchMasterPart(AbstractConfigFormPage page, Composite parent) {
		super(page, parent);
	}

	@Override
	protected void createEmptyDocumentActions(IMenuManager manager, IDOMDocument doc) {
		// TODO Auto-generated method stub

	}

	@Override
	protected String getSectionTitle() {
		return Messages.getString("BatchMasterPart.MASTER_SECTION_TITLE"); //$NON-NLS-1$
	}

}
