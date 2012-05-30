/******************************************************************************************
 * Copyright (c) 2009 - 2011 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.roo.core.model;

import java.net.URL;

import org.eclipse.core.runtime.IStatus;

/**
 * @author Christian Dupuis
 */
public interface IRooInstall {
	
	String SUPPORTED_VERSION = "[1.1.0.RELEASE, 2.0)";

	URL[] getClasspath();

	String getHome();

	String getName();

	String getVersion();

	boolean isDefault();
	
	IStatus validate();

}
