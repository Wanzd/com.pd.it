package com.pd.it.base.util;

import static com.pd.it.base.util.StaticTool.toObj;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.sql.Clob;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.StringJoiner;

import com.alibaba.fastjson.JSON;
import com.pd.it.base.exception.BusinessException;

import freemarker.template.Template;

public class StringTool {

	public static final String BLANK = "";
	public static final String Y_FLAG = "Y";
	public static final String N_FLAG = "N";
	public static final String HTML_TAB = "�?";

	public static String joinStr(Collection<String> strs, String joinStr) {
		StringJoiner sj = new StringJoiner(joinStr);
		strs.forEach(str -> sj.add(str));
		return sj.toString();
	}

	public static String from(Object in) {
		if (in == null) {
			return null;
		}
		return JSON.toJSONString(in);
	}

	public static String between(String target, String fromStr, String toStr) {
		int startIdx = target.indexOf(fromStr);
		if (startIdx < 0) {
			return null;
		}
		String tmpStr = target.substring(startIdx + fromStr.length());
		int endIdx = tmpStr.indexOf(toStr);
		if (endIdx < 0) {
			return null;
		}
		tmpStr = tmpStr.substring(0, endIdx);
		return tmpStr;
	}

	public static String clobToStr(Clob clob) {
		String reString = "";
		Reader is;
		try {
			is = clob.getCharacterStream();
			// 得到�?
			BufferedReader br = new BufferedReader(is);
			String tmp = null;
			StringBuffer sb = new StringBuffer();
			while ((tmp = br.readLine()) != null) {// 执行循环将字符串全部取出付�?�给StringBuffer由StringBuffer转成STRING
				sb.append(tmp);
			}
			reString = sb.toString();
			return reString;
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}
		return BLANK;
	}

	public static String objToStr(Object in) {
		if (in == null) {
			return null;
		}
		return in.toString();
	}

	public static String cap(String str) {
		if (str == null || str.length() == 0) {
			return str;
		}
		return str.substring(0, 1).toUpperCase() + str.substring(1);
	}

	public static String decap(String str) {
		if (str == null || str.length() == 0) {
			return str;
		}
		return str.substring(0, 1).toLowerCase() + str.substring(1);
	}

}
