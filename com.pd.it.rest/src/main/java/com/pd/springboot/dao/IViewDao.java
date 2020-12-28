package com.pd.springboot.dao;

import org.apache.ibatis.annotations.Mapper;

import com.pd.it.common.businessobject.MapVO;
import com.pd.it.common.itf.IBaseDao;
import com.pd.it.common.itf.IQueryComboDao;

@Mapper
public interface IViewDao extends IBaseDao<MapVO, MapVO>, IQueryComboDao {}