package com.pd.standard.itf;

import org.apache.ibatis.annotations.Param;

public interface IInsertInfoOperation<VO> {

    int insertInfo(@Param("fo") VO vo);

}
