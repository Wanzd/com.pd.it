package com.pd.standard.web;

import static com.pd.common.util.StaticTool.toObj;

import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.pd.businessobject.ComboVO;
import com.pd.businessobject.MapVO;
import com.pd.common.util.Reflects;
import com.pd.standard.itf.IQueryComboOperation;
import com.pd.standard.itf.RestPathConst;

public interface IQueryComboRest<FO, DTO> {

    @RequestMapping(value = RestPathConst.QUERY_COMBO)
    @ResponseBody
    default List<ComboVO> queryCombo(@RequestBody(required = false) JSONObject fo) {
        IQueryComboOperation op = Reflects.firstExistField(this, IQueryComboOperation.class, "dao,service,business");
        return op.queryCombo(toObj(fo, MapVO.class));
    }

}
