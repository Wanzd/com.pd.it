package com.pd.common.util;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pd.base.exception.BusinessException;
import com.pd.springboot.service.ServiceAdapter;
import com.pd.standard.itf.IInsertInfoOperation;
import com.pd.standard.itf.IInsertListOperation;

public class CreateBridge {

    public static <VO> int insertList(Object field, List<VO> list) throws BusinessException {
        if (field instanceof ServiceAdapter) {
            ServiceAdapter op = (ServiceAdapter) field;
            return op.insertList(list);
        }
        if (field instanceof IInsertListOperation) {
            IInsertListOperation op = (IInsertListOperation) field;
            return op.insertList(list);
        }
        return 0;
    }

    public static <VO> int insertInfo(Object field, VO vo) {
        if (field instanceof ServiceAdapter) {
            ServiceAdapter op = (ServiceAdapter) field;
            return op.insertInfo(vo);
        }
        if (field instanceof BaseMapper) {
            BaseMapper op = (BaseMapper) field;
            return op.insert(vo);
        }
        if (field instanceof IInsertInfoOperation) {
            IInsertInfoOperation op = (IInsertInfoOperation) field;
            return op.insertInfo(vo);
        }
        return 0;
    }

}
