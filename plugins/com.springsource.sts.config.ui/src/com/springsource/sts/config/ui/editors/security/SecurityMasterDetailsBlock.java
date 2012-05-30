/******************************************************************************************
 * Copyright (c) 2008 - 2010 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.ui.editors.security;

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
public class SecurityMasterDetailsBlock extends AbstractNamespaceMasterDetailsBlock {

	public static final String DOCS_SPRINGSECURITY_20 = "http://static.springsource.org/spring-security/site/docs/2.0.x/reference/springsecurity.html"; //$NON-NLS-1$

	public static final String DOCS_SPRINGSECURITY_30 = "http://static.springsource.org/spring-security/site/docs/3.0.x/reference/springsecurity.html"; //$NON-NLS-1$ 

	public SecurityMasterDetailsBlock(AbstractConfigFormPage page) {
		super(page);
	}

	@Override
	protected AbstractConfigMasterPart createMasterSectionPart(AbstractConfigFormPage page, Composite parent) {
		return new SecurityMasterPart(page, parent);
	}

	@Override
	public AbstractConfigDetailsPart getDetailsPage(Object key) {
		if (getFormPage().getSchemaVersion().compareTo(new Version("3.0")) >= 0) { //$NON-NLS-1$
			return new SecurityDetailsPart(getMasterPart(), DOCS_SPRINGSECURITY_30);
		}
		else {
			return new SecurityDetailsPart(getMasterPart(), DOCS_SPRINGSECURITY_20);
		}
	}

}
