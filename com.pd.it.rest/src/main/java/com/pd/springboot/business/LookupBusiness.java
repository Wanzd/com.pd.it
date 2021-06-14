package com.pd.springboot.business;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import com.pd.businessobject.LookupFO;
import com.pd.businessobject.LookupVO;
import com.pd.it.common.exception.BusinessException;
import com.pd.springboot.service.LookupService;

@Named
public class LookupBusiness {
    @Inject
    private LookupService lookupService;

    public List<LookupVO> getCityList() throws BusinessException {
        LookupFO lookupFO = new LookupFO();
        lookupFO.setType("市");
        return lookupService.queryList(lookupFO);
    }
}
