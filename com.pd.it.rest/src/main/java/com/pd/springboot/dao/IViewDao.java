package com.pd.springboot.dao;

import org.apache.ibatis.annotations.Mapper;

import com.pd.it.common.businessobject.MapVO;
import com.pd.it.common.itf.IQueryComboDao;
import com.pd.it.common.itf.IQueryDao;

@Mapper
public interface IViewDao extends IQueryDao<MapVO, MapVO>, IQueryComboDao {
}