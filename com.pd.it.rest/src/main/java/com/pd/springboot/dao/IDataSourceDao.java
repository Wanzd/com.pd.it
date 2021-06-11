package com.pd.springboot.dao;

import org.apache.ibatis.annotations.Mapper;

import com.pd.it.common.itf.IBaseDao;
import com.pd.model.datasource.vo.DataSourceFO;
import com.pd.model.datasource.vo.DataSourceVO;

@Mapper
public interface IDataSourceDao extends IBaseDao<DataSourceFO, DataSourceVO> {}