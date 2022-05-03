package com.pd.job.business;

import com.pd.it.common.businessobject.MapVO;
import com.pd.job.service.Job51Service;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class JobBusiness {
    @Inject
    private Job51Service job51Service;

    public void init(MapVO fo) {
        job51Service.init(fo);
    }

    public void process(MapVO fo) {
        job51Service.process(fo);
    }

}
