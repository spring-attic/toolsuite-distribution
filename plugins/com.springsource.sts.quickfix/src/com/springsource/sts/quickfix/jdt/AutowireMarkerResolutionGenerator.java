/******************************************************************************************
 * Copyright (c) 2011 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.quickfix.jdt;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IMarker;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IMember;
import org.eclipse.jdt.core.ISourceRange;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;
import org.eclipse.jdt.internal.ui.text.correction.AssistContext;
import org.eclipse.jdt.ui.text.java.IJavaCompletionProposal;
import org.eclipse.ui.IMarkerResolution;
import org.eclipse.ui.IMarkerResolutionGenerator2;
import org.springframework.ide.eclipse.beans.core.autowire.internal.provider.AutowireDependencyProvider;
import org.springsource.ide.eclipse.commons.core.StatusHandler;

import com.springsource.sts.quickfix.jdt.processors.AutowireRequiredNotFoundAnnotationQuickAssistProcessor;
import com.springsource.sts.quickfix.jdt.processors.QualifierAnnotationQuickAssistProcessor;

/**
 * Marker resolution generator for @Autowired/@Qualifier as defined by
 * {@link AutowireDependencyProvider}
 * 
 * @author Terry Denney
 * @author Martin Lippert
 */
public class AutowireMarkerResolutionGenerator implements IMarkerResolutionGenerator2 {

	@SuppressWarnings("unchecked")
	public IMarkerResolution[] getResolutions(IMarker marker) {
		if (hasResolutions(marker)) {
			String problemType = marker.getAttribute(AutowireDependencyProvider.AUTOWIRE_PROBLEM_TYPE, null);
			String handle = marker.getAttribute("JAVA_HANDLE", null);
			IJavaElement javaElement = JavaCore.create(handle);
			try {

				if (javaElement instanceof IMember) {
					List<IJavaCompletionProposal> proposals = new ArrayList<IJavaCompletionProposal>();

					IMember member = (IMember) javaElement;
					ICompilationUnit compilationUnit = member.getCompilationUnit();
					ISourceRange sourceRange = member.getSourceRange();
					AssistContext assistContext = new AssistContext(compilationUnit, null, sourceRange.getOffset(),
							sourceRange.getLength());
					ASTNode node = assistContext.getCoveringNode();

					if (node instanceof MethodDeclaration) {
						MethodDeclaration decl = (MethodDeclaration) node;
						// SimpleName name = decl.getName();
						if (problemType.equals(AutowireDependencyProvider.TOO_MANY_MATCHING_BEANS)) {
							proposals.addAll(new QualifierAnnotationQuickAssistProcessor().getAssists(decl,
									assistContext));
						}
						else if (problemType.equals(AutowireDependencyProvider.REQUIRED_NO_MATCH)) {
							proposals.addAll(new AutowireRequiredNotFoundAnnotationQuickAssistProcessor().getAssists(
									decl, assistContext));
						}
					}
					else if (node instanceof FieldDeclaration) {
						FieldDeclaration decl = (FieldDeclaration) node;
						List<VariableDeclarationFragment> fragments = decl.fragments();
						// SimpleName name = fragments.get(0).getName();
						if (fragments.size() > 0) {
							if (problemType.equals(AutowireDependencyProvider.TOO_MANY_MATCHING_BEANS)) {
								proposals.addAll(new QualifierAnnotationQuickAssistProcessor().getAssists(decl,
										assistContext));
							}
							else if (problemType.equals(AutowireDependencyProvider.REQUIRED_NO_MATCH)) {
								proposals.addAll(new AutowireRequiredNotFoundAnnotationQuickAssistProcessor()
										.getAssists(decl, assistContext));
							}

						}

					}

					List<IMarkerResolution> resolutions = new ArrayList<IMarkerResolution>();
					for (IJavaCompletionProposal proposal : proposals) {
						if (proposal instanceof IMarkerResolution) {
							resolutions.add((IMarkerResolution) proposal);
						}
					}
					return resolutions.toArray(new IMarkerResolution[resolutions.size()]);

				}
			}
			catch (JavaModelException e) {
				StatusHandler.log(e.getStatus());
			}
		}

		return new IMarkerResolution[0];
	}

	public boolean hasResolutions(IMarker marker) {
		String problemType = marker.getAttribute(AutowireDependencyProvider.AUTOWIRE_PROBLEM_TYPE, null);
		String handle = marker.getAttribute("JAVA_HANDLE", null);
		return problemType != null && handle != null;
	}

}
