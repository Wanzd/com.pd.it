package com.pd.it.common.itf;

import java.util.List;

import com.pd.it.common.exception.BusinessException;

public interface IQueryListAction<IN, OUT> {
    default List<OUT> queryList(IN fo) throws BusinessException {
        return null;
    }
}
