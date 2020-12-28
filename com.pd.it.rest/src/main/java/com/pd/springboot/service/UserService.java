package com.pd.springboot.service;

import javax.inject.Named;

import com.pd.businessobject.UserFO;
import com.pd.businessobject.UserVO;
import com.pd.it.common.itf.BaseService;
import com.pd.springboot.dao.IUserDao;

@Named
public class UserService extends BaseService<UserFO, UserVO, IUserDao> {

}
