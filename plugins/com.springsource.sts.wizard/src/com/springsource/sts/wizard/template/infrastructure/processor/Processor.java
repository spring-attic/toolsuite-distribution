/******************************************************************************************
 * Copyright (c) 2008 - 2009 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.wizard.template.infrastructure.processor;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Map;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.swt.widgets.Shell;

/**
 * @author Terry Denney
 * @author Leo Dos Santos
 * @author Christian Dupuis
 */
public class Processor {

	private final ProcessingInfo processingInfo;

	public Processor(ProcessingInfo processingInfo) {
		this.processingInfo = processingInfo;
	}

	public IProject process(IProject project, IPath projectPath, String[] topLevelPackageTokens,
			String projectNameToken, Map<String, Object> userInput, Map<String, String> inputKinds, Shell shell,
			IProgressMonitor progressMonitor) throws IOException, URISyntaxException, CoreException {
		processingInfo.setInputKinds(inputKinds);
		processingInfo.setTopLevelPackageTokens(topLevelPackageTokens);
		processingInfo.setProjectNameToken(projectNameToken);
		processingInfo.setUserInput(userInput);
		DirectoryProcessor directoryProcessor = new DirectoryProcessor(processingInfo, project, projectPath);
		return directoryProcessor.process(processingInfo.getTemplateSourceDirectory(), shell, progressMonitor);
	}
}
