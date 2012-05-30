/******************************************************************************************
 * Copyright (c) 2008 - 2009 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.wizard.template.infrastructure.processor;

import java.net.URL;
import java.util.Collection;
import java.util.Map;

/**
 * Stores user input from template wizard UI elements
 * @author Terry Denney
 * @author Leo Dos Santos
 * @author Christian Dupuis
 */
public interface ProcessingInfo {

	Collection<String> getExclusionPatterns();

	Collection<String> getInclusionPatterns();

	Map<String, String> getResourceReplacementContext();

	Map<String, String> getTemplateReplacementContext();

	URL getTemplateSourceDirectory();

	void setInputKinds(Map<String, String> inputKinds);

	void setProjectNameToken(String projectNameToken);

	void setTopLevelPackageTokens(String[] topLevelPackageTokens);

	void setUserInput(Map<String, Object> userInput);

}