package com.pd.springboot.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pd.businessobject.UserFO;
import com.pd.businessobject.UserVO;
import com.pd.springboot.service.UserService;
import com.pd.standard.web.IStandardRest;

@RestController
@RequestMapping("userRest")
public class UserRest implements IStandardRest<UserFO, UserVO> {
	@Autowired
	private UserService service;

}
