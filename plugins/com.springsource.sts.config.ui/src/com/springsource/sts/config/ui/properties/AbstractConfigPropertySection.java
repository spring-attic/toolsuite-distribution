/******************************************************************************************
 * Copyright (c) 2010 - 2011 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.config.ui.properties;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.forms.widgets.ScrolledPageBook;
import org.eclipse.ui.views.properties.tabbed.AbstractPropertySection;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMAttr;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMElement;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMNode;

import com.springsource.sts.config.flow.model.Transition;
import com.springsource.sts.config.flow.parts.ActivityPart;
import com.springsource.sts.config.flow.parts.TransitionPart;
import com.springsource.sts.config.ui.editors.AbstractConfigEditor;
import com.springsource.sts.config.ui.editors.AbstractConfigSectionPart;

/**
 * @author Leo Dos Santos
 */
@SuppressWarnings("restriction")
public abstract class AbstractConfigPropertySection extends AbstractPropertySection {

	private AbstractConfigEditor editor;

	protected IDOMElement input;

	protected ScrolledPageBook pageBook;

	protected Map<IDOMElement, AbstractConfigSectionPart> partMap;

	public AbstractConfigPropertySection() {
		partMap = new HashMap<IDOMElement, AbstractConfigSectionPart>();
	}

	@Override
	public void createControls(Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage) {
		super.createControls(parent, aTabbedPropertySheetPage);
		parent.setLayoutData(new GridData(GridData.FILL_BOTH));
		if (aTabbedPropertySheetPage instanceof SpringConfigPropertySheetPage) {
			SpringConfigPropertySheetPage page = (SpringConfigPropertySheetPage) aTabbedPropertySheetPage;
			editor = page.getConfigEditor();
			editor.addPropertiesPage(this);
		}
		pageBook = getWidgetFactory().createPageBook(parent, SWT.V_SCROLL | SWT.H_SCROLL);
	}

	@Override
	public void dispose() {
		if (editor != null) {
			editor.removePropertiesPage(this);
		}
		super.dispose();
	}

	protected AbstractConfigEditor getConfigEditor() {
		return editor;
	}

	public IDOMElement getInput() {
		return input;
	}

	@Override
	public void refresh() {
		if (input != null) {
			AbstractConfigSectionPart sectionPart = partMap.get(input);
			if (sectionPart != null) {
				sectionPart.refresh();
			}
		}
	}

	@Override
	public void setInput(IWorkbenchPart part, ISelection selection) {
		super.setInput(part, selection);
		input = null;
		if (selection instanceof IStructuredSelection) {
			IStructuredSelection struct = (IStructuredSelection) selection;
			Object obj = struct.getFirstElement();
			if (obj instanceof IDOMElement) {
				input = (IDOMElement) obj;
			}
			else if (obj instanceof TreeItem) {
				TreeItem item = (TreeItem) obj;
				if (item.getData() instanceof IDOMElement) {
					input = (IDOMElement) item.getData();
				}
			}
			else if (obj instanceof ActivityPart) {
				ActivityPart activity = (ActivityPart) obj;
				input = activity.getModelElement().getInput();
			}
			else if (obj instanceof TransitionPart) {
				TransitionPart trans = (TransitionPart) obj;
				IDOMNode node = ((Transition) trans.getModel()).getInput();
				if (node instanceof IDOMElement) {
					input = (IDOMElement) node;
				}
				else if (node instanceof IDOMAttr) {
					input = (IDOMElement) ((IDOMAttr) node).getOwnerElement();
				}
			}
		}
	}

}
