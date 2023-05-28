package com.pd.it.base.util;

import static java.util.stream.Collectors.toList;

import java.lang.reflect.Array;
import java.lang.reflect.UndeclaredThrowableException;
import java.math.BigDecimal;
import java.sql.Clob;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Stream;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.pd.it.base.exception.BusinessException;
import com.pd.it.base.factory.ResultVOFactory;
import com.pd.it.base.itf.IBuilder;
import com.pd.it.base.itf.ICalculator;
import com.pd.it.base.itf.IProcessor;
import com.pd.it.base.itf.IValidable;
import com.pd.it.base.itf.IValidator;
import com.pd.it.base.vo.ResultVO;

public class StaticTool {

	public static final String BLANK = StringTool.BLANK;
	public final static String ZERO_STR = "0";
	public final static String SUCCESS = "success";
	public final static BigDecimal ZERO = BigDecimal.ZERO;

	public final static String SDF_D = "YYYY-MM-dd";
	public final static String SDF_DT = "YYYY-MM-dd HH:mm:ss";

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

	public static int len(Object obj) {
		if(obj==null) {
			return 0;
		}
		if (obj instanceof String) {
			return ((String) obj).trim().length();
		}
		if (obj instanceof Array) {
			return Array.getLength((Array) obj);
		}
		if (obj instanceof Collection) {
			return ((Collection) obj).size();
		}
		return 0;
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

	public static String strCap(String str) {
		return StringTool.cap(str);
	}

	public static String strDate(Date _date) {
		return formatDate(_date, SDF_D);
	}

	public static String strDecap(String str) {
		return StringTool.decap(str);
	}

	public static String dateToStr(Date date, String formatter) {
		DateFormat df = new SimpleDateFormat(formatter);
		return df.format(date);
	}

	public static <T> T nvl(T in, T defaultValue) {
		return in != null ? in : defaultValue;
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
		} else if (in instanceof Enum) {
			SerializeConfig config = new SerializeConfig();
			config.configEnumAsJavaBean((Class) in.getClass());
			jsonString = JSON.toJSONString(in, config);
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
		return ReflectUtil.getField(target, outClass, attrName);
	}

	public static <IN, OUT> OUT apply(IN in, Function<IN, OUT> func) {
		if (in == null) {
			return null;
		}
		return func.apply(in);
	}

	public static <IN, OUT> OUT build(IN in, IBuilder<IN, OUT> builder) throws BusinessException {
		if (in == null) {
			return null;
		}
		return builder.build(in);
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

	public static String asStr(Object _in) {
		if (_in == null) {
			return null;
		}
		if (_in instanceof String) {
			return (String) _in;
		}
		if (_in instanceof Clob) {
			return StringTool.clobToStr((Clob) _in);
		}
		try {
			return JSON.toJSONStringWithDateFormat(_in, "yyyy-MM-dd HH:mm:ss");
		} catch (Exception e) {
			return _in.toString();
		}
	}

	public static <T> T asObj(Object _in, Class<T> _class) {
		if (_in == null) {
			return null;
		}
		String jsonStr = asStr(_in);
		return JSON.parseObject(jsonStr, _class);
	}

	public static <T> List<T> asList(T... inArray) {
		if (inArray == null) {
			return null;
		}
		return Arrays.asList(inArray);
	}

	public static <T> List<T> asList(Collection<T> ins) {
		if (ins == null) {
			return null;
		}
		return ins.stream().collect(toList());
	}

	public static <T> T first(Collection<T> collection) {
		if (isEmpty(collection)) {
			return null;
		}
		return collection.iterator().next();
	}

	public static <IN> ResultVO<IN> success(IN in) {
		ResultVO<IN> res = new ResultVO();
		res.setCode("S");
		res.setData(in);
		return res;
	}

	public static <T> ResultVO<T> error(String msg, Exception e) {
		ResultVO resVO = new ResultVO();
		resVO.setCode(ResultVO.ERROR);
		if (e instanceof UndeclaredThrowableException) {
			UndeclaredThrowableException ute = (UndeclaredThrowableException) e;
			resVO.setCode(ResultVO.TIMEOUT);
			resVO.setMsg(ute.getUndeclaredThrowable().getMessage());
		} else {
			resVO.setMsg(msg);
		}
		return resVO;
	}

	public static <T> ResultVO<T> timeout(String msg) {
		ResultVO resVO = new ResultVO();
		resVO.setCode(ResultVO.TIMEOUT);
		resVO.setMsg(msg);
		return resVO;
	}

	/**
	 * 通过入参和指定算子计算得到结�?
	 *
	 * @param in
	 *            主入参对�?
	 * @param calculator
	 *            算子
	 * @param <IN>
	 *            IN
	 * @param <OUT>
	 *            OUT
	 * @return 计算结果
	 */
	public static <IN, OUT> OUT cal(IN in, ICalculator<IN, OUT> calculator) {
		if (calculator == null) {
			return null;
		}
		return calculator.cal(in);
	}

	/**
	 * 通过入参和指定算子计算得到校验结�?
	 * 
	 * @param in
	 *            主入参对�?
	 * @param validator
	 *            算子
	 * @param <IN>
	 *            IN
	 * @return 校验结果
	 */
	public static <IN> boolean valid(IN in, IValidator<IN> validator) {
		if (validator == null) {
			return false;
		}
		return validator.valid(in);
	}

	/**
	 * 通过入参和指定算子执行流程处�?
	 *
	 * @param in
	 *            主入参对�?
	 * @param processor
	 *            算子
	 * @param <IN>
	 *            IN
	 */
	public static <IN> void process(IN in, IProcessor<IN> processor) {
		processor.process(in);
	}

	public static ResultVO<String> valid(Object obj) {
		if (obj instanceof IValidable) {
			String validResult = ((IValidable) obj).valid();
			if (isNull(validResult)) {
				return null;
			}
			return ResultVOFactory.error(validResult);
		}
		return null;
	}

	public static <T> int compare(T param1, T param2) {
		if (param1 instanceof Comparable) {
			return ((Comparable) param1).compareTo(param2);
		}
		return 0;
	}

	public static List<Integer> genInt(int from, int to) {
		List<Integer> resultList = new ArrayList<>();
		for (int i = from; i <= to; i++) {
			resultList.add(i);
		}
		return resultList;
	}

	public static String multy(String str, int cnt) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < cnt; i++) {
			sb.append(str);
		}
		return sb.toString();
	}
}
