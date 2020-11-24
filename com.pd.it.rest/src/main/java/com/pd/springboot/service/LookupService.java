package com.pd.springboot.service;

import javax.inject.Named;

import com.pd.businessobject.LookupFO;
import com.pd.businessobject.LookupVO;
import com.pd.common.enums.LookupExportConfigEum;
import com.pd.springboot.dao.ILookupDao;

@Named
public class LookupService extends ServiceAdapter<LookupFO, LookupVO, ILookupDao> {
    @Override
    public Class<LookupExportConfigEum> getExportConfig() {
        return LookupExportConfigEum.class;
    }
}
