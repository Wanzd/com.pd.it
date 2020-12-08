package com.pd.springboot.dao;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pd.businessobject.SysChartBO;
import com.pd.businessobject.SysChartFO;
import com.pd.it.common.businessobject.MapVO;
import com.pd.it.common.itf.IQueryComboOperation;
import com.pd.it.common.itf.IQueryDao;
import com.pd.it.common.itf.IUpdateDao;

@Mapper
public interface ISysChartDao extends BaseMapper<SysChartBO>, IQueryDao<SysChartFO, SysChartBO>, IQueryComboOperation,
        IUpdateDao<MapVO, MapVO> {
}