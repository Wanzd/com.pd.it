package com.pd.springboot.dao;

import org.apache.ibatis.annotations.Mapper;

import com.pd.businessobject.UserFO;
import com.pd.businessobject.UserVO;
import com.pd.it.common.itf.IBaseDao;
import com.pd.it.common.itf.IQueryComboOperation;

@Mapper
public interface IUserDao extends IBaseDao<UserFO, UserVO>, IQueryComboOperation {

}