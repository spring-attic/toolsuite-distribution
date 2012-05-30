/******************************************************************************************
 * Copyright (c) 2008 - 2009 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.wizard.ui;

import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.wizard.WizardPage;

import com.springsource.sts.wizard.Messages;

/**
 * @author Terry Denney
 * @author Leo Dos Santos
 * @author Christian Dupuis
 */
public abstract class AbstractBeanWizardPage extends WizardPage {

	protected BeanWizard wizard;
	
	protected AbstractBeanWizardPage(String pageName, BeanWizard wizard) {
		super(pageName);
		this.wizard = wizard;
	}
	
	public abstract void updateMessage();

	protected void setDialogMessage(String message, boolean canIgnore) {
		if (BeanWizard.getIgnoreError()) {
			if (canIgnore) {
				setMessage(message, IMessageProvider.WARNING);
			} else {
				setMessage(message, IMessageProvider.ERROR);
			}
		} else {
			if (canIgnore) {
				message += " " + Messages.getString("NewBeanWizardPage.SELECT_IGNORE_ERROR"); //$NON-NLS-1$ //$NON-NLS-2$
			}
			setMessage(message, IMessageProvider.ERROR); //$NON-NLS-1$
		}
	}
	
	protected void setDialogMessage(String message) {
		setDialogMessage(message, true);
	}
	
}
