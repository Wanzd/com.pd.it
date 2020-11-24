package com.pd.springboot.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pd.businessobject.AppJobBO;
import com.pd.springboot.service.JobService;
import com.pd.standard.web.IStandardRest;

@RestController
@RequestMapping("jobRest")
public class JobRest implements IStandardRest<AppJobBO, AppJobBO> {
	@Autowired
	private JobService service;

}
