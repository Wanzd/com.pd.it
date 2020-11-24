package com.pd.springboot.dao;

import org.apache.ibatis.annotations.Mapper;

import com.pd.businessobject.AppMyFollowFO;
import com.pd.businessobject.AppMyFollowVO;
import com.pd.standard.web.IStandardDao;

@Mapper
public interface IAppMyFollowDao extends IStandardDao<AppMyFollowFO, AppMyFollowVO> {
}
