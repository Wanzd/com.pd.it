package com.pd.standard.itf;

public enum TaskEnum implements IAttrName {
	JOB_INFO_PARSE_TODAY_TASK("jobInfoParseTodayTask");
	private String name;

	TaskEnum(String name) {
		this.name = name;
	}
}
