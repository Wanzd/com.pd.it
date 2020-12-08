package com.pd.it.common.util;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pd.it.common.businessobject.ComboVO;
import com.pd.it.common.businessobject.PageVO;
import com.pd.it.common.exception.BusinessException;
import com.pd.it.common.itf.IExportOperation;

public class ServiceAdapter<FO, VO, Dao> extends ServiceImpl<BaseMapper<VO>, VO> implements IExportOperation<FO> {
    @Autowired
    protected Dao dao;

    public VO queryInfo(FO fo) throws BusinessException {
        return StaticTool.queryInfo(dao, fo);
    }

    public String queryJson(FO fo) throws BusinessException {
        return QueryBridge.queryJson(dao, fo);
    }

    public VO queryDetailInfo(FO fo) throws BusinessException {
        return QueryBridge.queryDetailInfo(dao, fo);
    }

    public List<VO> queryList(FO fo) throws BusinessException {
        return StaticTool.queryList(dao, fo);
    }

    public List<VO> queryPagedList(FO fo, PageVO page) throws BusinessException {
        return StaticTool.queryPagedList(dao, fo, page);
    }

    public int queryCount(FO fo) throws BusinessException {
        return QueryBridge.queryCount(dao, fo);
    }

    public int insertInfo(VO vo) {
        return CreateBridge.insertInfo(dao, vo);
    }

    public int insertList(List<VO> list) throws BusinessException {
        return CreateBridge.insertList(dao, list);
    }

    public int delete(VO vo) throws BusinessException {
        return DeleteBridge.delete(dao, vo);
    }

    public int deleteInfo(VO vo) throws BusinessException {
        return DeleteBridge.deleteInfo(dao, vo);
    }

    public int update(VO vo) throws BusinessException {
        return UpdateBridge.updateInfo(dao, vo);
    }

    public int updateList(List<VO> list) throws BusinessException {
        return UpdateBridge.updateList(dao, list);
    }

    public int deleteList(List<VO> list) {
        return DeleteBridge.deleteList(dao, list);
    }

    @Override
    public void export(FO fo) throws BusinessException {
        ExportUtil.export(queryList(fo), getExportConfig());
    }

    public List<ComboVO> queryCombo(FO fo) throws BusinessException {
        return QueryBridge.queryCombo(dao, fo);
    }

}
