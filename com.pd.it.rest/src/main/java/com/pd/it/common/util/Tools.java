package com.pd.it.common.util;

import static com.pd.it.common.util.StaticTool.getBean;

import com.pd.springboot.struct.CountryStruct.CountryService;

public class Tools {
    public static CountryService getCountryTool() {
        return getBean("countryService", CountryService.class);
    }

}
