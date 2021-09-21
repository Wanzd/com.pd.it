package com.pd.it.common.util;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pd.it.common.businessobject.PageVO;
import com.pd.it.common.exception.BusinessException;
import com.pd.it.common.itf.BaseService;
import com.pd.it.common.itf.MybatisPlusService;

public class MybatisPlusServiceDbAdapter<FO, VO, Dao extends BaseMapper<VO>> implements IDbAdapter<FO, VO> {
    private MybatisPlusService<FO, VO, BaseMapper<VO>> mybatisPlusService;

    public MybatisPlusServiceDbAdapter(MybatisPlusService<FO, VO, BaseMapper<VO>> mybatisPlusService) {
        this.mybatisPlusService = mybatisPlusService;
    }

    @Override
    public VO queryInfo(FO fo) throws BusinessException {
        return mybatisPlusService.queryInfo(fo);
    }

    @Override
    public List<VO> queryList(FO fo) throws BusinessException {
        return mybatisPlusService.queryList(fo);
    }

    @Override
    public List<VO> queryPagedList(FO fo, PageVO page) throws BusinessException {
        return mybatisPlusService.queryPagedList(fo, page);
    }

}
