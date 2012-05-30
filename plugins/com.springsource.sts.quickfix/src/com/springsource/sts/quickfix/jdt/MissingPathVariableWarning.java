/******************************************************************************************
 * Copyright (c) 2011 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.quickfix.jdt;

import org.eclipse.core.resources.IFile;
import org.eclipse.jdt.core.compiler.CategorizedProblem;
import org.eclipse.jdt.core.compiler.IProblem;
import org.eclipse.jdt.core.dom.Annotation;

import com.springsource.sts.quickfix.jdt.util.UriTemplateVariable;

/**
 * Marker problem for missing @PathVariable
 * 
 * @author Terry Denney
 * @since 2.6
 */
public class MissingPathVariableWarning extends CategorizedProblem {

	public static final String MARKER_TYPE = "com.springsource.sts.jdt.quickfix.marker";

	public static final int PROBLEM_ID = IProblem.MethodRelated;

	private int lineNumber;

	private final String fileName;

	private final UriTemplateVariable variable;

	public MissingPathVariableWarning(Annotation annotation, UriTemplateVariable variable, IFile file, int lineNumber) {
		this.fileName = file.getName();
		this.variable = variable;
		this.lineNumber = lineNumber;
	}

	public String[] getArguments() {
		return new String[0];
	}

	@Override
	public int getCategoryID() {
		return CategorizedProblem.CAT_POTENTIAL_PROGRAMMING_PROBLEM;
	}

	public int getID() {
		return PROBLEM_ID;
	}

	public int getLineNumber() {
		return lineNumber;
	}

	@Override
	public String getMarkerType() {
		return MARKER_TYPE;
	}

	public String getMessage() {
		return "URI template variable \"" + variable.getVariableName() + "\" is not defined";
	}

	public char[] getOriginatingFileName() {
		return fileName.toCharArray();
	}

	public int getSourceEnd() {
		return variable.getOffset() + variable.getVariableName().length() - 1;
	}

	public int getSourceLineNumber() {
		return lineNumber;
	}

	public int getSourceStart() {
		return variable.getOffset();
	}

	public boolean isError() {
		return false;
	}

	public boolean isWarning() {
		return true;
	}

	public void setSourceEnd(int sourceEnd) {
	}

	public void setSourceLineNumber(int lineNumber) {
		this.lineNumber = lineNumber;
	}

	public void setSourceStart(int sourceStart) {
	}

}
