package com.pd.common.util;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pd.base.exception.BusinessException;
import com.pd.springboot.service.ServiceAdapter;
import com.pd.standard.itf.IDeleteListOperation;
import com.pd.standard.itf.IDeleteOperation;
import com.pd.standard.itf.IIdentity;
import com.pd.standard.itf.IUpdateListOperation;

public class DeleteBridge {

    public static <VO> int delete(Object field, VO vo) throws BusinessException {
        if (field instanceof ServiceAdapter) {
            ServiceAdapter op = (ServiceAdapter) field;
            return op.delete(vo);
        }
        if (field instanceof IDeleteOperation) {
            IDeleteOperation op = (IDeleteOperation) field;
            return op.delete(vo);
        }
        return -1;
    }

    public static <VO> int deleteInfo(Object field, VO vo) throws BusinessException {
        if (field instanceof ServiceAdapter) {
            ServiceAdapter op = (ServiceAdapter) field;
            return op.deleteInfo(vo);
        }
        if (field instanceof IDeleteOperation) {
            IDeleteOperation op = (IDeleteOperation) field;
            return op.deleteById(vo);
        }
        if (field instanceof BaseMapper) {
            BaseMapper op = (BaseMapper) field;
            return op.deleteById((Serializable) vo);
        }
        return 0;
    }

    public static <VO> int deleteList(Object field, List<VO> list) {
        if (field instanceof BaseMapper) {
            BaseMapper<VO> op = (BaseMapper<VO>) field;
            List<Serializable> idList = list.stream().map(vo -> {
                if (vo instanceof IIdentity) {
                    IIdentity<Serializable> identity = (IIdentity) vo;
                    return identity.getId();
                }
                return null;
            }).collect(Collectors.toList());
            return op.deleteBatchIds(idList);
        }
        if (field instanceof ServiceAdapter) {
            ServiceAdapter op = (ServiceAdapter) field;
            return op.deleteList(list);
        }
        if (field instanceof IUpdateListOperation) {
            IDeleteListOperation op = (IDeleteListOperation) field;
            return op.deleteList(list);
        }
        return -1;
    }

}
