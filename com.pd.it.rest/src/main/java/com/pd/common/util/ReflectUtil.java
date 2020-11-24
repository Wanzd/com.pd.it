package com.pd.common.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ReflectUtil {

	public static Object getField(Object target, String attrName) {
		try {
			Field field = target.getClass().getDeclaredField(attrName);
			field.setAccessible(true);
			return field.get(target);
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			// exception in plan
		}
		return null;
	}

	public static <T> T getField(Object target, Class<T> outClass, String attrName) {
		try {
			Field field = target.getClass().getDeclaredField(attrName);
			field.setAccessible(true);
			return (T) field.get(target);
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			// exception in plan
		}
		return null;
	}

	public static <IN> Object firstExistField(IN in, String attrNames) {
		String[] attrArray = attrNames.split(",");
		for (String eachAttr : attrArray) {
			Object field = getField(in, eachAttr);
			if (field != null) {
				return field;
			}
		}
		return null;
	}

	public static <IN, OUT> OUT firstExistField(IN in, Class<OUT> outClass, String attrNames) {
		String[] attrArray = attrNames.split(",");
		for (String eachAttr : attrArray) {
			OUT field = getField(in, outClass, eachAttr);
			if (field != null) {
				return field;
			}
		}
		return null;
	}

	public static <IN, OUT> OUT firstExistField(IN in, Class<OUT> outClass, String... attrNames) {
		for (String eachAttr : attrNames) {
			OUT field = getField(in, outClass, eachAttr);
			if (field != null) {
				return field;
			}
		}
		return null;
	}

	/**
	 * 获取类的静态方法
	 * 
	 * @param class1
	 * @param code
	 */
	public static Method getClassMethod(Class cls, String methodName) {
		Method method;
		try {
			Method[] methods = cls.getDeclaredMethods();
			List<Method> methodList = Arrays.asList(methods).stream().filter(obj -> obj.getName().equals(methodName))
					.collect(Collectors.toList());
			if (methodList.size() > 0) {
				return methodList.get(0);
			}
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		return null;
	}
}
