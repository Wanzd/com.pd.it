package com.pd.it.common.itf;

import org.apache.ibatis.annotations.Param;

import com.pd.it.common.exception.BusinessException;

public interface IDeleteInfoOperation<VO> {

	int deleteInfo(@Param("vo") VO vo) throws BusinessException;

}
