package com.pd.springboot.dao;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pd.businessobject.LookupTypeFO;
import com.pd.businessobject.LookupTypeVO;
import com.pd.standard.web.IStandardDao;

@Mapper
public interface ILookupTypeDao extends BaseMapper<LookupTypeVO>, IStandardDao<LookupTypeFO, LookupTypeVO> {
}