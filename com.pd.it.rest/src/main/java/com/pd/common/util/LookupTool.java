package com.pd.common.util;

import static com.pd.it.common.util.StaticTool.getBean;

import com.pd.businessobject.LookupFO;
import com.pd.businessobject.LookupVO;
import com.pd.it.common.exception.BusinessException;
import com.pd.springboot.service.LookupService;

public class LookupTool {
    public static LookupVO queryInfo(LookupFO fo) {
        LookupService lookupService = getBean("lookupService", LookupService.class);
        try {
            return lookupService.queryInfo(fo);
        } catch (BusinessException e) {
            e.printStackTrace();
        }
        return null;
    }
}
