package com.pd.springboot.dao;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pd.it.common.businessobject.MapVO;
import com.pd.it.common.itf.IUpdateDao;

@Mapper
public interface IAppJobDao extends BaseMapper<MapVO>, IUpdateDao<MapVO, MapVO> {
}