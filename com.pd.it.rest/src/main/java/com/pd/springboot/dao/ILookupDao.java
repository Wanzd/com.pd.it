package com.pd.springboot.dao;

import org.apache.ibatis.annotations.Mapper;

import com.pd.businessobject.LookupFO;
import com.pd.businessobject.LookupVO;
import com.pd.it.common.itf.IBaseDao;
import com.pd.it.common.itf.IQueryComboOperation;

@Mapper
public interface ILookupDao extends IBaseDao<LookupFO, LookupVO>, IQueryComboOperation {}