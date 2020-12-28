package com.pd.springboot.dao;

import org.apache.ibatis.annotations.Mapper;

import com.pd.businessobject.LookupTypeFO;
import com.pd.businessobject.LookupTypeVO;
import com.pd.it.common.itf.IBaseDao;

@Mapper
public interface ILookupTypeDao extends IBaseDao<LookupTypeFO, LookupTypeVO> {}