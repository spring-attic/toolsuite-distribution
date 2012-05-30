/******************************************************************************************
 * Copyright (c) 2008 - 2009 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.wizard.template.infrastructure.ui;

import java.util.Map;

/**
 * @author Terry Denney
 * @author Leo Dos Santos
 * @author Christian Dupuis
 */
public interface WizardUI {
	public void collectInput(Map<String, Object> collectedInput, Map<String, String> inputKinds);
}
