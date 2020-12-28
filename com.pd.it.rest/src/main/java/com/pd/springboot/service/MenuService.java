package com.pd.springboot.service;

import javax.inject.Named;

import com.pd.businessobject.SysMenuFO;
import com.pd.businessobject.SysMenuVO;
import com.pd.it.common.itf.BaseService;
import com.pd.springboot.dao.ISysMenuDao;

@Named
public class MenuService extends BaseService<SysMenuFO, SysMenuVO, ISysMenuDao> {

}
