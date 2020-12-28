package com.pd.springboot.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pd.businessobject.SysInfoFO;
import com.pd.businessobject.SysInfoVO;
import com.pd.springboot.service.InfoService;
import com.pd.standard.web.BaseRest;

@RestController
@RequestMapping("infoRest")
public class InfoRest extends BaseRest<SysInfoFO, SysInfoVO, InfoService> {

}
