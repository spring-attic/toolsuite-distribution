/******************************************************************************************
 * Copyright (c) 2010 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.roo.core.commands;

import java.util.List;

import org.springsource.ide.eclipse.commons.frameworks.core.internal.commands.ICommandParameter;
import org.springsource.ide.eclipse.commons.frameworks.core.internal.commands.ICommandParameterDescriptor;
import org.springsource.ide.eclipse.commons.frameworks.core.internal.commands.IFrameworkCommand;


/**
 * @author Christian Dupuis
 * @since 2.5.0
 */
public class RooCommandUtils {

	public static String constructCommandString(IFrameworkCommand command) {

		if (command == null) {
			return null;
		}

		final StringBuffer actualCommand = new StringBuffer();
		actualCommand.append(command.getCommandDescriptor().getName());
		actualCommand.append(" ");
		List<ICommandParameter> values = command.getParameters();
		if (values != null) {
			for (ICommandParameter value : values) {
				if (value.hasValue()) {

					ICommandParameterDescriptor descriptor = value.getParameterDescriptor();

					if (descriptor.requiresParameterNameInCommand()) {
						String prefix = descriptor.getParameterPrefix();
						String valueSeparator = descriptor.getValueSeparator();
						String name = descriptor.getName();
						if (prefix != null) {
							actualCommand.append(prefix);
						}
						actualCommand.append(name);
						if (valueSeparator != null) {
							actualCommand.append(valueSeparator);
						}
					}

					actualCommand.append(value.getValue());
					actualCommand.append(" ");
				}
			}
		}
		return actualCommand.toString();
	}

}
