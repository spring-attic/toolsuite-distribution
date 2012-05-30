/******************************************************************************************
 * Copyright (c) 2010 - 2012 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.quickfix.jdt.processors;

/**
 * @author Terry Denney
 * @author Kaitlin Duck Sherwood
 */
public class AnnotationQuickAssistProcessorRegistry {

	public static AbstractAnnotationQuickAssistProcessor[] processors = new AbstractAnnotationQuickAssistProcessor[] {
			new AutowiredAnnotationQuickAssistProcessor(), new QualifierAnnotationQuickAssistProcessor(),
			new AutowireRequiredNotFoundAnnotationQuickAssistProcessor(),
			new ControllerAnnotationQuickAssistProcessor(), new RequestMappingParamAnnotationQuickAssistProcessor(),
			new InitBinderAnnotationQuickAssistProcessor(), new ExceptionHandlerAnnotationQuickAssistProcessor(),
			new ResponseBodyAnnotationQuickAssistProcessor(), new AddAutowireConstructorQuickAssistProcessor()
	// path variable quick assist is implemented in marker resolution generator
	// , new PathVariableAnnotationQuickAssistProcessor()
	// , new RequestMappingAnnotationQuickAssistProcessor()
	};

}
