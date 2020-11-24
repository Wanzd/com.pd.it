package com.pd.common.util;

import static com.pd.common.util.StaticTool.strCap;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class EnumX {

    /**
     * 通用查询枚举
     * 
     * @param enumClass
     * @param from
     * @param code
     * @return
     */
    public static <T extends Enum> T getEnum(Class<T> enumClass, String from, String code) {
        if (!enumClass.isEnum()) {
            return null;
        }
        try {
            Class<T> enumClass2 = (Class<T>) enumClass;
            T[] enumArr = enumClass2.getEnumConstants();

            Method getFromMethod = enumClass2.getMethod("get" + strCap(from));
            List<T> validList = Arrays.asList(enumArr).stream().filter(vo -> {
                try {
                    return code.equals(getFromMethod.invoke(vo) + "");
                } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                }
                return false;
            }).collect(Collectors.toList());
            if (validList.size() == 0) {
                return null;
            }
            return validList.get(0);
        } catch (NoSuchMethodException | SecurityException | IllegalArgumentException e) {
            e.printStackTrace();
        }
        return null;
    }
}
