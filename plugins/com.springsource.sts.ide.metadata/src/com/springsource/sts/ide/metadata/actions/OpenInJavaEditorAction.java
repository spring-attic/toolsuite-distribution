/******************************************************************************************
 * Copyright (c) 2009 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.ide.metadata.actions;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.actions.BaseSelectionListenerAction;
import org.springframework.ide.eclipse.core.io.FileResource;
import org.springframework.ide.eclipse.core.model.IModelSourceLocation;
import org.springframework.ide.eclipse.ui.SpringUIUtils;

import com.springsource.sts.ide.metadata.MetadataUIImages;
import com.springsource.sts.ide.metadata.core.RequestMappingAnnotationMetadata;
import com.springsource.sts.ide.metadata.core.RequestMappingMethodAnnotationMetadata;
import com.springsource.sts.ide.metadata.ui.RequestMappingMethodToClassMap;

/**
 * @author Leo Dos Santos
 */
public class OpenInJavaEditorAction extends BaseSelectionListenerAction {

	public OpenInJavaEditorAction() {
		super(Messages.OpenInJavaEditorAction_TITLE);
		setImageDescriptor(MetadataUIImages.DESC_OBJS_JAVA_FILE);
	}

	@Override
	public void run() {
		IModelSourceLocation sourceLocation = null;
		IStructuredSelection selection = getStructuredSelection();
		Object obj = selection.getFirstElement();

		if (obj instanceof RequestMappingAnnotationMetadata) {
			RequestMappingAnnotationMetadata annotation = (RequestMappingAnnotationMetadata) obj;
			sourceLocation = annotation.getElementSourceLocation();
		} else if (obj instanceof RequestMappingMethodToClassMap) {
			RequestMappingMethodAnnotationMetadata annotation = ((RequestMappingMethodToClassMap) obj)
					.getMethodMetadata();
			sourceLocation = annotation.getElementSourceLocation();
		}

		if (sourceLocation != null) {
			SpringUIUtils.openInEditor(((FileResource) sourceLocation
					.getResource()).getRawFile(),
					sourceLocation.getStartLine(), true);
		}
	}

}
