package com.pd.it.common.itf;

import org.apache.ibatis.annotations.Param;

import com.pd.it.common.exception.BusinessException;

public interface IInsertInfoOperation<VO> {

    int insertInfo(@Param("fo") VO vo) throws BusinessException;

}
