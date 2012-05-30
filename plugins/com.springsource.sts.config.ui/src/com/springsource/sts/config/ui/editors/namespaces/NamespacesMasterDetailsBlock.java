/******************************************************************************************
 * Copyright (c) 2008 - 2010 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.ui.editors.namespaces;

import org.eclipse.swt.widgets.Composite;
import org.springframework.ide.eclipse.beans.ui.namespaces.INamespaceDefinition;

import com.springsource.sts.config.ui.editors.AbstractConfigDetailsPart;
import com.springsource.sts.config.ui.editors.AbstractConfigFormPage;
import com.springsource.sts.config.ui.editors.AbstractConfigMasterDetailsBlock;
import com.springsource.sts.config.ui.editors.AbstractConfigMasterPart;

/**
 * @author Leo Dos Santos
 * @author Christian Dupuis
 */
public class NamespacesMasterDetailsBlock extends AbstractConfigMasterDetailsBlock {

	public NamespacesMasterDetailsBlock(AbstractConfigFormPage page) {
		super(page);
	}

	@Override
	protected AbstractConfigMasterPart createMasterSectionPart(AbstractConfigFormPage page, Composite container) {
		return new NamespacesMasterPart(page, container);
	}

	@Override
	public AbstractConfigDetailsPart getPage(Object key) {
		if (key instanceof INamespaceDefinition) {
			return new NamespacesDetailsPart(getMasterPart());
		}
		return null;
	}

}
