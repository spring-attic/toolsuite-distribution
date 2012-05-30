/******************************************************************************************
 * Copyright (c) 2010 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.quickfix.jdt.processors;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IImportDeclaration;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.dom.rewrite.ImportRewrite;
import org.eclipse.jdt.internal.corext.codemanipulation.StubUtility;
import org.eclipse.text.edits.TextEdit;

/**
 * @author Terry Denney
 */
public class JdtQuickfixUtils {

	public static TextEdit getTextEditForImport(ICompilationUnit cu, String importTypeNString) {
		try {
			IImportDeclaration requestMappingDecl = cu.getImport(importTypeNString);
			if (requestMappingDecl == null || !requestMappingDecl.exists()) {
				ImportRewrite importRewrite = StubUtility.createImportRewrite(cu, true);
				importRewrite.addImport(importTypeNString);
				return importRewrite.rewriteImports(null);
			}

		}
		catch (JavaModelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (CoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
