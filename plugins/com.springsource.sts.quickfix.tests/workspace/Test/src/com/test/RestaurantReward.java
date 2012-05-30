/******************************************************************************************
 * Copyright (c) 2009 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.test;

/**
 * @author Leo Dos Santos
 */
public class RestaurantReward {

	private String date;
	
	private int award;
	
	private boolean recurring;

	public RestaurantReward(String date, int award, boolean recurring) {
		this.date = date;
		this.award = award;
		this.recurring = recurring;
	}
	
}
