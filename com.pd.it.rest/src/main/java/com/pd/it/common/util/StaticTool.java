package com.pd.it.common.util;

import static com.pd.it.base.constant.BaseConst.HTTP_CODE_ERROR;
import static java.util.stream.Collectors.toList;

import java.math.BigDecimal;
import java.sql.Clob;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Stream;

import org.springframework.cglib.core.internal.Function;
import org.springframework.validation.ObjectError;

import com.alibaba.fastjson.JSON;
import com.pd.it.common.businessobject.PageVO;
import com.pd.it.common.businessobject.ResultVO;
import com.pd.it.common.exception.BusinessException;
import com.pd.it.common.itf.BaseService;
import com.pd.it.common.itf.IQueryInfoOperation;
import com.pd.it.common.itf.IQueryListOperation;

public class StaticTool {

    public final static String BLANK = "";
    public final static String ZERO_STR = "0";
    public final static BigDecimal ZERO = BigDecimal.ZERO;

    public final static String ENCODE_UTF8 = "UTF8";

    public static boolean isNull(Object obj) {
        return obj == null;
    }

    public static boolean eq(Object in1, Object in2) {
        if (in1 == null && in2 == null) {
            return true;
        }
        if (in1 == null ^ in2 == null) {
            return false;
        }
        return in1.equals(in2);
    }

    public static boolean ne(Object in1, Object in2) {
        return !eq(in1, in2);
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

    public static boolean notEmpty(Object obj) {
        return !isEmpty(obj);
    }

    public static Double toDouble(Object in) {
        if (in == null) {
            return null;
        }
        if (in instanceof String) {
            return Double.parseDouble((String) in);
        }
        return 0d;
    }

    public static String str(Object in) {
        if (in == null) {
            return null;
        }
        if (in instanceof String) {
            return (String) in;
        }
        if (in instanceof Clob) {
            return StringFactory.clobToStr((Clob) in);
        }
        return JSON.toJSONString(in);
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

    public static Integer toInt(Object in) {
        if (in == null) {
            return null;
        }
        String jsonString = null;
        if (in instanceof String) {
            return Integer.parseInt((String) in);
        }
        return null;
    }

    public static Integer toInteger(String in) {
        if (in == null) {
            return null;
        }
        if (in instanceof String) {
            return Integer.valueOf((String) in);
        }
        return null;
    }

    public static BigDecimal toDecimal(Object in) {
        if (in == null) {
            return ZERO;
        }
        if (in instanceof String) {
            return new BigDecimal((String) in);
        }
        return new BigDecimal(in.toString());
    }

    public static BigDecimal mul(Object in1, Object in2) {
        return toDecimal(in1).multiply(toDecimal(in2));
    }

    public static String toStr(Object in) {
        if (in == null) {
            return null;
        }
        String jsonString = null;
        if (in instanceof String) {
            return (String) in;
        }
        return toJsonStr(in);
    }

    public static String toJsonStr(Object in) {
        if (in == null) {
            return null;
        }
        String jsonString = null;
        if (in instanceof String) {
            return (String) in;
        }
        return JSON.toJSONString(in);
    }

    public static String formatStr(String tpl, Object... in) {
        return String.format(tpl, in);
    }

    public static String formatDate(Date date, String tpl) {
        return new SimpleDateFormat(tpl).format(date);
    }

    public static Date addDate(Date date, int dayCnt) {
        return new Date(date.getTime() + dayCnt * 86400000);
    }

    public static <T> List<T> list(T[] in) {
        if (in == null) {
            return null;
        }
        return Stream.of(in).collect(toList());
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

    public static <FO, VO> String queryJson(Object bean, FO fo) throws BusinessException {
        if (bean instanceof IQueryInfoOperation) {
            IQueryInfoOperation op = (IQueryInfoOperation) bean;
            return op.queryJson(fo);
        }
        return null;
    }

    public static Object validError(List<ObjectError> in) {
        ResultVO<List<String>> rsVO = new ResultVO();
        rsVO.setCode(HTTP_CODE_ERROR);
        rsVO.setMsg("valid fail");
        List<String> list = in.stream().map(error -> {
            return attr(error, "field", String.class) + error.getDefaultMessage();
        }).collect(toList());
        rsVO.setData(list);
        return rsVO;
    }

    public static <OUT> OUT getBean(String beanName, Class<OUT> outClass) {
        return SpringUtil.getBean(beanName, outClass);
    }

    public static <IN, OUT> OUT apply(IN in, Function<IN, OUT> func) {
        if (in == null) {
            return null;
        }
        return func.apply(in);
    }

    public static <IN, OUT> OUT applys(IN in, Class<OUT> outClass, Function... func) {
        if (in == null) {
            return null;
        }
        Object tmp = in;
        for (Function eachFunc : func) {
            tmp = eachFunc.apply(tmp);
            if (tmp == null) {
                return null;
            }
        }
        return (OUT) tmp;
    }

    public static <T> T supply(Supplier<T> supplier) {
        if (supplier == null) {
            return null;
        }
        return supplier.get();
    }

    public static int sum(Integer... list) {
        int sum = 0;
        for (Integer eachValue : list) {
            sum += nvl(eachValue, 0);
        }
        return sum;
    }

    public static int count(int value, Integer... list) {
        int cnt = 0;
        for (Integer eachValue : list) {
            if (eachValue == null) {
                continue;
            }
            cnt += value == eachValue.intValue() ? 1 : 0;
        }
        return cnt;
    }

    public static int countNotEmpty(Integer... list) {
        int cnt = 0;
        for (Integer eachValue : list) {
            cnt += eachValue != null ? 1 : 0;
        }
        return cnt;
    }

    public static int countEmpty(Integer... list) {
        int cnt = 0;
        for (Integer eachValue : list) {
            cnt += eachValue != null ? 0 : 1;
        }
        return cnt;
    }

    public static Integer[] row(Integer[][] arr, int rowNum) {
        List<Integer> rs = new ArrayList<>();
        for (int i = 0, total = arr.length; i < total; i++) {
            try {
                rs.add(arr[i][rowNum]);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return rs.toArray(new Integer[rs.size()]);
    }

    public static Integer[][][] toIntegerArray3(String... in) {
        Integer[][][] rs = new Integer[in.length][][];
        for (int i = 0, total = in.length; i < total; i++) {
            rs[i] = toIntegerArray2(in[i]);
        }
        return rs;
    }

    public static Integer[][] toIntegerArray2(String in) {
        String[] split = in.split(",");
        Integer[][] rs = new Integer[split.length][];
        for (int i = 0, total = split.length; i < total; i++) {
            List<Integer> tmpList = new ArrayList<>();
            String tmpStr = split[i];
            for (int j = 0, total2 = tmpStr.length(); j < total2; j++) {
                String tarStr = tmpStr.substring(j, j + 1);
                tmpList.add(toInteger(tarStr));
            }
            rs[i] = tmpList.toArray(new Integer[tmpList.size()]);
        }
        return rs;
    }
}
