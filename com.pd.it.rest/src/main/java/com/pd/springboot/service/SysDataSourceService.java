package com.pd.springboot.service;

import javax.inject.Named;

import com.pd.businessobject.SysDataSourceBO;
import com.pd.businessobject.SysDataSourceFO;
import com.pd.springboot.dao.ISysDataSourceDao;

@Named
public class SysDataSourceService extends ServiceAdapter<SysDataSourceFO, SysDataSourceBO, ISysDataSourceDao> {
}
