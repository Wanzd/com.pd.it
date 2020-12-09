package com.pd.it.common.util;

import static com.pd.it.common.util.StaticTool.emptyList;
import static com.pd.it.common.util.StaticTool.toObj;

import java.util.HashMap;
import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pd.it.common.businessobject.ComboVO;
import com.pd.it.common.businessobject.MapVO;
import com.pd.it.common.businessobject.PageVO;
import com.pd.it.common.exception.BusinessException;
import com.pd.it.common.itf.IQueryComboOperation;
import com.pd.it.common.itf.IQueryInfoOperation;
import com.pd.it.common.itf.IQueryListOperation;
import com.pd.it.common.itf.IQueryPagedListOperation;

public class QueryBridge {

    public static <FO, VO> VO queryInfo(Object field, FO fo) throws BusinessException {
        if (field instanceof ServiceAdapter) {
            ServiceAdapter op = (ServiceAdapter) field;
            return (VO) op.queryInfo(fo);
        }
        if (field instanceof IQueryInfoOperation) {
            IQueryInfoOperation op = (IQueryInfoOperation) field;
            return (VO) op.queryInfo(fo);
        }
        if (field instanceof BaseMapper) {
            BaseMapper op = (BaseMapper) field;
            return (VO) op.selectById(toObj(fo, HashMap.class));
        }
        return null;
    }

    public static <FO, VO> String queryJson(Object field, FO fo) throws BusinessException {
        if (field instanceof ServiceAdapter) {
            ServiceAdapter op = (ServiceAdapter) field;
            return op.queryJson(fo);
        }
        if (field instanceof IQueryInfoOperation) {
            IQueryInfoOperation op = (IQueryInfoOperation) field;
            String jsonStr = StringFactory.from(fo);
            return op.queryJson(fo);
        }
        return null;
    }

    public static <FO, VO> VO queryDetailInfo(Object field, FO fo) throws BusinessException {
        if (field instanceof ServiceAdapter) {
            ServiceAdapter op = (ServiceAdapter) field;
            return (VO) op.queryDetailInfo(fo);
        }
        if (field instanceof IQueryInfoOperation) {
            IQueryInfoOperation<FO, VO> op = (IQueryInfoOperation) field;
            return op.queryDetailInfo(fo);
        }
        return null;
    }

    public static <FO, VO> List<VO> queryList(Object field, FO fo) throws BusinessException {
        if (field instanceof ServiceAdapter) {
            ServiceAdapter op = (ServiceAdapter) field;
            return op.queryList(fo);
        }
        if (field instanceof IQueryListOperation) {
            IQueryListOperation op = (IQueryListOperation) field;
            return op.queryList(fo);
        }
        if (field instanceof BaseMapper) {
            BaseMapper op = (BaseMapper) field;
            return op.selectList(null);
        }
        return null;
    }

    public static <FO, VO> List<VO> queryPagedList(Object field, FO fo, PageVO page) throws BusinessException {
        if (field instanceof ServiceAdapter) {
            ServiceAdapter op = (ServiceAdapter) field;
            return op.queryPagedList(fo, page);
        }
        if (field instanceof IQueryPagedListOperation) {
            IQueryPagedListOperation op = (IQueryPagedListOperation) field;
            return op.queryPagedList(fo, page);
        }
        if (field instanceof BaseMapper) {
            BaseMapper op = (BaseMapper) field;
            return op.selectList(null);
        }
        return null;
    }

    public static <FO> int queryCount(Object field, FO fo) throws BusinessException {
        if (field instanceof ServiceAdapter) {
            ServiceAdapter op = (ServiceAdapter) field;
            return op.queryCount(fo);
        }
        if (field instanceof IQueryPagedListOperation) {
            IQueryPagedListOperation op = (IQueryPagedListOperation) field;
            return op.queryCount(fo);
        }
        if (field instanceof BaseMapper) {
            BaseMapper op = (BaseMapper) field;
            return op.selectCount(null);
        }
        return 0;
    }

    public static <FO> List<ComboVO> queryCombo(Object field, FO fo) throws BusinessException {
        if (field instanceof ServiceAdapter) {
            ServiceAdapter op = (ServiceAdapter) field;
            return op.queryCombo(fo);
        }
        if (field instanceof IQueryComboOperation) {
            IQueryComboOperation op = (IQueryComboOperation) field;
            return op.queryCombo(toObj(fo, MapVO.class));
        }
        return emptyList();
    }

}
