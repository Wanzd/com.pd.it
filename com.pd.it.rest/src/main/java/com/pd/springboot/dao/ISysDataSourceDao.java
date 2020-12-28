package com.pd.springboot.dao;

import org.apache.ibatis.annotations.Mapper;

import com.pd.businessobject.SysDataSourceBO;
import com.pd.businessobject.SysDataSourceFO;
import com.pd.it.common.itf.IBaseDao;

@Mapper
public interface ISysDataSourceDao extends IBaseDao<SysDataSourceFO, SysDataSourceBO> {}