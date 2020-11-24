package com.pd.standard.itf;

import java.util.List;

import com.pd.base.exception.BusinessException;

public interface IQueryListAction<IN, OUT> {
	default List<OUT> queryList(IN fo) throws BusinessException {
		return null;
	}
}
