/******************************************************************************************
 * Copyright (c) 2009 - 2010 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.quickfix.tests;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.jface.text.IDocument;
import org.eclipse.wst.sse.core.StructuredModelManager;
import org.eclipse.wst.xml.core.internal.document.DOMModelImpl;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMDocument;
import org.springframework.ide.eclipse.beans.core.BeansCorePlugin;
import org.springframework.ide.eclipse.beans.core.internal.model.BeansModel;
import org.springframework.ide.eclipse.beans.core.internal.model.BeansProject;
import org.springframework.ide.eclipse.beans.core.model.IBeansConfig;
import org.springframework.ide.eclipse.beans.core.model.IBeansProject;
import org.springsource.ide.eclipse.commons.tests.util.StsTestCase;
import org.w3c.dom.Node;


/**
 * Abstract test case with set up for creating a new test project and setting up
 * a DOMModel and document
 * @author Terry Denney
 * @author Leo Dos Santos
 * @author Christian Dupuis
 * @author Steffen Pingel
 */
@SuppressWarnings("restriction")
public class AbstractQuickfixTestCase extends StsTestCase {

	protected IFile file;

	protected IProject project;

	protected DOMModelImpl model;

	protected Node beansNode;

	protected IDocument document;

	protected void copyProjectCreateDocument(String name) throws Exception {
		super.setUp();
		project = createPredefinedProject("Test");

		file = (IFile) project.findMember(name);
		model = (DOMModelImpl) StructuredModelManager.getModelManager().getModelForRead(file);
		IDOMDocument domDocument = model.getDocument();
		beansNode = domDocument.getDocumentElement();

		document = model.getStructuredDocument();

		((BeansModel) BeansCorePlugin.getModel()).start();
		IBeansProject springProject = BeansCorePlugin.getModel().getProject(project);
		if (springProject != null && springProject instanceof BeansProject) {
			((BeansProject) springProject).addConfig(file, IBeansConfig.Type.MANUAL);
		}

	}

	@Override
	protected String getBundleName() {
		return "com.springsource.sts.quickfix.tests";
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();
	}

	@Override
	protected void tearDown() throws Exception {
		model.releaseFromRead();

		super.tearDown();
	}

}
