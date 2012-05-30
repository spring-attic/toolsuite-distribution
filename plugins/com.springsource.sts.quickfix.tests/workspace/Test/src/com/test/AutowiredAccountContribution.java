/******************************************************************************************
 * Copyright (c) 2009 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.test;

/**
 * @author Leo Dos Santos
 */
public class AutowiredAccountContribution {

	private Account account;
	
	@org.springframework.beans.factory.annotation.Autowired
	public AutowiredAccountContribution(Account account) {
		this.account = account;
	}
	
	public void setAccount(Account account) {
		this.account = account;
	}
	
}
