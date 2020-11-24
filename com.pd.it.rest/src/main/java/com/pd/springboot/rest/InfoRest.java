package com.pd.springboot.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pd.businessobject.SysInfoFO;
import com.pd.businessobject.SysInfoVO;
import com.pd.springboot.service.InfoService;
import com.pd.standard.web.IStandardRest;

@RestController
@RequestMapping("infoRest")
public class InfoRest implements IStandardRest<SysInfoFO, SysInfoVO> {
	@Autowired
	private InfoService service;

}
