package com.pd.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OperationEnum {
	QUERY_INFO("queryInfo"), QUERY_JSON("queryJson"), QUERY_LIST("queryList"), QUERY_PAGED_LIST("queryPagedList");
	private String code;
}
