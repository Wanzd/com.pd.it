package com.pd.springboot.postgredao;

import org.apache.ibatis.annotations.Mapper;

import com.pd.businessobject.TestVO;
import com.pd.it.common.businessobject.MapVO;
import com.pd.it.common.itf.IBaseDao;

@Mapper
public interface ITestPgDao extends IBaseDao<TestVO, TestVO> {
    MapVO queryTest(MapVO fo);
}
