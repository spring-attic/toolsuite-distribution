/******************************************************************************************
 * Copyright (c) 2011 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.ide.metadata.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
import org.eclipse.core.resources.IProject;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.internal.corext.util.JavaModelUtil;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IActionDelegate;
import org.eclipse.ui.navigator.ICommonActionExtensionSite;
import org.springframework.ide.eclipse.ui.navigator.actions.AbstractNavigatorAction;

/**
 * @author Leo Dos Santos
 */
public class ModelAttributeLocator extends AbstractNavigatorAction implements
		IActionDelegate {

	private IJavaProject project;

	private ICompilationUnit[] sources;

	private Collection views;

	private Set<String> sourceModelRefs;

	private Set<String> viewModelRefs;

	// @ ModelAttribute ( value = "string" )
	private static final String REGEX_ANNOTATE_MODELATTR = "@\\s*ModelAttribute\\s*\\(\\s*value\\s*=\\s*\".*\"\\s*\\)";

	// . put ( "string" , Object ) ;
	private static final String REGEX_METHOD_PUT = "\\.\\s*put\\s*\\(\\s*\".*\"\\s*,\\s*.*\\s*\\)\\s*;";

	// ${ string }
	private static final String REGEX_TAG_JSP = "\\$\\{\\s*.*\\s*\\}";

	public ModelAttributeLocator() {
		this(null);
	}

	public ModelAttributeLocator(ICommonActionExtensionSite actionSite) {
		super(actionSite);
		setText("Show model attributes");
	}

	@Override
	protected boolean isEnabled(IStructuredSelection selection) {
		Object obj = selection.getFirstElement();
		if (obj instanceof IProject) {
			project = JavaCore.create((IProject) obj);
			return project != null;
		}
		return false;
	}

	private void gatherSourceFiles() {
		if (project != null) {
			List<IJavaElement> list = new ArrayList<IJavaElement>();
			list.add(project);
			IJavaElement[] children = list
					.toArray(new IJavaElement[list.size()]);
			try {
				sources = JavaModelUtil.getAllCompilationUnits(children);
			} catch (JavaModelException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private void gatherViewFiles() {
		if (project != null) {
			File projectDir = project.getProject().getLocation().toFile();
			if (projectDir.isDirectory()) {
				views = FileUtils.listFiles(projectDir, new String[] { "jsp" },
						true);
			}
		}
	}

	private void parseSourceFiles() {
		sourceModelRefs = new TreeSet<String>();
		parseSourceFiles(Pattern.compile(REGEX_ANNOTATE_MODELATTR));
		parseSourceFiles(Pattern.compile(REGEX_METHOD_PUT));
	}

	private void parseSourceFiles(Pattern pattern) {
		try {
			for (ICompilationUnit unit : sources) {
				String source = unit.getSource();
				Matcher matcher = pattern.matcher(source);
				while (matcher.find()) {
					String key = matcher.group();
					int openingQuote = key.indexOf('"') + 1;
					int secondQuote = key.indexOf('"', openingQuote);
					sourceModelRefs.add(key
							.substring(openingQuote, secondQuote));
				}
			}
		} catch (JavaModelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void parseViewFiles() {
		viewModelRefs = new TreeSet<String>();
		parseViewFiles(Pattern.compile(REGEX_TAG_JSP));
	}

	private void parseViewFiles(Pattern pattern) {
		for (Object obj : views) {
			if (obj instanceof File) {
				File view = (File) obj;
				BufferedReader reader = null;
				try {
					StringBuilder content = new StringBuilder();
					reader = new BufferedReader(new FileReader(view));
					String line = reader.readLine();
					while (line != null) {
						content.append(line);
						line = reader.readLine();
					}
					Matcher matcher = pattern.matcher(content);
					while (matcher.find()) {
						String key = matcher.group();
						int openingBrace = key.indexOf('{') + 1;
						int secondBrace = key.indexOf('}');
						viewModelRefs.add(key.substring(openingBrace,
								secondBrace));
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					if (reader != null) {
						try {
							reader.close();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			}
		}
	}

	private void returnSourceAnalysis() {
		System.out.println("There were " + sourceModelRefs.size()
				+ " model attribute declarations found.");
		for (String ref : sourceModelRefs) {
			System.out.println(ref);
		}
	}

	private void returnViewAnalysis() {
		System.out.println("There were " + viewModelRefs.size()
				+ " model attribute uses found.");
		for (String ref : viewModelRefs) {
			System.out.println(ref);
		}
	}

	@Override
	public void run() {
		gatherSourceFiles();
		gatherViewFiles();
		parseSourceFiles();
		parseViewFiles();
		returnSourceAnalysis();
		returnViewAnalysis();
	}

	public void run(IAction action) {
		run();
	}

	public void selectionChanged(IAction action, ISelection selection) {
		if (selection instanceof IStructuredSelection) {
			isEnabled((IStructuredSelection) selection);
		}
	}

}
