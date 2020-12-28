package com.pd.it.common.util;

import static com.pd.it.base.constant.BaseConst.HTTP_CODE_ERROR;
import static java.util.stream.Collectors.toList;

import java.sql.Clob;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.cglib.core.internal.Function;
import org.springframework.validation.ObjectError;

import com.alibaba.fastjson.JSON;
import com.pd.it.common.businessobject.PageVO;
import com.pd.it.common.businessobject.ResultVO;
import com.pd.it.common.exception.BusinessException;
import com.pd.it.common.itf.BaseService;
import com.pd.it.common.itf.IQueryInfoOperation;

public class StaticTool {

    public final static String BLANK = "";

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

    public static <FO, DTO> DTO queryInfo(IQueryInfoOperation<FO, DTO> op, FO fo) throws BusinessException {
        return DbTool.queryInfo(op, fo);
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

    public static <FO, VO> VO queryInfo(Object bean, FO fo) throws BusinessException {
        if (bean instanceof BaseService) {
            BaseService baseService = (BaseService) bean;
            return (VO) queryInfo(baseService.getBridge(), fo);
        }
        if (bean instanceof IQueryInfoOperation) {
            IQueryInfoOperation operation = (IQueryInfoOperation) bean;
            return (VO) operation.queryInfo(fo);
        }
        return null;
    }

    public static <FO, VO> List<VO> queryList(Object field, FO fo) throws BusinessException {
        return null;
    }

    public static <FO, VO> List<VO> queryPagedList(Object field, FO fo, PageVO pageVO) throws BusinessException {
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

    public static <IN, OUT> OUT build(IN in, Function<IN, OUT> func) {
        if (in == null) {
            return null;
        }
        return func.apply(in);
    }

    public static <IN, OUT> OUT builds(IN in, Class<OUT> outClass, Function... func) {
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
}
