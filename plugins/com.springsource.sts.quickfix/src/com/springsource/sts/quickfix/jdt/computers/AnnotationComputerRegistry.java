/******************************************************************************************
 * Copyright (c) 2010 - 2011 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.quickfix.jdt.computers;

import org.eclipse.jdt.internal.ui.text.java.JavaCompletionProposalComputer;

/**
 * @author Terry Denney
 * @since 2.6
 */
public class AnnotationComputerRegistry {

	public static JavaCompletionProposalComputer[] computers = new JavaCompletionProposalComputer[] {
			new QualifierArgumentProposalComputer(), new QualifierCompletionProposalComputer(),
			new RequestMappingVariableProposalComputer(), new RequestMappingParamTypeProposalComputer(),
			new ConfigurationLocationProposalComputer() };

}
