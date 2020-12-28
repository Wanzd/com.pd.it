package com.pd.standard.web;

import javax.websocket.server.PathParam;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pd.common.util.DeleteBridge;
import com.pd.common.util.ReflectUtil;
import com.pd.it.common.businessobject.PageVO;
import com.pd.it.common.exception.BusinessException;

public interface IDimensionStandardRest {
    default Object getDefaultField(String dimension) throws BusinessException {
        return ReflectUtil.firstExistField(this, dimension);
    }

    @RequestMapping("{dimension}/queryCombo")
    default Object queryCombo(@PathVariable("dimension") String dimension,
            @RequestBody(required = false) @Validated String fo) throws BusinessException {
        // return DbTool.queryCombo(getDefaultField(dimension), fo);
        return null;
    }

    @RequestMapping(value = "{dimension}/queryPagedList/{pageSize}/{curPage}", method = { RequestMethod.POST })
    @ResponseBody
    default <FO> Object queryPagedList(@PathVariable("dimension") String dimension,
            @RequestParam(required = false) FO fo, @PathParam(value = "") PageVO page) throws BusinessException {
        // return QueryBridge.queryPagedList(getDefaultField(dimension), fo, page);
        return null;
    }

    @RequestMapping("{dimension}/insertInfo")
    default <VO> int insertInfo(@PathVariable("dimension") String dimension, @RequestBody(required = false) VO vo)
            throws BusinessException {
        // return CreateBridge.insertInfo(getDefaultField(dimension), vo);
        return 1;
    }

    @RequestMapping("{dimension}/deleteInfo")
    default <VO> int deleteInfo(@PathVariable("dimension") String dimension, @RequestBody(required = false) VO vo)
            throws BusinessException {
        return DeleteBridge.deleteInfo(getDefaultField(dimension), vo);
    }
}
