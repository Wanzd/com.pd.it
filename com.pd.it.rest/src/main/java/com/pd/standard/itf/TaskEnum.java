package com.pd.standard.itf;

import com.pd.it.common.itf.IAttrName;

public enum TaskEnum implements IAttrName {
    JOB_INFO_PARSE_TODAY_TASK("jobInfoParseTodayTask");
    private String name;

    TaskEnum(String name) {
        this.name = name;
    }
}
