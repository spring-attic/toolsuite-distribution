/******************************************************************************************
 * Copyright (c) 2009 - 2010 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.test;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Leo Dos Santos
 * @author Terry Denney
 */
public class AccountManager {

	private static Set<Account> accounts = new HashSet<Account>();
	
	public static Account createDefaultAccount() {
		Account account = new Account();
		accounts.add(account);
		return account;
	}
	
	public static void unregisterAccount(Account account) {
		accounts.remove(account);
	}
	
	@Deprecated
	public static Account createTempAccount() {
		return null;
	}
}
