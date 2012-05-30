/******************************************************************************************
 * Copyright (c) 2009 - 2011 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.test;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Terry Denney
 */
public class ConstructorAutowireTest {

	@Autowired
	public ConstructorAutowireTest(TestClass testBean) {
	}
	
	public ConstructorAutowireTest(TestClass testBean, TestClass testBean2) {
	}

}
