package com.pd.springboot.business;

import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;

import com.pd.businessobject.MapVO;
import com.pd.springboot.service.Job51Service;

@Named
public class JobBusiness {
    @Autowired
    private Job51Service service;

    public void init(MapVO fo) {
        service.init(fo);
    }

    public void process(MapVO fo) {
        service.process(fo);
    }

}
