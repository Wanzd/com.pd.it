package com.pd.springboot.service;

import javax.inject.Named;

import com.pd.businessobject.SysChartBO;
import com.pd.businessobject.SysChartFO;
import com.pd.springboot.dao.ISysChartDao;

@Named
public class SysChartService extends ServiceAdapter<SysChartFO, SysChartBO, ISysChartDao> {
}