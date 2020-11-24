package com.pd.springboot.service;

import javax.inject.Named;

import com.pd.businessobject.MapVO;
import com.pd.springboot.dao.IBaseNewsDao;

@Named
public class SariBaseService extends ServiceAdapter<MapVO, MapVO, IBaseNewsDao> {

}
