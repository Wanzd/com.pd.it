package com.pd.it.common.util;

import java.util.List;

import com.pd.it.common.businessobject.PageVO;
import com.pd.it.common.exception.BusinessException;
import com.pd.it.common.itf.BaseService;
import com.pd.it.common.itf.IBaseDao;

public class BaseServiceDbAdapter<FO, VO, Dao extends IBaseDao<FO, VO>> implements IDbAdapter<FO, VO> {
    private BaseService<FO, VO, Dao> baseService;

    public BaseServiceDbAdapter(BaseService<FO, VO, Dao> baseService) {
        this.baseService = baseService;
    }

    @Override
    public VO queryInfo(FO fo) throws BusinessException {
        return baseService.queryInfo(fo);
    }

    @Override
    public List<VO> queryList(FO fo) throws BusinessException {
        return baseService.queryList(fo);
    }

    @Override
    public List<VO> queryPagedList(FO fo, PageVO page) throws BusinessException {
        return baseService.queryPagedList(fo, page);
    }

}
