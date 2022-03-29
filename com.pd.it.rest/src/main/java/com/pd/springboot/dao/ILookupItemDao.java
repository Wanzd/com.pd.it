package com.pd.springboot.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.pd.businessobject.LookupItemFO;
import com.pd.businessobject.LookupItemVO;
import com.pd.it.common.itf.IBaseDao;

@Mapper
public interface ILookupItemDao extends IBaseDao<LookupItemFO, LookupItemVO> {
    String queryValue(@Param("fo") LookupItemFO fo);
}