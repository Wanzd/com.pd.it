package com.pd.common.util;

import java.sql.Clob;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.alibaba.fastjson.JSON;
import com.pd.base.exception.BusinessException;
import com.pd.standard.itf.IQueryInfoOperation;

public class StaticTool {

    public final static String BLANK = "";

    public static boolean isNull(Object obj) {
        return obj == null;
    }

    public static boolean isEmpty(Object obj) {
        if (obj instanceof String) {
            String str = (String) obj;
            return str.trim().length() == 0;
        }
        if (obj instanceof List) {
            List list = (List) obj;
            return list.size() == 0;
        }
        return obj == null;
    }

    public static String objToJsonStr(Object obj) {
        return JSON.toJSONString(obj);
    }

    public static String clobToStr(Clob clob) {
        return StringFactory.clobToStr(clob);
    }

    public static String objToStr(Object in) {
        return StringFactory.objToStr(in);
    }

    public static String strCap(String str) {
        return StringFactory.cap(str);
    }

    public static String strDecap(String str) {
        return StringFactory.decap(str);
    }

    public static String dateToStr(Date date, String formatter) {
        DateFormat df = new SimpleDateFormat(formatter);
        return df.format(date);
    }

    public static <T> T nvl(T in, T defaultValue) {
        return in != null ? in : defaultValue;
    }

    public static <T> List<T> strToList(String str, Class<T> outClass) {
        try {
            return JSON.parseArray(str, outClass);
        } catch (Exception e) {
            System.out.println("StringX.toList start:" + str);
            System.out.println("StringX.toList failed:" + e.getMessage());
        }
        return null;
    }

    public static void sort(List list) {
        Collections.sort(list);
    }

    public static <IN> void sort(List<IN> list, Comparator<IN> comparator) {
        Collections.sort(list, comparator);
    }

    public static <IN> Optional<IN> load(IN in) {
        return Optional.ofNullable(in);
    }

    public static void assertNull(Object obj, String errorMsg) throws BusinessException {
        if (obj == null) {
            throw new BusinessException(errorMsg);
        }
    }

    public static <FO, DTO> String queryJson(IQueryInfoOperation<FO, DTO> op, FO fo) throws BusinessException {
        return QueryBridge.queryJson(op, fo);
    }

    public static <FO, DTO> DTO queryInfo(IQueryInfoOperation<FO, DTO> op, FO fo) throws BusinessException {
        return QueryBridge.queryInfo(op, fo);
    }

    public static List emptyList() {
        return Collections.EMPTY_LIST;
    }

    public static <OUT> OUT toObj(Object in, Class<OUT> outClass) {
        if (in == null) {
            return null;
        }
        if (in.getClass().equals(outClass)) {
            return (OUT) in;
        }
        String jsonString = null;
        if (in instanceof String) {
            jsonString = (String) in;
        } else {
            jsonString = JSON.toJSONString(in);
        }
        return JSON.parseObject(jsonString, outClass);
    }

    public static <OUT> OUT attr(Object target, String attrName, Class<OUT> outClass) {
        if (target instanceof Map) {
            Map map = (Map) target;
            Object attr = map.get(attrName);
            if (attr == null) {
                return null;
            }
            return toObj(attr, outClass);
        }
        return Reflects.field(target, outClass, attrName);
    }

}
