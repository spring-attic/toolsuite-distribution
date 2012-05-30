package com.springsource.sts.internal.ide.help;

import java.util.Collection;
import java.util.List;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

class NameUrlContentProvider implements IStructuredContentProvider {

	public void dispose() {
		// nothing to dispose
	}

	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		// do nothing
	}

	@SuppressWarnings("rawtypes")
	public Object[] getElements(Object inputElement) {
		if (inputElement instanceof AbstractNameUrlPreferenceModel) {
			List<NameUrlPair> pairs = ((AbstractNameUrlPreferenceModel) inputElement).getElements();
			return pairs.toArray();
		}
		else {
			if (inputElement instanceof Object[]) {
				return (Object[]) inputElement;
			}
			if (inputElement instanceof Collection) {
				return ((Collection) inputElement).toArray();
			}
			return new Object[0];
		}

	}

}