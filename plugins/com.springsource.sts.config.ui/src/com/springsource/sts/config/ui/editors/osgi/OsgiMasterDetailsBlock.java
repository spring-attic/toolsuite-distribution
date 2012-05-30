/******************************************************************************************
 * Copyright (c) 2008 - 2010 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.ui.editors.osgi;

import org.eclipse.swt.widgets.Composite;
import org.osgi.framework.Version;

import com.springsource.sts.config.ui.editors.AbstractConfigDetailsPart;
import com.springsource.sts.config.ui.editors.AbstractConfigFormPage;
import com.springsource.sts.config.ui.editors.AbstractConfigMasterPart;
import com.springsource.sts.config.ui.editors.AbstractNamespaceMasterDetailsBlock;

/**
 * @author Leo Dos Santos
 * @author Christian Dupuis
 */
public class OsgiMasterDetailsBlock extends AbstractNamespaceMasterDetailsBlock {

	public static final String DOCS_SPRINGOSGI = "http://static.springsource.org/osgi/docs/current/reference/html/"; //$NON-NLS-1$ 

	public static final String DOCS_SPRINGOSGI_20 = "http://static.springsource.org/osgi/docs/2.0.0.M1/reference/html/"; //$NON-NLS-1$ 

	public OsgiMasterDetailsBlock(AbstractConfigFormPage page) {
		super(page);
	}

	@Override
	protected AbstractConfigMasterPart createMasterSectionPart(AbstractConfigFormPage page, Composite parent) {
		return new OsgiMasterPart(page, parent);
	}

	@Override
	public AbstractConfigDetailsPart getDetailsPage(Object key) {
		if (getFormPage().getSchemaVersion().compareTo(new Version("2.0")) >= 0) { //$NON-NLS-1$
			return new OsgiDetailsPart(getMasterPart(), DOCS_SPRINGOSGI_20);
		}
		else {
			return new OsgiDetailsPart(getMasterPart(), DOCS_SPRINGOSGI);
		}
	}

}
