/******************************************************************************************
 * Copyright (c) 2008 - 2009 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.ide.osgi;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.core.runtime.preferences.IScopeContext;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.core.variables.IDynamicVariable;
import org.eclipse.core.variables.IDynamicVariableResolver;

/**
 * {@link IDynamicVariableResolver} that resolves $
 * {osgi.console.port}
 * placeholders to the preference defined value.
 * @author Christian Dupuis
 * @author Leo Dos Santos
 * @since 1.0.0
 */
public class ConsolePortVariableResolver implements IDynamicVariableResolver {

	public String resolveValue(IDynamicVariable variable, String argument)
			throws CoreException {
		IScopeContext context = new InstanceScope();
		IEclipsePreferences preferences = context.getNode(OsgiPlugin.PLUGIN_ID);
		return preferences.get(OsgiPlugin.PORT_PREFERENCE_KEY,
				OsgiPlugin.DEFAULT_PORT);
	}

}
