package com.pd.springboot.dao;

import org.apache.ibatis.annotations.Mapper;

import com.pd.businessobject.MapVO;
import com.pd.businessobject.TestVO;
import com.pd.standard.web.IStandardDao;

@Mapper
public interface ITestDao extends IStandardDao<MapVO, MapVO> {

	int insert(TestVO testVO);
}
