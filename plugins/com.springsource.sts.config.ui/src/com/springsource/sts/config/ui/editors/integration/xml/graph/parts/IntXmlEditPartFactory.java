/******************************************************************************************
 * Copyright (c) 2010 - 2011 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.ui.editors.integration.xml.graph.parts;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;

import com.springsource.sts.config.ui.editors.integration.xml.graph.model.MarshallingTransformerModelElement;
import com.springsource.sts.config.ui.editors.integration.xml.graph.model.UnmarshallingTransformerModelElement;
import com.springsource.sts.config.ui.editors.integration.xml.graph.model.ValidatingFilterModelElement;
import com.springsource.sts.config.ui.editors.integration.xml.graph.model.XpathFilterModelElement;
import com.springsource.sts.config.ui.editors.integration.xml.graph.model.XpathHeaderEnricherModelElement;
import com.springsource.sts.config.ui.editors.integration.xml.graph.model.XpathRouterModelElement;
import com.springsource.sts.config.ui.editors.integration.xml.graph.model.XpathSplitterModelElement;
import com.springsource.sts.config.ui.editors.integration.xml.graph.model.XpathTransformerModelElement;
import com.springsource.sts.config.ui.editors.integration.xml.graph.model.XslTransformerModelElement;

/**
 * @author Leo Dos Santos
 */
public class IntXmlEditPartFactory implements EditPartFactory {

	public EditPart createEditPart(EditPart context, Object model) {
		EditPart part = null;
		if (model instanceof MarshallingTransformerModelElement) {
			part = new MarshallingTransformerGraphicalEditPart((MarshallingTransformerModelElement) model);
		}
		else if (model instanceof UnmarshallingTransformerModelElement) {
			part = new UnmarshallingTransformerGraphicalEditPart((UnmarshallingTransformerModelElement) model);
		}
		else if (model instanceof ValidatingFilterModelElement) {
			part = new ValidatingFilterGraphicalEditPart((ValidatingFilterModelElement) model);
		}
		else if (model instanceof XpathFilterModelElement) {
			part = new XpathFilterGraphicalEditPart((XpathFilterModelElement) model);
		}
		else if (model instanceof XpathHeaderEnricherModelElement) {
			part = new XpathHeaderEnricherGraphicalEditPart((XpathHeaderEnricherModelElement) model);
		}
		else if (model instanceof XpathRouterModelElement) {
			part = new XpathRouterGraphicalEditPart((XpathRouterModelElement) model);
		}
		else if (model instanceof XpathSplitterModelElement) {
			part = new XpathSplitterGraphicalEditPart((XpathSplitterModelElement) model);
		}
		else if (model instanceof XpathTransformerModelElement) {
			part = new XpathTransformerGraphicalEditPart((XpathTransformerModelElement) model);
		}
		else if (model instanceof XslTransformerModelElement) {
			part = new XsltTransformerGraphicalEditPart((XslTransformerModelElement) model);
		}
		return part;
	}
}
