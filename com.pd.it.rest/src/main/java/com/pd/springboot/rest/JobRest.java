package com.pd.springboot.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pd.businessobject.AppJobBO;
import com.pd.springboot.service.JobService;
import com.pd.standard.web.BaseRest;

@RestController
@RequestMapping("jobRest")
public class JobRest extends BaseRest<AppJobBO, AppJobBO, JobService> {

}
