/******************************************************************************************
 * Copyright (c) 2008 - 2010 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.ui.widgets;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.events.HyperlinkAdapter;
import org.eclipse.ui.forms.events.HyperlinkEvent;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Hyperlink;

/**
 * An {@link AbstractAttributeWidget} that displays an attribute as a
 * hyperlinked label, followed by a single line text field.
 * @author Leo Dos Santos
 * @author Christian Dupuis
 * @see TextAttribute
 * @since 2.0.0
 */
public abstract class HyperlinkedTextAttribute extends TextAttribute implements IHyperlinkAttribute {

	/**
	 * A hyperlink displaying the label of the attribute, typically the
	 * attribute's name.
	 */
	protected Hyperlink label;

	/**
	 * Constructs a row of widgets to display and perform actions upon an XML
	 * attribute. The primary control of the widget set is a single line text
	 * field.
	 * 
	 * @param client the parent composite
	 * @param toolkit the toolkit of the parent form
	 * @param attrName the name of the attribute displayed by the widget set
	 */
	public HyperlinkedTextAttribute(Composite client, FormToolkit toolkit, String attrName) {
		this(client, toolkit, attrName, false);
	}

	/**
	 * Constructs a row of widgets to display and perform actions upon an XML
	 * attribute. The primary control of the widget set is a single line text
	 * field.
	 * 
	 * @param client the parent composite
	 * @param toolkit the toolkit of the parent form
	 * @param attrName the name of the attribute displayed by the widget set
	 * @param required denotes whether this is a required field
	 */
	public HyperlinkedTextAttribute(Composite client, FormToolkit toolkit, String attrName, boolean required) {
		super(client, toolkit, attrName, required);
	}

	@Override
	protected void createLabel() {
		if (required) {
			label = toolkit.createHyperlink(client, attr + "*:", SWT.NONE); //$NON-NLS-1$
		}
		else {
			label = toolkit.createHyperlink(client, attr + ":", SWT.NONE); //$NON-NLS-1$
		}
		label.addHyperlinkListener(new HyperlinkAdapter() {
			@Override
			public void linkActivated(HyperlinkEvent e) {
				openHyperlink();
			}
		});
	}

	public Hyperlink getHyperlinkControl() {
		return label;
	}

}
