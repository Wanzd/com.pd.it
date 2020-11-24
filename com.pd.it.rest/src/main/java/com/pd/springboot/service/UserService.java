package com.pd.springboot.service;

import javax.inject.Named;

import com.pd.businessobject.UserFO;
import com.pd.businessobject.UserVO;
import com.pd.springboot.dao.IUserDao;

@Named
public class UserService extends ServiceAdapter<UserFO, UserVO, IUserDao> {

}
