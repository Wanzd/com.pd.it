package com.pd.springboot.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pd.businessobject.AppMyFollowFO;
import com.pd.businessobject.AppMyFollowVO;
import com.pd.springboot.dao.IAppMyFollowDao;
import com.pd.standard.web.BaseRest;

@RestController
@RequestMapping("myFollowRest")
public class MyFollowRest extends BaseRest<AppMyFollowFO, AppMyFollowVO, IAppMyFollowDao> {

}
