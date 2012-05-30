/******************************************************************************************
 * Copyright (c) 2009 - 2011 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.test;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Terry Denney
 */
public class AutowireTest {

	private TestClass testBean1;
	
	@Autowired
	private TestClass testBean2;
	
	public AutowireTest() {
	}
	
	public AutowireTest(TestClass testBean) {
	}
	
	public void testMethod1(TestClass testBean) {
	}
	
	@Autowired
	public void testMethod2(TestClass testBean) {	
	}
	
	public void testMethod3() {
	}

}
