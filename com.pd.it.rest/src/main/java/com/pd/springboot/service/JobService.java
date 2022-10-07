package com.pd.springboot.service;

import javax.inject.Named;

import com.pd.businessobject.AppJobBO;
import com.pd.it.common.itf.PlusService;
import com.pd.springboot.dao.IAppJobPlusDao;

@Named
public class JobService extends PlusService<AppJobBO, AppJobBO, IAppJobPlusDao> {}
