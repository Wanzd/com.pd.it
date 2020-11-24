package com.pd.standard.web;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pd.base.exception.BusinessException;
import com.pd.businessobject.PageVO;
import com.pd.common.util.DeleteBridge;
import com.pd.common.util.ExcelBridge;
import com.pd.common.util.QueryBridge;
import com.pd.common.util.ReflectUtil;
import com.pd.common.util.UpdateBridge;
import com.pd.standard.itf.IExportOperation;

public interface IStandardRest<FO, VO> {
    default Object getDefaultField() throws BusinessException {
        return ReflectUtil.firstExistField(this, "business,service,dao");
    }

    @RequestMapping("/queryInfo")
    default Object queryInfo(@RequestBody(required = false) @Validated FO fo, BindingResult bindingResult)
            throws BusinessException {
        return QueryBridge.queryInfo(getDefaultField(), fo);
    }

    @RequestMapping("/queryDetailInfo")
    default Object queryDetailInfo(@RequestBody(required = false) FO fo) throws BusinessException {
        return QueryBridge.queryDetailInfo(getDefaultField(), fo);
    }

    @RequestMapping(value = "/queryList", method = { RequestMethod.POST, RequestMethod.GET })
    @ResponseBody
    default List queryList(@RequestBody(required = false) FO fo) throws BusinessException {
        return QueryBridge.queryList(getDefaultField(), fo);
    }

    @RequestMapping(value = "/queryPagedList/{pageSize}/{curPage}", method = { RequestMethod.POST })
    @ResponseBody
    default Object queryPagedList(@RequestParam(required = false) FO fo, @PathParam(value = "") PageVO page)
            throws BusinessException {
        return QueryBridge.queryPagedList(getDefaultField(), fo, page);
    }

    @RequestMapping("/updateInfo")
    default int updateInfo(@RequestBody(required = false) VO vo) throws BusinessException {
        return UpdateBridge.updateInfo(getDefaultField(), vo);
    }

    @RequestMapping("/updateList")
    default int updateList(@RequestBody(required = false) List<VO> list) throws BusinessException {
        return UpdateBridge.updateList(getDefaultField(), list);
    }

    @RequestMapping("/deleteList")
    default int deleteList(@RequestBody(required = false) List<VO> list) throws BusinessException {
        return DeleteBridge.deleteList(getDefaultField(), list);
    }

    @RequestMapping("/deleteInfo")
    default int deleteInfo(@RequestBody(required = false) VO vo) throws BusinessException {
        return DeleteBridge.deleteInfo(getDefaultField(), vo);
    }

    @RequestMapping(value = "/export", method = { RequestMethod.POST, RequestMethod.GET })
    default void export(@RequestBody(required = false) FO fo) throws BusinessException {
        ExcelBridge.export(getDefaultField(), fo);
        Object field = ReflectUtil.firstExistField(this, "service");
        if (field instanceof IExportOperation) {
            IExportOperation op = (IExportOperation) field;
            op.export(fo);
        }
        return;
    }
}
