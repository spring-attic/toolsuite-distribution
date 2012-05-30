/******************************************************************************************
 * Copyright (c) 2008 - 2009 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.wizard.template.infrastructure.processor;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

/**
 * @author Terry Denney
 * @author Leo Dos Santos
 * @author Christian Dupuis
 */
public abstract class FileUtil {
	private static String[] BINARY_EXTENSIONS = new String[] { "jar", "gif", "jpg", "jpeg", ".class" };


	public static void copy(File source, File target) throws IOException {
		FileChannel sourceChannel = new FileInputStream(source).getChannel();
		FileChannel targetChannel = new FileOutputStream(target).getChannel();
		sourceChannel.transferTo(0, sourceChannel.size(), targetChannel);
		sourceChannel.close();
		targetChannel.close();
	}

	public static boolean isBinaryFile(File file) {
		String extension = FileUtil.getExtension(file);
		if (extension != null) {
			for (String binaryExtension : BINARY_EXTENSIONS) {
				if (binaryExtension.equals(extension)) {
					return true;
				}
			}
		}
		return false;
	}

	public static String getExtension(File file) {
		String fileName = file.getName();
		int extensionIndex = fileName.lastIndexOf('.');
		if (extensionIndex == -1) {
			return null;
		}
		return fileName.substring(extensionIndex + 1);
	}
}
