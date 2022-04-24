package com.pd.it.common.util;

import java.util.Collection;
import java.util.StringJoiner;

public class StringTool {

    public static final String Y_FLAG = "Y";

    public static String joinStr(Collection<String> strs,String joinStr){
        StringJoiner sj=new StringJoiner(joinStr);
        strs.forEach(str->sj.add(str));
        return sj.toString();
    }
}
