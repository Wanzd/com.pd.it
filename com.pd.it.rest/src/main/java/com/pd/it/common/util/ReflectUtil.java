package com.pd.it.common.util;

import static com.pd.it.common.util.StaticTool.eq;
import static com.pd.it.common.util.StaticTool.first;
import static com.pd.it.common.util.StaticTool.list;
import static com.pd.it.common.util.StaticTool.strCap;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.stream.Collectors;

import com.pd.it.common.itf.IIdentity;

public class ReflectUtil {
    public static Method getMethod(Object in, String methodName) {
        if (in == null) {
            return null;
        }
        List<Method> methods = methods(in, methodName);
        return first(methods);
    }

    public static List<Method> methods(Object in) {
        if (in == null) {
            return null;
        }
        List<Method> rsList = list(in.getClass().getMethods());
        return rsList;
    }

    public static List<Method> methods(Object in, String methodName) {
        if (in == null) {
            return null;
        }
        List<Method> rsList = list(in.getClass().getMethods());
        rsList.addAll(list(in.getClass().getDeclaredMethods()));
        List<String> nameList = rsList.stream().map(vo->vo.getName()).collect(Collectors.toList());
        rsList = rsList.stream().filter(b -> b.getName().equals(methodName)).collect(Collectors.toList());
        return rsList;
    }

    public static Object field(Object in, String attrName) {
        try {
            Field field = in.getClass().getDeclaredField(attrName);
            field.setAccessible(true);
            return field.get(in);
        } catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static <IN, OUT> OUT field(IN in, Class<OUT> outClass, String attrName) {
        try {
            Method[] methods = in.getClass().getMethods();
            String getMethodName = "get" + strCap(attrName);
            for (Method eachMethod : methods) {
                if (eq(getMethodName, eachMethod.getName())) {
                    eachMethod.setAccessible(true);
                    return (OUT) eachMethod.invoke(in);
                }
            }
        } catch (SecurityException | IllegalArgumentException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        try {
            Field field = in.getClass().getDeclaredField(attrName);
            field.setAccessible(true);
            return (OUT) field.get(in);
        } catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static <IN, OUT> OUT firstExistField(IN in, Class<OUT> outClass, String attrNames) {
        String[] attrArray = attrNames.split(",");
        for (String eachAttr : attrArray) {
            OUT field = field(in, outClass, eachAttr);
            if (field != null) {
                return field;
            }
        }
        return null;
    }

    public static <IN, OUT> OUT firstExistField(IN in, Class<OUT> outClass, String... attrNames) {
        for (String eachAttr : attrNames) {
            OUT field = field(in, outClass, eachAttr);
            if (field != null) {
                return field;
            }
        }
        return null;
    }

    public static <T> T identity(Object in) {
        if (in instanceof IIdentity) {
            return (T) ((IIdentity) in).getId();
        }
        return null;
    }

    public static Class getClass(Object in) {
        if (in == null) {
            return null;
        }
        return in.getClass();
    }
}
