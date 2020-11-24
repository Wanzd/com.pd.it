package com.pd.common.util;

import static com.pd.common.util.StaticTool.objToJsonStr;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pd.base.exception.BusinessException;
import com.pd.businessobject.PageVO;
import com.pd.common.enums.OperationEnum;
import com.pd.standard.itf.IQueryInfoOperation;
import com.pd.standard.itf.IQueryListOperation;
import com.pd.standard.itf.IQueryPagedListOperation;

/**
 * 控制反转工具
 * 
 * @author thinkpad
 *
 */
public class OperationUtil {
    /**
     * 查询列表操作
     * 
     * @param target
     *            Object
     * @param fo
     *            Object
     * @return Object
     * @throws BusinessException
     */
    public static Object operate(OperationEnum op, Object target, Object... fo) throws BusinessException {
        Object field = ReflectUtil.firstExistField(target, "dao,service,business");
        Method method = ReflectUtil.getClassMethod(OperationUtil.class, op.getCode());
        if (method == null) {
            return null;
        }
        try {
            return method.invoke(null, field, fo);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Object queryList(Object target, Object fo) throws BusinessException {
        if (target instanceof BaseMapper) {
            BaseMapper op = (BaseMapper) target;
            return op.selectList(null);
        } else if (target instanceof IQueryListOperation) {
            IQueryListOperation op = (IQueryListOperation) target;
            return op.queryList(fo);
        }
        return null;
    }

    private static Object queryInfo(Object target, Object fo) throws BusinessException {
        if (target instanceof BaseMapper) {
            BaseMapper op = (BaseMapper) target;
            return op.selectOne(null);
        } else if (target instanceof IQueryInfoOperation) {
            IQueryInfoOperation op = (IQueryInfoOperation) target;
            return op.queryInfo(fo);
        }
        return null;
    }

    private static Object queryJson(Object target, Object fo) throws BusinessException {
        if (target instanceof BaseMapper) {
            BaseMapper op = (BaseMapper) target;
            return objToJsonStr(op.selectOne(null));
        } else if (target instanceof IQueryInfoOperation) {
            IQueryInfoOperation op = (IQueryInfoOperation) target;
            return op.queryJson(fo);
        }
        return null;
    }

    private static Object queryPagedList(Object target, Object... fo) throws BusinessException {
        if (target instanceof BaseMapper) {
            BaseMapper op = (BaseMapper) target;
            return objToJsonStr(op.selectOne(null));
        } else if (target instanceof IQueryPagedListOperation) {
            IQueryPagedListOperation op = (IQueryPagedListOperation) target;
            return op.queryPagedList(fo[0], (PageVO) fo[1]);
        }
        return null;
    }
}
