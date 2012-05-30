/******************************************************************************************
 * Copyright (c) 2009 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.tests.util.gef;

import org.eclipse.gef.EditPart;
import org.hamcrest.Matcher;


/**
 * @author Leo Dos Santos
 */
public abstract class EditPartMatcherFactory {

	public static <T extends EditPart> Matcher<T> editPartOfType(Class<? extends EditPart> type) {
		return EditPartOfType.editPartOfType(type);
	}

	public static <T extends EditPart> Matcher<T> withLabel(String label) {
		return WithLabel.withLabel(label);
	}

}
