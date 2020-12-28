package com.pd.it.common.itf;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.pd.it.common.businessobject.PageVO;
import com.pd.it.common.exception.BusinessException;

public interface IQueryPagedListOperation<FO, DTO> {
    List<DTO> queryPagedList(@Param("fo") FO in, @Param("page") PageVO page) throws BusinessException;

    int queryCount(@Param("fo") FO fo) throws BusinessException;
}
