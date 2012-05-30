/******************************************************************************************
 * Copyright (c) 2009 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.tests.util.gef;

import org.eclipse.gef.EditPart;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;

/**
 * @author Leo Dos Santos
 */
public class EditPartOfType<T extends EditPart> extends BaseMatcher<T> {

	@Factory
	public static <T extends EditPart> Matcher<T> editPartOfType(Class<? extends EditPart> type) {
		return new EditPartOfType<T>(type);
	}

	private final Class<? extends EditPart> type;

	EditPartOfType(Class<? extends EditPart> type) {
		this.type = type;
	}

	public void describeTo(Description description) {
		description.appendText("of type '").appendText(type.getSimpleName()).appendText("'");
	}

	public boolean matches(Object item) {
		return type.isInstance(item);
	}

}
