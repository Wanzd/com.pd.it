package com.pd.it.common.itf;

import java.util.List;

import javax.inject.Inject;

import com.pd.it.common.businessobject.PageVO;
import com.pd.it.common.exception.BusinessException;

import lombok.Getter;

public class BaseService<FO, VO, Dao extends IBaseDao<FO, VO>> implements IBaseDao<FO, VO> {

    @Inject
    @Getter
    protected Dao dao;

    @Override
    public VO queryInfo(FO in) throws BusinessException {
        return dao.queryInfo(in);
    }

    @Override
    public VO queryInfoById(String id) throws BusinessException {
        return dao.queryInfoById(id);
    }

    @Override
    public VO queryDetailInfo(FO in) throws BusinessException {
        return dao.queryDetailInfo(in);
    }

    @Override
    public String queryJson(FO in) throws BusinessException {
        return dao.queryJson(in);
    }

    @Override
    public List<VO> queryList(FO in) throws BusinessException {
        return dao.queryList(in);
    }

    @Override
    public List<VO> queryPagedList(FO in, PageVO page) throws BusinessException {
        return dao.queryPagedList(in, page);
    }

    @Override
    public int queryCount(FO fo) throws BusinessException {
        return dao.queryCount(fo);
    }

    @Override
    public int insertInfo(VO vo) throws BusinessException {
        return dao.insertInfo(vo);
    }

    @Override
    public int insertList(List<VO> list) {
        return dao.insertList(list);
    }

    @Override
    public int updateList(List<VO> list) {
        return dao.updateList(list);
    }

    @Override
    public int deleteList(List<VO> list) {
        return dao.deleteList(list);
    }

    @Override
    public int deleteInfo(FO fo) {
        return dao.deleteInfo(fo);
    }

    @Override
    public int deleteById(String id) {
        return dao.deleteById(id);
    }

    @Override
    public int updateInfo(VO vo) throws BusinessException {
        return dao.updateInfo(vo);
    }

}
