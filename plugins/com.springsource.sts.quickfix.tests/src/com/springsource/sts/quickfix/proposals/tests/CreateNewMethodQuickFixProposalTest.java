/******************************************************************************************
 * Copyright (c) 2009 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.quickfix.proposals.tests;

import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.wst.sse.core.internal.provisional.text.ITextRegion;
import org.eclipse.wst.xml.core.internal.document.AttrImpl;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMNode;
import org.springframework.ide.eclipse.core.java.Introspector;

import com.springsource.sts.config.core.schemas.BeansSchemaConstants;
import com.springsource.sts.quickfix.QuickfixUtils;
import com.springsource.sts.quickfix.proposals.CreateNewMethodQuickFixProposal;
import com.springsource.sts.quickfix.tests.QuickfixTestUtil;

/**
 * @author Terry Denney
 * @author Leo Dos Santos
 * @author Christian Dupuis
 */
@SuppressWarnings("restriction")
public class CreateNewMethodQuickFixProposalTest extends AbstractBeanFileQuickfixTestCase {

	private IJavaProject javaProject;

	private void applyProposal(IDOMNode beanNode, String methodName, int argCount, String className) throws Exception {
		AttrImpl classAttr = (AttrImpl) beanNode.getAttributes().getNamedItem(BeansSchemaConstants.ATTR_CLASS);
		ITextRegion valueRegion = classAttr.getValueRegion();

		int offset = getOffset(valueRegion, beanNode);
		int length = getLength(valueRegion, false);

		// IPackageFragmentRoot[] allPackageFragmentRoots =
		// javaProject.getAllPackageFragmentRoots();
		// IPackageFragmentRoot sourceRoot = null;
		// for(IPackageFragmentRoot packageFragmentRoot:
		// allPackageFragmentRoots) {
		// if (!(packageFragmentRoot instanceof JarPackageFragmentRoot)) {
		// sourceRoot = packageFragmentRoot;
		// }
		// }

		String[] paramTypes = new String[argCount];
		for (int i = 0; i < argCount; i++) {
			paramTypes[i] = "Object";
		}
		CreateNewMethodQuickFixProposal proposal = QuickfixUtils.getNewMethodQuickFixProposal(methodName, null,
				paramTypes, javaProject, className, offset, length, methodName, false, false, "property");
		proposal.apply(document);
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();

		openBeanEditor("src/create-method-proposal.xml");
		javaProject = JavaCore.create(project);
	}

	public void testCreateInitMethod() throws Exception {
		IDOMNode beanNode = QuickfixTestUtil.getNode(BeansSchemaConstants.ELEM_BEAN, "createMethodTest2", beansNode
				.getChildNodes());

		String className = "com.test.AccountManager";
		IType type = javaProject.findType(className);

		assertNull("Method createNewAccount should not exist before proposal is applied", Introspector.findMethod(type,
				"createNewAccount", 0, Introspector.Public.YES, Introspector.Static.NO));
		applyProposal(beanNode, "createNewAccount", 0, className);
		assertNotNull("Expects method createNewAccount to be created", Introspector.findMethod(type,
				"createNewAccount", 0, Introspector.Public.YES, Introspector.Static.NO));
	}

	public void testCreateProperty() throws Exception {
		IDOMNode beanNode = QuickfixTestUtil.getNode(BeansSchemaConstants.ELEM_BEAN, "createMethodTest1", beansNode
				.getChildNodes());

		String className = "com.test.Account";
		IType type = javaProject.findType(className);

		assertNull("Method setName should not exist before proposal is applied", Introspector.findMethod(type,
				"setName", 1, Introspector.Public.YES, Introspector.Static.NO));
		applyProposal(beanNode, "setName", 1, className);
		assertNotNull("Expects method setName to be created", Introspector.findMethod(type, "setName", 1,
				Introspector.Public.YES, Introspector.Static.NO));
	}
}
