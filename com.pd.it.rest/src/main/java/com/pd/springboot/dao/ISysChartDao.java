package com.pd.springboot.dao;

import org.apache.ibatis.annotations.Mapper;

import com.pd.businessobject.SysChartBO;
import com.pd.businessobject.SysChartFO;
import com.pd.it.common.itf.IBaseDao;
import com.pd.it.common.itf.IQueryComboOperation;

@Mapper
public interface ISysChartDao extends IBaseDao<SysChartFO, SysChartBO>, IQueryComboOperation {}