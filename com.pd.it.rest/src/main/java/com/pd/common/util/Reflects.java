package com.pd.common.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;
import java.util.stream.Collectors;

import com.pd.standard.itf.IIdentity;

public class Reflects {
    public static List<Method> methods(Object in) {
        if (in == null) {
            return null;
        }
        List<Method> rsList = ListFactory.asList(in.getClass().getMethods());
        return rsList;
    }

    public static List<Method> methods(Object in, String methodName) {
        if (in == null) {
            return null;
        }
        List<Method> rsList = ListFactory.asList(in.getClass().getMethods());
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
