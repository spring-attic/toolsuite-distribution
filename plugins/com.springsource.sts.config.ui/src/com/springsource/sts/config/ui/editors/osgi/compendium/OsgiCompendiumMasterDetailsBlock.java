/******************************************************************************************
 * Copyright (c) 2008 - 2010 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.ui.editors.osgi.compendium;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMDocument;
import org.osgi.framework.Version;

import com.springsource.sts.config.core.ConfigCoreUtils;
import com.springsource.sts.config.core.schemas.OsgiSchemaConstants;
import com.springsource.sts.config.ui.editors.AbstractConfigDetailsPart;
import com.springsource.sts.config.ui.editors.AbstractConfigFormPage;
import com.springsource.sts.config.ui.editors.AbstractConfigMasterPart;
import com.springsource.sts.config.ui.editors.AbstractNamespaceMasterDetailsBlock;
import com.springsource.sts.config.ui.editors.osgi.OsgiMasterDetailsBlock;

/**
 * @author Leo Dos Santos
 * @author Christian Dupuis
 */
@SuppressWarnings("restriction")
public class OsgiCompendiumMasterDetailsBlock extends AbstractNamespaceMasterDetailsBlock {

	public OsgiCompendiumMasterDetailsBlock() {
		super();
	}

	public OsgiCompendiumMasterDetailsBlock(AbstractConfigFormPage page) {
		super(page);
	}

	@Override
	protected AbstractConfigMasterPart createMasterSectionPart(AbstractConfigFormPage page, Composite parent) {
		return new OsgiCompendiumMasterPart(page, parent);
	}

	@Override
	public AbstractConfigDetailsPart getDetailsPage(Object key) {
		IDOMDocument doc = getFormPage().getEditor().getDomDocument();
		if (ConfigCoreUtils.getSchemaVersion(doc, OsgiSchemaConstants.URI).compareTo(new Version("2.0")) >= 0) { //$NON-NLS-1$
			return new OsgiCompendiumDetailsPart(getMasterPart(), OsgiMasterDetailsBlock.DOCS_SPRINGOSGI_20);
		}
		else {
			return new OsgiCompendiumDetailsPart(getMasterPart(), OsgiMasterDetailsBlock.DOCS_SPRINGOSGI);
		}
	}

}
