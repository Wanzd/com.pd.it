package com.pd.springboot.service;

import javax.inject.Named;

import com.pd.businessobject.LookupTypeFO;
import com.pd.businessobject.LookupTypeVO;
import com.pd.it.common.itf.BaseService;
import com.pd.springboot.dao.ILookupTypeDao;

@Named
public class LookupTypeService extends BaseService<LookupTypeFO, LookupTypeVO, ILookupTypeDao> {}
