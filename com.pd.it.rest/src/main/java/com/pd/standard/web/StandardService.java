package com.pd.standard.web;

import java.util.List;

import javax.inject.Inject;

import com.pd.it.common.exception.BusinessException;
import com.pd.it.common.itf.IBaseDao;

public abstract class StandardService<FO, VO, Dao extends IBaseDao<FO, VO>> {

    @Inject
    protected Dao dao;

    public VO queryInfo(FO fo) throws BusinessException {
        return dao.queryInfo(fo);
    }

    public List<VO> queryList(FO fo) throws BusinessException {
        return dao.queryList(fo);
    }

    public int insertInfo(VO vo) throws BusinessException {
        return dao.insertInfo(vo);
    }
}
