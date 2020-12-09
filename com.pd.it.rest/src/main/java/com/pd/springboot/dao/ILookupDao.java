package com.pd.springboot.dao;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pd.businessobject.LookupFO;
import com.pd.businessobject.LookupVO;
import com.pd.it.common.itf.IQueryComboOperation;
import com.pd.it.common.itf.IQueryInfoOperation;

@Mapper
public interface ILookupDao
        extends BaseMapper<LookupVO>, IQueryComboOperation, IQueryInfoOperation<LookupFO, LookupVO> {
}