/******************************************************************************************
 * Copyright (c) 2010 - 2011 GoPivotal, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.internal.policy;

import org.eclipse.core.runtime.IProduct;
import org.eclipse.core.runtime.Platform;
import org.eclipse.equinox.internal.p2.ui.sdk.SDKPolicy;
import org.eclipse.equinox.p2.ui.Policy;

/**
 * P2 UI {@link Policy} implementation with the same functionality as the normal
 * {@link SDKPolicy} but not allowing the "Apply now" button when installing new
 * bundles.
 * @author Steffen Pingel
 * @author Christian Dupuis
 * @since 2.5.0
 */
public class NoApplyPolicy extends SDKPolicy {

	public NoApplyPolicy() {
		super();

		// Suppress the apply now button only if we really run inside STS
		IProduct product = Platform.getProduct();
		if (product != null && "com.springsource.sts.ide".equals(product.getId())) {
			setRestartPolicy(RESTART_POLICY_PROMPT);
		}
	}

}
