package com.pd.springboot.service;

import javax.inject.Named;

import com.pd.businessobject.LookupItemFO;
import com.pd.businessobject.LookupItemVO;
import com.pd.springboot.dao.ILookupItemDao;

@Named
public class LookupItemService extends ServiceAdapter<LookupItemFO, LookupItemVO, ILookupItemDao> {
}
