package com.pd.standard.web;

import java.util.List;

import javax.inject.Inject;
import javax.websocket.server.PathParam;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pd.common.util.DeleteBridge;
import com.pd.common.util.ExcelBridge;
import com.pd.common.util.ReflectUtil;
import com.pd.it.common.businessobject.PageVO;
import com.pd.it.common.exception.BusinessException;
import com.pd.it.common.itf.IExportOperation;
import com.pd.it.common.util.DbTool;

public abstract class BaseRest<FO, VO, Bridge> {
    @Inject
    protected Bridge bridge;

    @RequestMapping("/queryInfo")
    public Object queryInfoRest(@RequestBody(required = false) FO fo) throws BusinessException {
        return DbTool.queryInfo(bridge, fo);
    }

    @RequestMapping(value = "/queryList", method = { RequestMethod.POST, RequestMethod.GET })
    @ResponseBody
    public List queryListRest(@RequestBody(required = false) FO fo) throws BusinessException {
        return DbTool.queryList(bridge, fo);
    }

    @RequestMapping(value = "/queryPagedList/{pageSize}/{curPage}", method = { RequestMethod.POST })
    @ResponseBody
    public Object queryPagedList(@RequestParam(required = false) FO fo, @PathParam(value = "") PageVO page)
            throws BusinessException {
        return DbTool.queryPagedList(bridge, fo, page);
    }

    @RequestMapping("/updateInfo")
    public int updateInfo(@RequestBody(required = false) VO vo) throws BusinessException {
        // return UpdateBridge.updateInfo(getDefaultField(), vo);
        return 0;
    }

    @RequestMapping("/updateList")
    public int updateList(@RequestBody(required = false) List<VO> list) throws BusinessException {
        // return UpdateBridge.updateList(getDefaultField(), list);
        return 0;
    }

    @RequestMapping("/deleteList")
    public int deleteList(@RequestBody(required = false) List<VO> list) throws BusinessException {
        return DeleteBridge.deleteList(bridge, list);
    }

    @RequestMapping("/deleteInfo")
    public int deleteInfo(@RequestBody(required = false) VO vo) throws BusinessException {
        return DeleteBridge.deleteInfo(bridge, vo);
    }

    @RequestMapping(value = "/export", method = { RequestMethod.POST, RequestMethod.GET })
    public void export(@RequestBody(required = false) FO fo) throws BusinessException {
        ExcelBridge.export(bridge, fo);
        Object field = ReflectUtil.firstExistField(this, "service");
        if (field instanceof IExportOperation) {
            IExportOperation op = (IExportOperation) field;
            op.export(fo);
        }
        return;
    }
}
