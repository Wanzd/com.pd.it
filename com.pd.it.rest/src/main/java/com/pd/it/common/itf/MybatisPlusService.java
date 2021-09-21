package com.pd.it.common.itf;

import java.util.List;

import javax.inject.Inject;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pd.it.common.businessobject.PageVO;
import com.pd.it.common.exception.BusinessException;

import lombok.Getter;

public class MybatisPlusService<FO, VO, Dao extends BaseMapper<VO>> {

    @Inject
    @Getter
    protected Dao dao;

    public VO queryInfo(FO in) throws BusinessException {
        QueryWrapper<VO> wrapper = new QueryWrapper(in);
        return dao.selectOne(wrapper);
    }

    public List<VO> queryList(FO in) throws BusinessException {
        QueryWrapper<VO> wrapper = new QueryWrapper(in);
        return dao.selectList(wrapper);
    }

    public List<VO> queryPagedList(FO in, PageVO pageVO) throws BusinessException {
        QueryWrapper<VO> wrapper = new QueryWrapper(in);
        Page<VO> page = new Page<VO>(pageVO.getCurPage(), pageVO.getPageSize());
        return dao.selectPage(page, wrapper).getRecords();
    }
}
