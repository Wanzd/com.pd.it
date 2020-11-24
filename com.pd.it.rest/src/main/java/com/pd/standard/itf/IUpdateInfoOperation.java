package com.pd.standard.itf;

import org.apache.ibatis.annotations.Param;

import com.pd.base.exception.BusinessException;

public interface IUpdateInfoOperation<VO> {

	int updateInfo(@Param("vo") VO vo) throws BusinessException;

}
