package com.pd.it.common.itf;

import org.apache.ibatis.annotations.Param;

import com.pd.it.common.exception.BusinessException;

public interface IQueryInfoDao<FO, DTO> extends IQueryInfoOperation<FO, DTO> {
    @Override
    DTO queryInfo(@Param("fo") FO in) throws BusinessException;
}
