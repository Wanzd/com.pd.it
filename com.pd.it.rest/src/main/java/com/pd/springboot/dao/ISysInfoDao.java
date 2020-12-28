package com.pd.springboot.dao;

import org.apache.ibatis.annotations.Mapper;

import com.pd.businessobject.SysInfoFO;
import com.pd.businessobject.SysInfoVO;
import com.pd.it.common.itf.IBaseDao;

@Mapper
public interface ISysInfoDao extends IBaseDao<SysInfoFO, SysInfoVO> {}
