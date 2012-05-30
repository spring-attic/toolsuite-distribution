/******************************************************************************************
 * Copyright (c) 2009 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.test;

/**
 * @author Leo Dos Santos
 */
public class Restaurant {

	private String name, cuisine;

	public Restaurant(String name, String cuisine) {
		this.name = name;
		this.cuisine = cuisine;
	}

	public String getName() {
		return name;
	}

	public String getCuisine() {
		return cuisine;
	}
	
	public void setCuisineType(String cuisine) {
		this.cuisine = cuisine;
	}

}
