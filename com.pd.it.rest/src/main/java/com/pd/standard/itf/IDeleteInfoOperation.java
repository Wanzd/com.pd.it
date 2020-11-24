package com.pd.standard.itf;

import org.apache.ibatis.annotations.Param;

import com.pd.base.exception.BusinessException;

public interface IDeleteInfoOperation<VO> {

	int deleteInfo(@Param("vo") VO vo) throws BusinessException;

}
