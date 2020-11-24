package com.pd.springboot.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.pd.base.exception.BusinessException;
import com.pd.businessobject.SysChartFO;
import com.pd.businessobject.SysChartVO;
import com.pd.springboot.service.SysChartService;
import com.pd.standard.web.IStandardRest;

/**
 * AI系统
 * 
 * @author thinkpad
 *
 */
@RestController
@RequestMapping("chartRest")
public class ChartRest implements IStandardRest<SysChartFO, SysChartVO> {
    @Autowired
    private SysChartService service;

    @RequestMapping(value = "/refresh", produces = { "application/json;charset=UTF-8" })
    @ResponseBody
    public Object refresh() throws BusinessException {
        // List<SysChartBO> list = dao.queryList(null);
        // list.stream().forEach(vo -> vo.put("jsonData", JSON.toJSONString(vo)));
        // dao.updateList(list);
        return 0;
    }

}
