package com.pd.springboot.service;

import javax.inject.Named;

import com.pd.businessobject.SysInfoFO;
import com.pd.businessobject.SysInfoVO;
import com.pd.it.common.itf.BaseService;
import com.pd.springboot.dao.ISysInfoDao;

@Named
public class InfoService extends BaseService<SysInfoFO, SysInfoVO, ISysInfoDao> {

}
