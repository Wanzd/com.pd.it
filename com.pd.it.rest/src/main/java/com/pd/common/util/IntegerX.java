package com.pd.common.util;

import java.util.Date;

public class IntegerX {

	public static Integer getAge(Date birthday) {
		if (birthday == null) {
			return null;
		}
		Date today = new Date();
		int yearDiff = today.getYear() - birthday.getYear();
		int dayDiff = (today.getMonth() * 100 + today.getDate() - today.getMonth() * 100 - today.getDate()) >= 0 ? 0
				: 1;
		return yearDiff - dayDiff;
	}

}
