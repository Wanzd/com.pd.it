package com.pd.springboot.dao;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pd.businessobject.SysDataSourceBO;
import com.pd.businessobject.SysDataSourceFO;
import com.pd.standard.itf.IQueryComboDao;
import com.pd.standard.itf.IQueryDao;

@Mapper
public interface ISysDataSourceDao
        extends BaseMapper<SysDataSourceBO>, IQueryComboDao, IQueryDao<SysDataSourceFO, SysDataSourceBO> {
}