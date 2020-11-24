package com.pd.springboot.service;

import javax.inject.Named;

import com.pd.businessobject.LookupTypeFO;
import com.pd.businessobject.LookupTypeVO;
import com.pd.springboot.dao.ILookupTypeDao;

@Named
public class LookupTypeService extends ServiceAdapter<LookupTypeFO, LookupTypeVO, ILookupTypeDao> {
}
