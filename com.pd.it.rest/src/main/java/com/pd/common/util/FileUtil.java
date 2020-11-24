package com.pd.common.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileUtil {

	public static String readAll(File file) {
		// TODO Auto-generated method stub
		return null;
	}

	public static void writeOrOverwrite(String str, String filePath, String fileName) {
		System.out.println(str);
		FileWriter fw = null;
		try {
			File dir = new File(filePath);
			if (!dir.exists()) {
				dir.mkdirs();
			}
			File file = new File(filePath + fileName);
			fw = new FileWriter(file, false);
			fw.append(str);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fw != null) {
				try {
					fw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
