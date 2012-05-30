/******************************************************************************************
 * Copyright (c) 2011 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Terry Denney
 */
public class ControllerTest {
	
	private class NoAnnotation {
		@RequestMapping("/")
		public void requestMappingAnnotation() {
		}
		
		public void noAnnotation() {
		}	
	}
	
	private class NoAnnotationNoRequestMapping {
		public void noAnnotation() {
		}
	}
	
	@RequestMapping
	private class RequestMappingAnnotationNoRequestMapping {
		public void noAnnotation() {
		}
	}
	
	@RequestMapping
	private class RequestMappingAnnotation {
		@RequestMapping("/")
		public void requestMappingAnnotation() {
		}
		
		public void noAnnotation() {
		}
	}
	
	@Controller
	private class ControllerAnnotation {
		@RequestMapping("/")
		public void requestMappingAnnotation() {
		}
		
		public void noAnnotation() {
		}
	}
	
	@Controller
	private class ControllerAnnotationNoRequestMapping {		
		public void noAnnotation() {
		}
	}
	
	@Controller
	@RequestMapping("/")
	private class BothAnnotationsNoRequestMapping {
		public void noAnnotation() {
		}
	}

	@Controller
	@RequestMapping("/")
	private class BothAnnotations {
		@RequestMapping("/")
		public void requestMappingAnnotation() {
		}
		
		public void noAnnotation() {
		}
	}
	
}
