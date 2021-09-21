package com.pd.it.common.util;

import java.util.List;

import com.pd.it.common.businessobject.PageVO;
import com.pd.it.common.exception.BusinessException;

public interface IDbAdapter<FO, VO> {

    VO queryInfo(FO fo) throws BusinessException;

    List<VO> queryList(FO fo) throws BusinessException;

    List<VO> queryPagedList(FO fo, PageVO page) throws BusinessException;
}
