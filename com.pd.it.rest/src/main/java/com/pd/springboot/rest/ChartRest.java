package com.pd.springboot.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pd.businessobject.SysChartFO;
import com.pd.businessobject.SysChartVO;
import com.pd.springboot.dao.ISysChartDao;
import com.pd.standard.web.BaseRest;

/**
 * AI系统
 * 
 * @author thinkpad
 *
 */
@RestController
@RequestMapping("chartRest")
public class ChartRest extends BaseRest<SysChartFO, SysChartVO, ISysChartDao> {

}
