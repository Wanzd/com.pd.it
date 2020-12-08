package com.pd.springboot.dao;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pd.businessobject.SysDataSourceBO;
import com.pd.businessobject.SysDataSourceFO;
import com.pd.it.common.itf.IQueryComboDao;
import com.pd.it.common.itf.IQueryDao;

@Mapper
public interface ISysDataSourceDao
        extends BaseMapper<SysDataSourceBO>, IQueryComboDao, IQueryDao<SysDataSourceFO, SysDataSourceBO> {
}