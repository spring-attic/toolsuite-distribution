/******************************************************************************************
 * Copyright (c) 2010 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.ui.editors.task;

import org.eclipse.jface.action.IMenuManager;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMDocument;

import com.springsource.sts.config.ui.editors.AbstractConfigFormPage;
import com.springsource.sts.config.ui.editors.AbstractNamespaceMasterPart;

/**
 * @author Leo Dos Santos
 */
@SuppressWarnings("restriction")
public class TaskMasterPart extends AbstractNamespaceMasterPart {

	public TaskMasterPart(AbstractConfigFormPage page, Composite parent) {
		super(page, parent);
	}

	@Override
	protected void createEmptyDocumentActions(IMenuManager manager, IDOMDocument doc) {
		// TODO Auto-generated method stub

	}

	@Override
	protected String getSectionTitle() {
		return Messages.getString("TaskMasterPart.MASTER_SECTION_TITLE"); //$NON-NLS-1$
	}

}
