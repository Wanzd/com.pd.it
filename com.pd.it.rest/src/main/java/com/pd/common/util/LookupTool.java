package com.pd.common.util;

import static com.pd.it.common.util.StaticTool.assertNull;
import static com.pd.it.common.util.StaticTool.getBean;

import com.pd.businessobject.LookupFO;
import com.pd.businessobject.LookupVO;
import com.pd.it.common.exception.BusinessException;
import com.pd.it.common.util.DbTool;
import com.pd.springboot.service.LookupService;

public class LookupTool {
    public static LookupVO getInfo(LookupFO fo) throws BusinessException {
        LookupService lookupService = getBean("lookupService", LookupService.class);
        assertNull(lookupService, "lookupService");
        return DbTool.queryInfo(lookupService, fo);
    }
}
