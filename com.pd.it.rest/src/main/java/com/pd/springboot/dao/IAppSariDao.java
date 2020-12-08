package com.pd.springboot.dao;

import org.apache.ibatis.annotations.Mapper;

import com.pd.it.common.businessobject.MapVO;
import com.pd.standard.web.IStandardDao;

@Mapper
public interface IAppSariDao extends IStandardDao<MapVO, MapVO> {
    void initChinaData();
}