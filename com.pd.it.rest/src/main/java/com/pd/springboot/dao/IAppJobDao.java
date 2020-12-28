package com.pd.springboot.dao;

import org.apache.ibatis.annotations.Mapper;

import com.pd.it.common.businessobject.MapVO;
import com.pd.it.common.itf.IBaseDao;

@Mapper
public interface IAppJobDao extends IBaseDao<MapVO, MapVO> {}