package com.pd.springboot.service;

import javax.inject.Named;

import com.pd.businessobject.AppJobBO;
import com.pd.it.common.itf.MybatisPlusService;
import com.pd.springboot.dao.IAppJobPlusDao;

@Named
public class JobService extends MybatisPlusService<AppJobBO, AppJobBO, IAppJobPlusDao> {}
