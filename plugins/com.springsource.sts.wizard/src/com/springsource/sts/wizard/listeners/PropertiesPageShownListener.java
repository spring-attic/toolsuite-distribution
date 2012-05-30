/******************************************************************************************
 * Copyright (c) 2008 - 2009 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.wizard.listeners;

import org.eclipse.jface.dialogs.IPageChangedListener;
import org.eclipse.jface.dialogs.PageChangedEvent;

import com.springsource.sts.wizard.ui.AbstractBeanWizardPage;
import com.springsource.sts.wizard.ui.BeanPropertiesWizardPage;
import com.springsource.sts.wizard.ui.BeanWizard;

/**
 * Listener for invoking properties validation on entering or leaving the bean properties page.
 * @author Terry Denney
 * @author Leo Dos Santos
 * @author Christian Dupuis
 * @since 2.0
 */
public class PropertiesPageShownListener implements IPageChangedListener {
	
	private BeanWizard wizard;
	
	public PropertiesPageShownListener(BeanWizard wizard) {
		this.wizard = wizard;
	}

	public void pageChanged(PageChangedEvent event) {
		Object selectedPage = event.getSelectedPage();
		if (selectedPage instanceof AbstractBeanWizardPage) {
			((AbstractBeanWizardPage) selectedPage).updateMessage();
		}
		if (selectedPage instanceof BeanPropertiesWizardPage) {
			((BeanPropertiesWizardPage) selectedPage).validateElements();
		} else {
			wizard.getPropertiesPage().resetProblemCounter();
		}
	}

}
