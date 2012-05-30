/******************************************************************************************
 * Copyright (c) 2008 - 2010 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.ui.editors.context;

import org.eclipse.swt.widgets.Composite;
import org.osgi.framework.Version;

import com.springsource.sts.config.ui.editors.AbstractConfigDetailsPart;
import com.springsource.sts.config.ui.editors.AbstractConfigFormPage;
import com.springsource.sts.config.ui.editors.AbstractConfigMasterPart;
import com.springsource.sts.config.ui.editors.AbstractNamespaceDetailsPart;
import com.springsource.sts.config.ui.editors.AbstractNamespaceMasterDetailsBlock;

/**
 * @author Leo Dos Santos
 * @author Christian Dupuis
 */
public class ContextMasterDetailsBlock extends AbstractNamespaceMasterDetailsBlock {

	public ContextMasterDetailsBlock(AbstractConfigFormPage page) {
		super(page);
	}

	@Override
	protected AbstractConfigMasterPart createMasterSectionPart(AbstractConfigFormPage page, Composite parent) {
		return new ContextMasterPart(page, parent);
	}

	@Override
	public AbstractConfigDetailsPart getDetailsPage(Object key) {
		if (getFormPage().getSchemaVersion().compareTo(new Version("3.0")) >= 0) { //$NON-NLS-1$
			return new ContextDetailsPart(getMasterPart());
		}
		else {
			return new ContextDetailsPart(getMasterPart(), AbstractNamespaceDetailsPart.DOCS_SPRINGFRAMEWORK_25);
		}
	}

}
