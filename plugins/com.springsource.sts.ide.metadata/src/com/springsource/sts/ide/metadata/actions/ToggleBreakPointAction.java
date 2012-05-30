/******************************************************************************************
 * Copyright (c) 2009 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.ide.metadata.actions;

import org.eclipse.jdt.core.IMethod;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.internal.debug.ui.actions.ToggleBreakpointAdapter;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.actions.BaseSelectionListenerAction;

import com.springsource.sts.ide.metadata.MetadataUIImages;
import com.springsource.sts.ide.metadata.core.RequestMappingAnnotationMetadata;
import com.springsource.sts.ide.metadata.core.RequestMappingMethodAnnotationMetadata;
import com.springsource.sts.ide.metadata.ui.RequestMappingMethodToClassMap;

/**
 * @author Leo Dos Santos
 */
@SuppressWarnings("restriction")
public class ToggleBreakPointAction extends BaseSelectionListenerAction {

	private IWorkbenchPart workbenchPart;

	private ToggleBreakpointAdapter breakpointAdapter;

	public ToggleBreakPointAction(IWorkbenchPart workbenchPart) {
		super(Messages.ToggleBreakPointAction_TITLE);
		setImageDescriptor(MetadataUIImages.DESC_OBJS_BREAKPOINT);
		this.workbenchPart = workbenchPart;
		breakpointAdapter = new ToggleBreakpointAdapter();
	}

	@Override
	public void run() {
		IStructuredSelection selection = getStructuredSelection();
		Object obj = selection.getFirstElement();

		if (obj instanceof RequestMappingAnnotationMetadata) {
			RequestMappingAnnotationMetadata annotation = (RequestMappingAnnotationMetadata) obj;
			IType type = (IType) JavaCore.create(annotation.getClassHandle());
			breakpointAdapter.toggleClassBreakpoints(workbenchPart,
					new StructuredSelection(type));
		} else if (obj instanceof RequestMappingMethodToClassMap) {
			RequestMappingMethodAnnotationMetadata annotation = ((RequestMappingMethodToClassMap) obj)
					.getMethodMetadata();
			IMethod method = (IMethod) JavaCore.create(annotation
					.getHandleIdentifier());
			breakpointAdapter.toggleMethodBreakpoints(workbenchPart,
					new StructuredSelection(method));
		}
	}

}
