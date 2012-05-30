/******************************************************************************************
 * Copyright (c) 2009 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.ui.widgets;

import org.eclipse.ui.forms.widgets.Hyperlink;

/**
 * Interface for collecting common functionality between
 * {@link AbstractAttributeWidget} classes with hyperlink controls.
 * @author Leo Dos Santos
 * @since 2.1.0
 */
public interface IHyperlinkAttribute {

	/**
	 * Returns the hyperlink displaying the label of attribute, typically the
	 * attribute's name.
	 * 
	 * @return hyperlink displaying the label of attribute, typically the
	 * attribute's name
	 */
	public Hyperlink getHyperlinkControl();

	/**
	 * Clients must implement this method to perform an appropriate action based
	 * on the content of the attribute. This method is called automatically when
	 * the hyperlink is clicked.
	 */
	public void openHyperlink();

}
