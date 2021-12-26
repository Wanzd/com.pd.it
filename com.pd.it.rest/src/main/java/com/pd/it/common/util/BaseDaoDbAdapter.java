package com.pd.it.common.util;

import java.util.List;

import com.pd.it.common.businessobject.PageVO;
import com.pd.it.common.exception.BusinessException;
import com.pd.it.common.itf.IBaseDao;

public class BaseDaoDbAdapter<FO, VO> implements IDbAdapter<FO, VO> {
    private IBaseDao<FO, VO> dao;

    public BaseDaoDbAdapter(IBaseDao<FO, VO> dao) {
        this.dao = dao;
    }

    @Override
    public VO queryInfo(FO fo) throws BusinessException {
        return dao.queryInfo(fo);
    }

    @Override
    public List<VO> queryList(FO fo) throws BusinessException {
        return dao.queryList(fo);
    }

    @Override
    public List<VO> queryPagedList(FO fo, PageVO page) throws BusinessException {
        return dao.queryPagedList(fo, page);
    }

}
