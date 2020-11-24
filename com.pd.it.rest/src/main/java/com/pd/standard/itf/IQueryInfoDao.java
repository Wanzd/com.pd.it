package com.pd.standard.itf;

import org.apache.ibatis.annotations.Param;

import com.pd.base.exception.BusinessException;

public interface IQueryInfoDao<FO, DTO> extends IQueryInfoOperation<FO, DTO> {
	@Override
	DTO queryInfo(@Param("fo") FO in) throws BusinessException;
}
