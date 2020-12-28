package com.pd.springboot.service;

import javax.inject.Named;

import com.pd.businessobject.LookupItemFO;
import com.pd.businessobject.LookupItemVO;
import com.pd.it.common.itf.BaseService;
import com.pd.springboot.dao.ILookupItemDao;

@Named
public class LookupItemService extends BaseService<LookupItemFO, LookupItemVO, ILookupItemDao> {}
