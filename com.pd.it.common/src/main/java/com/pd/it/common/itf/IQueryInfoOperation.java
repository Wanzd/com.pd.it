package com.pd.it.common.itf;

import org.apache.ibatis.annotations.Param;

import com.pd.it.common.exception.BusinessException;

public interface IQueryInfoOperation<FO, DTO> {
    DTO queryInfo(@Param("fo") FO in) throws BusinessException;

    DTO queryDetailInfo(@Param("fo") FO in) throws BusinessException;

    String queryJson(@Param("fo") FO in) throws BusinessException;
}
