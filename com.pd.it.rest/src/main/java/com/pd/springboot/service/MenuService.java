package com.pd.springboot.service;

import javax.inject.Named;

import com.pd.businessobject.SysMenuFO;
import com.pd.businessobject.SysMenuVO;
import com.pd.springboot.dao.ISysMenuDao;

@Named
public class MenuService extends ServiceAdapter<SysMenuFO, SysMenuVO, ISysMenuDao> {

}
