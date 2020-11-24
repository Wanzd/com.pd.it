package com.pd.common.util;

public class IntFactory {
	public static int str(String str) {
		if (str == null) {
			return 0;
		}
		str=str.trim();
		try {
			return Integer.valueOf(str);
		} catch (NumberFormatException e) {
			return 0;
		}
	}
}
