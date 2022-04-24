package com.pd.standard.web;

import static com.pd.it.common.util.StaticTool.toObj;

import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.pd.it.common.businessobject.ComboVO;
import com.pd.it.common.businessobject.MapVO;
import com.pd.it.common.itf.IQueryComboOperation;
import com.pd.it.common.util.Reflects;
import com.pd.standard.constants.RestPathConst;

public interface IQueryComboRest<FO, DTO> {

    @RequestMapping(value = RestPathConst.QUERY_COMBO)
    @ResponseBody
    default List<ComboVO> queryCombo(@RequestBody(required = false) JSONObject fo) {
        IQueryComboOperation op = Reflects.firstExistField(this, IQueryComboOperation.class, "dao,service,business");
        return op.queryCombo(toObj(fo, MapVO.class));
    }

}
