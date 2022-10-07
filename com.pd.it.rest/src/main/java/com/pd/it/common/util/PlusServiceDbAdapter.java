package com.pd.it.common.util;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pd.it.common.businessobject.PageVO;
import com.pd.it.common.exception.BusinessException;
import com.pd.it.common.itf.BaseService;
import com.pd.it.common.itf.IBaseDao;
import com.pd.it.common.itf.PlusService;

import java.util.List;

public class PlusServiceDbAdapter<FO, VO, Dao extends BaseMapper<VO>> implements IDbAdapter<FO, VO> {
    private PlusService<FO, VO, Dao> plusService;

    public PlusServiceDbAdapter(PlusService<FO, VO, Dao> plusService) {
        this.plusService = plusService;
    }

    @Override
    public VO queryInfo(FO fo) throws BusinessException {
        return plusService.queryInfo(fo);
    }

    @Override
    public List<VO> queryList(FO fo) throws BusinessException {
        return plusService.queryList(fo);
    }

    @Override
    public List<VO> queryPagedList(FO fo, PageVO page) throws BusinessException {
        return plusService.queryPagedList(fo, page);
    }

}
