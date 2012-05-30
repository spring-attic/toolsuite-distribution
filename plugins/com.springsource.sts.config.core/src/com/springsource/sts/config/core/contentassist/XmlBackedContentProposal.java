/******************************************************************************************
 * Copyright (c) 2008 - 2009 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.core.contentassist;

import org.eclipse.jface.fieldassist.IContentProposal;
import org.eclipse.jface.text.contentassist.ICompletionProposal;
import org.eclipse.swt.graphics.Image;

/**
 * This interface extends {@link IContentProposal} in order to make it behave
 * more like {@link ICompletionProposal}.
 * @author Leo Dos Santos
 * @author Christian Dupuis
 * @since 2.0.0
 */
public interface XmlBackedContentProposal extends IContentProposal {

	/**
	 * Returns the image to be displayed in the list of completion proposals.
	 * The image would typically be shown to the left of the display string.
	 * 
	 * @return the image to be shown or null if no image is desired
	 */
	public Image getImage();

}
