package com.pd.springboot.service;

import javax.inject.Named;

import com.pd.businessobject.SysChartBO;
import com.pd.businessobject.SysChartFO;
import com.pd.it.common.itf.BaseService;
import com.pd.springboot.dao.ISysChartDao;

@Named
public class SysChartService extends BaseService<SysChartFO, SysChartBO, ISysChartDao> {}