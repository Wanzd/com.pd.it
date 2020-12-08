package com.pd.it.common.itf;

import org.apache.ibatis.annotations.Param;

public interface IInsertInfoOperation<VO> {

    int insertInfo(@Param("fo") VO vo);

}
