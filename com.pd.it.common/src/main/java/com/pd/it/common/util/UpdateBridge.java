package com.pd.it.common.util;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pd.it.common.exception.BusinessException;
import com.pd.it.common.itf.IUpdateInfoOperation;
import com.pd.it.common.itf.IUpdateListOperation;

public class UpdateBridge {

    public static <VO> int updateInfo(Object field, VO vo) throws BusinessException {
        if (field instanceof ServiceAdapter) {
            ServiceAdapter op = (ServiceAdapter) field;
            return op.update(vo);
        }
        if (field instanceof BaseMapper) {
            BaseMapper op = (BaseMapper) field;
            return op.updateById(vo);
        }
        if (field instanceof IUpdateInfoOperation) {
            IUpdateInfoOperation op = (IUpdateInfoOperation) field;
            return op.updateInfo(vo);
        }
        return 0;
    }

    public static <VO> int updateList(Object field, List<VO> list) throws BusinessException {
        if (field instanceof ServiceImpl) {
            ServiceImpl<BaseMapper<VO>, VO> op = (ServiceImpl) field;
            op.saveOrUpdateBatch(list);
            return list.size();
        }
        if (field instanceof IUpdateListOperation) {
            IUpdateListOperation op = (IUpdateListOperation) field;
            return op.updateList(list);
        }
        return -1;
    }

}
