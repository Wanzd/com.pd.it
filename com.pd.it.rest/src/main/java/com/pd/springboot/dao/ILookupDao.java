package com.pd.springboot.dao;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pd.businessobject.LookupVO;
import com.pd.standard.itf.IQueryComboOperation;

@Mapper
public interface ILookupDao extends BaseMapper<LookupVO>, IQueryComboOperation {
}