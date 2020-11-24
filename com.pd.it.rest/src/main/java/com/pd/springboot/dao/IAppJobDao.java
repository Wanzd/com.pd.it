package com.pd.springboot.dao;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pd.businessobject.MapVO;
import com.pd.standard.itf.IUpdateDao;

@Mapper
public interface IAppJobDao extends BaseMapper<MapVO>, IUpdateDao<MapVO, MapVO> {
}