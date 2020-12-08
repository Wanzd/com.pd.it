package com.pd.springboot.dao;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pd.businessobject.SysMenuFO;
import com.pd.businessobject.SysMenuVO;
import com.pd.it.common.itf.IQueryListOperation;
import com.pd.it.common.itf.IQueryPagedListOperation;

@Mapper
public interface ISysMenuDao extends BaseMapper<SysMenuVO>, IQueryListOperation<SysMenuFO, SysMenuVO>,
        IQueryPagedListOperation<SysMenuFO, SysMenuVO> {

}