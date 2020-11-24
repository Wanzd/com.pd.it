package com.pd.springboot.dao;

import org.apache.ibatis.annotations.Mapper;

import com.pd.businessobject.MapVO;
import com.pd.standard.itf.IQueryDao;

@Mapper
public interface ISysObjDao extends IQueryDao<MapVO, String> {
}