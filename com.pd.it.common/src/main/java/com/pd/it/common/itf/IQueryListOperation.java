package com.pd.it.common.itf;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.pd.it.common.exception.BusinessException;

public interface IQueryListOperation<FO, DTO> {

	List<DTO> queryList(@Param("fo") FO in) throws BusinessException;
}
