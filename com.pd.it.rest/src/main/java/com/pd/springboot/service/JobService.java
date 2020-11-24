package com.pd.springboot.service;

import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;

import com.pd.springboot.dao.IAppJobPlusDao;

@Named
public class JobService {
    @Autowired
    private IAppJobPlusDao dao;
}
