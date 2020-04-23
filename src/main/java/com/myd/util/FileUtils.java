package com.myd.util;

import java.io.File;

public class FileUtils {

	/**
	 * 检测与创建一级、二级文件夹、文件名 这里我通过传入的两个字符串来做一级文件夹和二级文件夹名称
	 * 通过此种办法我们可以做到根据用户的选择保存到相应的文件夹下
	 */
	@SuppressWarnings("unused")
	public static File creatFolder(String fileName, String path) {
		File file = null;

		File firstFolder = new File(path); // 一级文件夹
		firstFolder.mkdirs();
		if (firstFolder.exists()) { // 如果二级文件夹也存在，则创建文件
			file = new File(firstFolder, fileName);
		} else { // 如果二级文件夹不存在，则创建二级文件夹
			firstFolder.mkdirs();
			file = new File(firstFolder, fileName);
		}
		return file;
	}
	
}
