/******************************************************************************************
 * Copyright (c) 2008 - 2009 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.wizard.template.newproject;

import java.net.URL;
import java.util.Collection;
import java.util.Map;

import com.springsource.sts.wizard.template.infrastructure.processor.AbstractProcessingInfo;

/**
 * @author Terry Denney
 * @author Leo Dos Santos
 * @author Christian Dupuis
 */
public class NewProjectProcessingInfo extends AbstractProcessingInfo {

	private final URL projectSourceLocation;

	private String[] topLevelPackageTokens;

	private final String projectName;

	private String projectNameToken;

	public NewProjectProcessingInfo(URL projectSourceLocation, String projectName) {
		this.projectSourceLocation = projectSourceLocation;
		this.projectName = projectName;
	}

	@Override
	public Collection<String> getExclusionPatterns() {
		Collection<String> exclusionPatterns = super.getExclusionPatterns();

		if (Boolean.FALSE.equals(userInput.get("oraclePackSupport"))) {
			exclusionPatterns.add("/**/oracle*.*");
		}
		return exclusionPatterns;
	}

	public URL getTemplateSourceDirectory() {
		return projectSourceLocation;
	}

	public void setProjectNameToken(String projectNameToken) {
		this.projectNameToken = projectNameToken;
	}

	public void setTopLevelPackageTokens(String[] topLevelPackageTokens) {
		this.topLevelPackageTokens = topLevelPackageTokens;
	}

	@Override
	public void setUserInput(Map<String, Object> userInput) {
		super.setUserInput(userInput);

		String userTopLevelPackageName = getUserTopLevelPackageName();

		if (userTopLevelPackageName != null) {
			String[] userTopLevelPackage = userTopLevelPackageName.split("\\.");

			if (topLevelPackageTokens != null) {
				StringBuilder topLevelPackageStr = new StringBuilder();
				StringBuilder userTopLevelPackageStr = new StringBuilder();

				for (int i = 0; i < userTopLevelPackage.length || i < topLevelPackageTokens.length; i++) {
					if (i >= userTopLevelPackage.length) {
						userInput.put(topLevelPackageTokens[i], topLevelPackageTokens[i]);
						topLevelPackageStr.append("&&");
						topLevelPackageStr.append(topLevelPackageTokens[i]);
					}
					else if (i >= topLevelPackageTokens.length) {
						userInput.put(userTopLevelPackage[i], userTopLevelPackage[i]);
						userTopLevelPackageStr.append("&&");
						userTopLevelPackageStr.append(userTopLevelPackage[i]);
					}
					else {
						userInput.put(topLevelPackageTokens[i], userTopLevelPackage[i]);
						topLevelPackageStr.append("&&");
						topLevelPackageStr.append(topLevelPackageTokens[i]);
						userTopLevelPackageStr.append("&&");
						userTopLevelPackageStr.append(userTopLevelPackage[i]);
					}
				}

				userInput.put("||top-level-package||", topLevelPackageStr.toString());
				userInput.put("||user-top-level-package||", userTopLevelPackageStr.toString());
			}
		}

		userInput.put(projectNameToken, projectName);
	}
}
