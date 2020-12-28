package com.pd.springboot.service;

import javax.inject.Named;

import com.pd.it.common.businessobject.MapVO;
import com.pd.it.common.itf.BaseService;
import com.pd.springboot.dao.IBaseNewsDao;

@Named
public class SariBaseService extends BaseService<MapVO, MapVO, IBaseNewsDao> {

}
