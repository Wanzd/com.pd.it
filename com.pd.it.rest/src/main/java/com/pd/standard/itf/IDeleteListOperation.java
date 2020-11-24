package com.pd.standard.itf;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface IDeleteListOperation<VO> {

	int deleteList(@Param("list") List<VO> list);

}
