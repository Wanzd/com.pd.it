package com.pd.springboot.service;

import javax.inject.Named;

import com.pd.businessobject.SysInfoFO;
import com.pd.businessobject.SysInfoVO;
import com.pd.springboot.dao.ISysInfoDao;

@Named
public class InfoService extends ServiceAdapter<SysInfoFO, SysInfoVO, ISysInfoDao> {

}
