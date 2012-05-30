/******************************************************************************************
 * Copyright (c) 2009 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.tests.util.gef;

import org.eclipse.gef.EditPart;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;

import com.springsource.sts.config.flow.parts.ActivityPart;

/**
 * @author Leo Dos Santos
 */
public class WithLabel<T extends EditPart> extends BaseMatcher<T> {

	@Factory
	public static <T extends EditPart> Matcher<T> withLabel(String label) {
		return new WithLabel<T>(label);
	}

	private final String label;

	WithLabel(String label) {
		this.label = label;
	}

	public void describeTo(Description description) {
		description.appendText("with label '").appendText(label).appendText("'");
	}

	public boolean matches(Object item) {
		if (label == null || label.trim().length() == 0) {
			return false;
		}

		String toMatch = null;
		// Too specific to current visual editor implementation. What if future
		// editors don't operate on ActivityPart? There is no common way of
		// getting a label from an EditPart.
		if (item instanceof ActivityPart) {
			ActivityPart part = (ActivityPart) item;
			toMatch = part.getModelElement().getName();
		}

		if (toMatch == null) {
			return false;
		}
		else {
			return label.equals(toMatch);
		}
	}

}
