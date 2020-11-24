package com.pd.standard.itf;

public interface RestPathConst {
	String QUERY_INFO = "/queryInfo";// 查询单行数据
	String QUERY_LIST = "/queryList";// 查询多行数据
	String QUERY_PAGED_LIST = "/queryPagedList/{pageSize}/{curPage}";// 查询分页数据
	String QUERY_COMBO = "/queryCombo";// 查询下拉框
	String QUERY_DIMENSION_LIST = "/query{dimension}List";// 按维度查询多行数据
	String QUERY_DIMENSION_PAGED_LIST = "/query{dimension}PagedList/{pageSize}/{curPage}";// 按维度分页查询数据
}
