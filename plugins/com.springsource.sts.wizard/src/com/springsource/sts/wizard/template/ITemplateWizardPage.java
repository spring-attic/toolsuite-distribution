/******************************************************************************************
 * Copyright (c) 2009 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.wizard.template;

import org.eclipse.jface.wizard.IWizardPage;

/**
 * @author Terry Denney
 */
public interface ITemplateWizardPage extends IWizardPage {

	public String[] getErrorMessages();

	public String[] getMessages();

	public void setNextPage(IWizardPage page);

	public void updateMessage();

}
