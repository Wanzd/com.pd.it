package com.pd.common.util;

import static com.pd.common.util.StaticTool.BLANK;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.sql.Clob;
import java.sql.SQLException;

import com.alibaba.fastjson.JSON;
import com.pd.businessobject.MapVO;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class StringFactory {

    public static String from(Object in) {
        if (in == null) {
            return null;
        }
        return JSON.toJSONString(in);
    }

    public static String freeMarker(String ftlName, MapVO vo) {
        Writer out = new StringWriter();
        Configuration cfg = new Configuration(Configuration.getVersion());
        try {
            Template tmp = cfg.getTemplate(ftlName);
            tmp.process(vo, out);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String file(File file) {
        return FileUtil.readAll(file);
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
            // 得到流
            BufferedReader br = new BufferedReader(is);
            String tmp = null;
            StringBuffer sb = new StringBuffer();
            while ((tmp = br.readLine()) != null) {// 执行循环将字符串全部取出付值给StringBuffer由StringBuffer转成STRING
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
