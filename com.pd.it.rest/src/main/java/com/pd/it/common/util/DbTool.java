package com.pd.it.common.util;

import static com.pd.it.common.util.StaticTool.toObj;

import java.util.HashMap;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pd.it.common.exception.BusinessException;
import com.pd.it.common.itf.IQueryInfoOperation;

public class DbTool {
    public static <FO, VO> VO queryInfo(Object field, FO fo) throws BusinessException {
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

}
