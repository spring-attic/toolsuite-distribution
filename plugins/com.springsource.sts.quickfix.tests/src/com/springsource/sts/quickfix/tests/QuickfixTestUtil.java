/******************************************************************************************
 * Copyright (c) 2009 - 2010 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.quickfix.tests;

import org.eclipse.wst.xml.core.internal.provisional.document.IDOMNode;
import org.w3c.dom.Attr;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Util class for quickfix test cases
 * @author Terry Denney
 * @author Leo Dos Santos
 * @author Christian Dupuis
 */
@SuppressWarnings("restriction")
public class QuickfixTestUtil {

	public static IDOMNode getFirstNode(String nodeName, NodeList children) {
		for (int i = 0; i < children.getLength(); i++) {
			Node child = children.item(i);
			String currentNodeName = child.getNodeName();
			if (currentNodeName == null) {
				continue;
			}

			if (currentNodeName.equals(nodeName)) {
				return (IDOMNode) child;
			}
		}

		return null;
	}

	public static IDOMNode getNode(String nodeName, int pos, NodeList children) {
		int currentPos = 0;
		for (int i = 0; i < children.getLength(); i++) {
			Node child = children.item(i);
			String currentNodeName = child.getNodeName();
			if (currentNodeName == null) {
				continue;
			}

			if (currentNodeName.equals(nodeName)) {
				if (currentPos == pos) {
					return (IDOMNode) child;
				}

				currentPos++;
			}
		}
		return null;
	}

	public static IDOMNode getNode(String nodeName, String nodeValue, NodeList children) {
		IDOMNode node = getNode(nodeName, "id", nodeValue, children);
		if (node != null) {
			return node;
		}
		return getNode(nodeName, "name", nodeValue, children);
	}

	public static IDOMNode getNode(String nodeName, String attrName, String attrValue, NodeList children) {
		for (int i = 0; i < children.getLength(); i++) {
			Node child = children.item(i);
			String currentNodeName = child.getNodeName();
			if (currentNodeName == null) {
				continue;
			}

			if (currentNodeName.equals(nodeName)) {
				NamedNodeMap attributes = child.getAttributes();
				Attr nameAttr = (Attr) attributes.getNamedItem(attrName);
				if (nameAttr == null) {
					continue;
				}

				if (nameAttr.getNodeValue().equals(attrValue)) {
					return (IDOMNode) child;
				}
			}
		}

		return null;
	}

}
