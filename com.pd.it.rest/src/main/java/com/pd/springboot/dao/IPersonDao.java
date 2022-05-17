package com.pd.springboot.dao;

import org.apache.ibatis.annotations.Mapper;

import com.pd.businessobject.PersonFO;
import com.pd.businessobject.PersonVO;
import com.pd.it.common.itf.IBaseDao;
import com.pd.it.common.itf.IQueryComboOperation;

@Mapper
public interface IPersonDao extends IBaseDao<PersonFO, PersonVO>, IQueryComboOperation {

}