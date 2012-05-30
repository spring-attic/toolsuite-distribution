/******************************************************************************************
 * Copyright (c) 2010 2011 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.ui.properties;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbenchPart;

import com.springsource.sts.config.ui.editors.AbstractConfigDetailsPart;
import com.springsource.sts.config.ui.editors.AbstractConfigFormPage;
import com.springsource.sts.config.ui.editors.AbstractNamespaceDetailsPart;
import com.springsource.sts.config.ui.editors.SpringConfigDocumentationSectionPart;
import com.springsource.sts.config.ui.editors.overview.OverviewFormPage;

/**
 * @author Leo Dos Santos
 */
public class SpringConfigDocumentationPropertySection extends AbstractConfigPropertySection {

	@Override
	public void setInput(IWorkbenchPart part, ISelection selection) {
		super.setInput(part, selection);
		if (input != null) {
			if (!pageBook.hasPage(input) && getConfigEditor() != null) {
				AbstractConfigFormPage page = getConfigEditor().getFormPageForAdapterUri(getInput().getNamespaceURI());
				if (page == null) {
					page = getConfigEditor().getFormPage(OverviewFormPage.ID);
				}
				if (page != null) {
					AbstractConfigDetailsPart detailsPart = page.getMasterDetailsBlock().getPage(getInput());
					if (detailsPart instanceof AbstractNamespaceDetailsPart) {
						AbstractNamespaceDetailsPart namespaceDetails = (AbstractNamespaceDetailsPart) detailsPart;
						Composite composite = pageBook.createPage(input);
						composite.setLayout(new GridLayout());
						SpringConfigDocumentationSectionPart sectionPart = namespaceDetails
								.createDocumentationSectionPart(getConfigEditor(), getInput(), composite,
										getWidgetFactory());
						sectionPart.createContent();
						composite.setData(sectionPart);
						partMap.put(input, sectionPart);
					}
				}
			}
			pageBook.showPage(input);
		}
	}

}
