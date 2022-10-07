package com.pd.springboot.dao;

import javax.inject.Named;

import org.apache.ibatis.annotations.Mapper;

import com.pd.businessobject.SysMenuFO;
import com.pd.businessobject.SysMenuVO;
import com.pd.it.common.itf.IBaseDao;

@Named("sysMenuDao")
@Mapper
public interface ISysMenuDao extends IBaseDao<SysMenuFO, SysMenuVO> {
}