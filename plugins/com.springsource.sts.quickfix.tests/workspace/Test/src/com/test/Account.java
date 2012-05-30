/******************************************************************************************
 * Copyright (c) 2009 - 2010 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.test;

/**
 * @author Leo Dos Santos
 * @author Terry Denney
 */
public class Account {

	private int balance;
	
	public Account() {
	}
	
	public void initializeAccount() {
		balance = 0;
	}
	
	@Deprecated
	public void createAccount() {
		
	}
	
	public void disposeAccount() {
		AccountManager.unregisterAccount(this);
	}
	
	@Deprecated
	public void deleteAccount() {
		
	}
	
	public int getBalance() {
		return balance;
	}
	
	public void setBalance(int balance) {
		this.balance = balance;
	}
	
	public static class AccountReader {
		
		public void displayAccount() {
			System.out.println("Account with balance " + balance);
		}
		
	}
}
