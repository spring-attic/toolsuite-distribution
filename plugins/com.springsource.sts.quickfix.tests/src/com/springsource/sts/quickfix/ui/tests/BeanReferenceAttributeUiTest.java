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
public class BeanReferenceAttributeUiTest extends AbstractQuickfixUiTestCase {

	@Override
	protected IEditorPart openEditor() throws CoreException, IOException {
		return openFileInEditor("src/bean-ref-attribute.xml");
	}

	@Test
	public void testBeanReferenceQuickfix() throws CoreException, IOException {
		final IEditorPart editor = openEditor();

		assertNotNull("Expects editor to open", editor);
		assertTrue("Expects spring config editor", editor instanceof IConfigEditor);

		StsBotConfigEditor configEditor = getBot().activeConfigEditor();
		configEditor.navigateTo(21, 29);

		// bot.sleep(StsTestUtil.WAIT_TIME);
		StsTestUtil.waitForEditor(editor);
		int quickfixListItemCount = configEditor.getQuickfixListItemCount();
		assertTrue("Expects quick fixes", quickfixListItemCount > 1);
		assertFalse("Expects no duplicate quick fixes", checkNoDuplicateQuickfixes(configEditor));
		configEditor.getStyledText().setFocus();

		configEditor.quickfix("Change to account [Account] - src/bean-ref-attribute.xml");
		configEditor.getStyledText().setFocus();

		StsTestUtil.saveAndWaitForEditor(editor);
		// bot.sleep(StsTestUtil.WAIT_TIME);
		quickfixListItemCount = configEditor.getQuickfixListItemCount();
		assertTrue("Expects no quick fix", quickfixListItemCount == 1);
		configEditor.getStyledText().setFocus();

		assertEquals("Expects quickfix to change property name", "		<constructor-arg ref=\"account\"/>",
				configEditor.getTextOnCurrentLine());
	}

}
