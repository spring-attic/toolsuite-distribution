/******************************************************************************************
 * Copyright (c) 2009 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.roo.core.model;

import java.util.Set;

/**
 * @author Christian Dupuis
 */
public interface IRooInstallListener {

	void installChanged(Set<IRooInstall> installs);

}
