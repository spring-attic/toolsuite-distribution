/******************************************************************************************
 * Copyright (c) 2009 - 2010 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.test;

/**
 * @author Leo Dos Santos
 * @author Terry Denney
 */
public class Foo {
	
	public static Object ABCD;

	public Foo(String foo) {
	}
	
	public Foo() {
		
	}
	
	public Bar getBar() {
		return new Bar();
	}
	
	public static class Bar {
		private String foobar;
	}
	
	static Foo createFoo(String test) {
		return new Foo(test);
	}
	
	@Deprecated
	public void setZoo(String zoo) {
	}
	
}
