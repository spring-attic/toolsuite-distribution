/******************************************************************************************
 * Copyright (c) 2008 - 2009 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.wizard.template.infrastructure.ui;

/**
 * @author Terry Denney
 * @author Leo Dos Santos
 * @author Christian Dupuis
 */
public class WizardUIInfoPage {
	
	private int order;
	
	private String description;
	
	public int getOrder() {
		return order;
	}
	
	public String getDescription() {
		return description;
	}
	
	public static WizardUIInfoPage getDefaultPage(int order) {
		WizardUIInfoPage page = new WizardUIInfoPage();
		page.order = order;
		page.description = "";
		return page;
	}
	
}
