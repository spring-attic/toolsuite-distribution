/******************************************************************************************
 * Copyright (c) 2009 - 2010 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.ui.editors.batch;

import org.eclipse.swt.widgets.Composite;

import com.springsource.sts.config.ui.editors.AbstractConfigDetailsPart;
import com.springsource.sts.config.ui.editors.AbstractConfigFormPage;
import com.springsource.sts.config.ui.editors.AbstractConfigMasterPart;
import com.springsource.sts.config.ui.editors.AbstractNamespaceMasterDetailsBlock;

/**
 * @author Leo Dos Santos
 * @author Christian Dupuis
 */
public class BatchMasterDetailsBlock extends AbstractNamespaceMasterDetailsBlock {

	public static final String DOCS_SPRINGBATCH = "http://static.springsource.org/spring-batch/reference/html/index.html"; //$NON-NLS-1$ 

	public BatchMasterDetailsBlock(AbstractConfigFormPage page) {
		super(page);
	}

	@Override
	protected AbstractConfigMasterPart createMasterSectionPart(AbstractConfigFormPage page, Composite parent) {
		return new BatchMasterPart(page, parent);
	}

	@Override
	public AbstractConfigDetailsPart getDetailsPage(Object key) {
		return new BatchDetailsPart(getMasterPart(), DOCS_SPRINGBATCH);
	}

}
