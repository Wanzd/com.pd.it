package com.pd.springboot.service;

import javax.inject.Named;

import com.pd.businessobject.SysDataSourceBO;
import com.pd.businessobject.SysDataSourceFO;
import com.pd.it.common.itf.BaseService;
import com.pd.springboot.dao.ISysDataSourceDao;

@Named
public class SysDataSourceService extends BaseService<SysDataSourceFO, SysDataSourceBO, ISysDataSourceDao> {}
