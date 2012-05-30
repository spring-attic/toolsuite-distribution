/******************************************************************************************
 * Copyright (c) 2010 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.ui.editors.osgi.compendium;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMElement;

import com.springsource.sts.config.ui.editors.AbstractConfigEditor;
import com.springsource.sts.config.ui.editors.AbstractConfigMasterPart;
import com.springsource.sts.config.ui.editors.AbstractNamespaceDetailsPart;
import com.springsource.sts.config.ui.editors.SpringConfigDetailsSectionPart;

/**
 * @author Leo Dos Santos
 */
@SuppressWarnings("restriction")
public class OsgiCompendiumDetailsPart extends AbstractNamespaceDetailsPart {

	public OsgiCompendiumDetailsPart(AbstractConfigMasterPart master) {
		super(master);
	}

	public OsgiCompendiumDetailsPart(AbstractConfigMasterPart master, String docsUrl) {
		super(master, docsUrl);
	}

	@Override
	public SpringConfigDetailsSectionPart createDetailsSectionPart(AbstractConfigEditor editor, IDOMElement input,
			Composite parent, FormToolkit toolkit) {
		return new OsgiCompendiumDetailsSectionPart(editor, input, parent, toolkit);
	}

}
