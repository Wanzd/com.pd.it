package com.pd.it.common.itf;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface IUpdateListOperation<VO> {

	int updateList(@Param("list") List<VO> list);

}
