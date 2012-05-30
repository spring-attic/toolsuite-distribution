/******************************************************************************************
 * Copyright (c) 2009 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.ui.editors.batch.graph.parts;

import com.springsource.sts.config.flow.parts.SequentialActivityPart;
import com.springsource.sts.config.ui.editors.batch.graph.model.JobModelElement;

/**
 * @author Leo Dos Santos
 * @author Christian Dupuis
 */
public class JobGraphicalEditPart extends SequentialActivityPart {

	public JobGraphicalEditPart(JobModelElement job) {
		super(job);
	}

	@Override
	public JobModelElement getModelElement() {
		return (JobModelElement) getModel();
	}

}
