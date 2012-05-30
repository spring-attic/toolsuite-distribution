/******************************************************************************************
 * Copyright (c) 2008 - 2009 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.internal.ide.help.cheatsheet;

import org.eclipse.mylyn.tasks.ui.TasksUiUtil;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.cheatsheets.AbstractItemExtensionElement;
import org.eclipse.ui.forms.events.HyperlinkAdapter;
import org.eclipse.ui.forms.events.HyperlinkEvent;
import org.eclipse.ui.forms.widgets.ImageHyperlink;

import com.springsource.sts.internal.ide.help.HelpImages;

/**
 * @author Steffen Pingel
 * @author Leo Dos Santos
 * @author Christian Dupuis
 */
public class CheatsheetUrlExtension extends AbstractItemExtensionElement {

	private Image image;

	private String url;

	public CheatsheetUrlExtension(String attributeName) {
		super(attributeName);
	}

	@Override
	public void createControl(Composite composite) {
		image = HelpImages.getImage(HelpImages.BROWSER);

		ImageHyperlink button = new ImageHyperlink(composite, SWT.NULL);
		button.setImage(image);
		button.setToolTipText("More Information");

		button.addHyperlinkListener(new HyperlinkAdapter() {
			@Override
			public void linkActivated(HyperlinkEvent e) {
				TasksUiUtil.openUrl(url);
			}
		});

	}

	@Override
	public void dispose() {
		// image is kept in shared registry, do not dispose
	}

	@Override
	public void handleAttribute(String attributeValue) {
		this.url = attributeValue;
	}

}
