package com.pd.it.common.itf;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface IInsertListOperation<VO> {

	int insertList(@Param("list") List<VO> list);

}
