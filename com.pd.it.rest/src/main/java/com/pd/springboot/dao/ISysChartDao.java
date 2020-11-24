package com.pd.springboot.dao;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pd.businessobject.MapVO;
import com.pd.businessobject.SysChartBO;
import com.pd.businessobject.SysChartFO;
import com.pd.standard.itf.IQueryComboOperation;
import com.pd.standard.itf.IQueryDao;
import com.pd.standard.itf.IUpdateDao;

@Mapper
public interface ISysChartDao extends BaseMapper<SysChartBO>, IQueryDao<SysChartFO, SysChartBO>, IQueryComboOperation,
        IUpdateDao<MapVO, MapVO> {
}