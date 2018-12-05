package com.search.searchrank.util;

import java.io.File;
import java.net.URL;
import java.net.URLDecoder;

public class MyPath {

	public static String getProjectPath() {
		URL url = MyPath.class.getProtectionDomain().getCodeSource().getLocation();
		String filePath = null;
		try {
			filePath = URLDecoder.decode(url.getPath(), "utf-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (filePath.endsWith(".jar"))
			filePath = filePath.substring(0, filePath.lastIndexOf("/") + 1);
		File file = new File(filePath);
		filePath = file.getAbsolutePath();
		return filePath;
	}

	public static String getRealPath() {
		String realPath = MyPath.class.getClassLoader().getResource("").getFile();
		File file = new File(realPath);
		realPath = file.getAbsolutePath();
		try {
			realPath = URLDecoder.decode(realPath, "utf-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return realPath;
	}

}
