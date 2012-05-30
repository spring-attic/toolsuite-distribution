/******************************************************************************************
 * Copyright (c) 2009 - 2010 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.quickfix.ui.tests;

import java.io.IOException;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner;
import org.eclipse.ui.IEditorPart;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springsource.ide.eclipse.commons.tests.util.StsTestUtil;

import com.springsource.sts.config.core.IConfigEditor;
import com.springsource.sts.config.tests.util.StsBotConfigEditor;

/**
 * @author Terry Denney
 * @author Steffen Pingel
 */
@RunWith(SWTBotJunit4ClassRunner.class)
public class MethodAttributeUiTest extends AbstractQuickfixUiTestCase {

	@Override
	protected IEditorPart openEditor() throws CoreException, IOException {
		return openFileInEditor("src/method-attribute.xml");
	}

	@Test
	public void testFactoryMethodQuickfix() throws CoreException, IOException {
		IEditorPart editor = openEditor();

		assertNotNull("Expects editor to open", editor);
		assertTrue("Expects spring config editor", editor instanceof IConfigEditor);

		StsBotConfigEditor configEditor = getBot().activeConfigEditor();
		configEditor.navigateTo(8, 83);

		// bot.sleep(StsTestUtil.WAIT_TIME);
		StsTestUtil.waitForEditor(editor);
		int quickfixListItemCount = configEditor.getQuickfixListItemCount();
		assertTrue("Expects quick fixes", quickfixListItemCount > 1);
		assertFalse("Expects no duplicate quick fixes", checkNoDuplicateQuickfixes(configEditor));
		configEditor.getStyledText().setFocus();

		configEditor.quickfix("Change to createDefaultAccount() Account - AccountManager");
		configEditor.getStyledText().setFocus();

		// bot.sleep(StsTestUtil.WAIT_TIME);
		StsTestUtil.saveAndWaitForEditor(editor);
		quickfixListItemCount = configEditor.getQuickfixListItemCount();
		assertTrue("Expects no quick fix", quickfixListItemCount == 1);
		configEditor.getStyledText().setFocus();

		assertEquals(
				"Expects quickfix to change property name",
				"	<bean id=\"factoryMethodTest2\" class=\"com.test.AccountManager\" factory-method=\"createDefaultAccount\"/>",
				configEditor.getTextOnCurrentLine());
	}
}
