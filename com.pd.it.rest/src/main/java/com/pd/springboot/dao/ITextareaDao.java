package com.pd.springboot.dao;

import org.apache.ibatis.annotations.Mapper;

import com.pd.businessobject.TextareaFO;
import com.pd.businessobject.TextareaVO;
import com.pd.it.common.itf.IBaseDao;
import com.pd.it.common.itf.IQueryComboOperation;

@Mapper
public interface ITextareaDao extends IBaseDao<TextareaFO, TextareaVO>, IQueryComboOperation {}