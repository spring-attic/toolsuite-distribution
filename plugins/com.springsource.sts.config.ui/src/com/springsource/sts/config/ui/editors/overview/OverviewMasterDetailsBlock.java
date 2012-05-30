/******************************************************************************************
 * Copyright (c) 2008 - 2011 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.ui.editors.overview;

import java.util.Set;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.swt.widgets.Composite;

import com.springsource.sts.config.core.extensions.FormPagesExtensionPointConstants;
import com.springsource.sts.config.ui.editors.AbstractConfigDetailsPart;
import com.springsource.sts.config.ui.editors.AbstractConfigEditor;
import com.springsource.sts.config.ui.editors.AbstractConfigFormPage;
import com.springsource.sts.config.ui.editors.AbstractConfigMasterDetailsBlock;
import com.springsource.sts.config.ui.editors.AbstractConfigMasterPart;
import com.springsource.sts.config.ui.editors.AbstractNamespaceDetailsPart;
import com.springsource.sts.config.ui.extensions.ConfigUiExtensionPointReader;

/**
 * @author Leo Dos Santos
 * @author Christian Dupuis
 */
public class OverviewMasterDetailsBlock extends AbstractConfigMasterDetailsBlock {

	public OverviewMasterDetailsBlock(AbstractConfigFormPage page) {
		super(page);
	}

	@Override
	protected AbstractConfigMasterPart createMasterSectionPart(AbstractConfigFormPage page, Composite parent) {
		return new OverviewMasterPart(page, parent);
	}

	@Override
	public AbstractConfigDetailsPart getPage(Object key) {
		AbstractConfigEditor editor = getFormPage().getEditor();
		Set<IConfigurationElement> definitions = ConfigUiExtensionPointReader.getPageDefinitions();
		for (IConfigurationElement def : definitions) {
			String uri = def.getAttribute(FormPagesExtensionPointConstants.ATTR_NAMESPACE_URI);
			AbstractConfigFormPage formPage = editor.getFormPageForUri(uri);
			if (formPage != null) {
				AbstractConfigDetailsPart page = formPage.getMasterDetailsBlock().getPage(key);
				if (page != null) {
					page.setMasterPart(getMasterPart());
					return page;
				}
			}
		}
		return new AbstractNamespaceDetailsPart(getMasterPart());
	}

}
