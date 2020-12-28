package com.pd.springboot.dao;

import org.apache.ibatis.annotations.Mapper;

import com.pd.businessobject.AppMyFollowFO;
import com.pd.businessobject.AppMyFollowVO;
import com.pd.it.common.itf.IBaseDao;

@Mapper
public interface IAppMyFollowDao extends IBaseDao<AppMyFollowFO, AppMyFollowVO> {}
