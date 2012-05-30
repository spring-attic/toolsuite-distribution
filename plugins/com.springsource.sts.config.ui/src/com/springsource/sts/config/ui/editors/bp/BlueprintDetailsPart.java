/******************************************************************************************
 * Copyright (c) 2010 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.ui.editors.bp;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMElement;

import com.springsource.sts.config.ui.editors.AbstractConfigEditor;
import com.springsource.sts.config.ui.editors.AbstractConfigMasterPart;
import com.springsource.sts.config.ui.editors.SpringConfigDetailsSectionPart;
import com.springsource.sts.config.ui.editors.beans.BeansDetailsPart;

/**
 * @author Leo Dos Santos
 */
@SuppressWarnings("restriction")
public class BlueprintDetailsPart extends BeansDetailsPart {

	public BlueprintDetailsPart(AbstractConfigMasterPart master) {
		super(master);
	}

	public BlueprintDetailsPart(AbstractConfigMasterPart master, String docsUrl) {
		super(master, docsUrl);
	}

	@Override
	public SpringConfigDetailsSectionPart createDetailsSectionPart(AbstractConfigEditor editor, IDOMElement input,
			Composite parent, FormToolkit toolkit) {
		return new BlueprintDetailsSectionPart(editor, input, parent, toolkit);
	}

}
