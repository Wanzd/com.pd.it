package com.pd.springboot.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pd.businessobject.SysDataSourceBO;
import com.pd.businessobject.SysDataSourceFO;
import com.pd.springboot.service.SysDataSourceService;
import com.pd.standard.web.IStandardRest;

/**
 * AI系统
 * 
 * @author thinkpad
 *
 */
@RestController
@RequestMapping("sysDataSourceRest")
public class SysDataSourceRest implements IStandardRest<SysDataSourceFO, SysDataSourceBO> {
    @Autowired
    private SysDataSourceService service;

}
