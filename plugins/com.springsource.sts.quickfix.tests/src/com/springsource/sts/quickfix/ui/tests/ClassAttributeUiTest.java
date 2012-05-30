/******************************************************************************************
 * Copyright (c) 2009 - 2010 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.quickfix.ui.tests;

import java.io.IOException;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotEditor;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotText;
import org.eclipse.ui.IEditorPart;
import org.junit.Test;
import org.springsource.ide.eclipse.commons.tests.util.StsTestUtil;

import com.springsource.sts.config.core.IConfigEditor;
import com.springsource.sts.config.tests.util.StsBotConfigEditor;

/**
 * @author Terry Denney
 * @author Steffen Pingel
 */
public class ClassAttributeUiTest extends AbstractQuickfixUiTestCase {

	@Override
	protected IEditorPart openEditor() throws CoreException, IOException {
		return openFileInEditor("src/class-attribute.xml");
	}

	@Test
	public void testClassAttributeQuickfix() throws CoreException, IOException {
		IEditorPart editor = openEditor();

		assertNotNull("Expects editor to open", editor);
		assertTrue("Expects spring config editor", editor instanceof IConfigEditor);
		StsBotConfigEditor configEditor = getBot().activeConfigEditor();
		configEditor.navigateTo(8, 37);

		// bot.sleep(StsTestUtil.WAIT_TIME);
		StsTestUtil.waitForEditor(editor);
		int quickfixListItemCount = configEditor.getQuickfixListItemCount();
		assertTrue("Expects quick fixes", quickfixListItemCount > 1);
		configEditor.getStyledText().setFocus();

		configEditor.quickfix("Change to Account (com.test)");
		configEditor.getStyledText().setFocus();

		// bot.sleep(StsTestUtil.WAIT_TIME);
		StsTestUtil.saveAndWaitForEditor(editor);
		quickfixListItemCount = configEditor.getQuickfixListItemCount();
		assertTrue("Expects no quick fix", quickfixListItemCount == 1);
		configEditor.getStyledText().setFocus();

		assertEquals("Expects quickfix to change class name", "	<bean id=\"classTest2\" class=\"com.test.Account\"/>",
				configEditor.getTextOnCurrentLine());
	}

	@Test
	public void testCreateClassQuickfix() throws CoreException, IOException {
		IEditorPart editor = openEditor();

		assertNotNull("Expects editor to open", editor);
		assertTrue("Expects spring config editor", editor instanceof IConfigEditor);
		StsBotConfigEditor configEditor = getBot().activeConfigEditor();
		configEditor.navigateTo(21, 34);

		// bot.sleep(StsTestUtil.WAIT_TIME);
		StsTestUtil.waitForEditor(editor);
		int quickfixListItemCount = configEditor.getQuickfixListItemCount();
		assertTrue("Expects quick fixes", quickfixListItemCount > 1);
		assertFalse("Expects no duplicate quick fixes", checkNoDuplicateQuickfixes(configEditor));
		configEditor.getStyledText().setFocus();

		configEditor.quickfix("Create class 'Account'");
		bot.shell("New Class").activate();

		SWTBotText text = bot.text(1);
		assertNotNull(text);
		String str = text.getText();
		assertNotNull(str);
		assertEquals("com.test.ui", str);

		bot.button("Finish").click();

		// bot.sleep(StsTestUtil.WAIT_TIME);
		StsTestUtil.saveAndWaitForEditor(editor);
		configEditor.getStyledText().setFocus();

		quickfixListItemCount = configEditor.getQuickfixListItemCount();
		assertTrue("Expects no quick fix", quickfixListItemCount == 1);

		SWTBotEditor javaEditor = bot.editorByTitle("Account.java");
		assertNotNull(javaEditor);
		javaEditor.close();
	}

}
