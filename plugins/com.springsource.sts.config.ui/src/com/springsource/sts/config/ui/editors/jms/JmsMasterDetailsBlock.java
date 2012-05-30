/******************************************************************************************
 * Copyright (c) 2008 - 2010 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.ui.editors.jms;

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
public class JmsMasterDetailsBlock extends AbstractNamespaceMasterDetailsBlock {

	public JmsMasterDetailsBlock(AbstractConfigFormPage page) {
		super(page);
	}

	@Override
	protected AbstractConfigMasterPart createMasterSectionPart(AbstractConfigFormPage page, Composite parent) {
		return new JmsMasterPart(page, parent);
	}

	@Override
	public AbstractConfigDetailsPart getDetailsPage(Object key) {
		if (getFormPage().getSchemaVersion().compareTo(new Version("3.0")) >= 0) { //$NON-NLS-1$
			return new JmsDetailsPart(getMasterPart());
		}
		else {
			return new JmsDetailsPart(getMasterPart(), AbstractNamespaceDetailsPart.DOCS_SPRINGFRAMEWORK_25);
		}
	}

}
