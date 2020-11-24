package com.pd.springboot.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pd.businessobject.AppMyFollowFO;
import com.pd.businessobject.AppMyFollowVO;
import com.pd.springboot.dao.IAppMyFollowDao;
import com.pd.standard.web.IStandardRest;

@RestController
@RequestMapping("myFollowRest")
public class MyFollowRest implements IStandardRest<AppMyFollowFO, AppMyFollowVO> {
	@Autowired
	private IAppMyFollowDao dao;

}
