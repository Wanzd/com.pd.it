package com.pd.springboot.rest;

import com.pd.springboot.service.PdSwitchService;
import com.pd.springboot.business.CheckService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pd.it.common.exception.BusinessException;

import javax.inject.Inject;

@RestController
@RequestMapping("/cacheRest")
public class CacheRest {

    @Inject
    private CheckService checkService;
    @Inject
    private PdSwitchService pdSwitchService;

    @RequestMapping(value="/clearAllCache", method = { RequestMethod.POST})
    public boolean clearCache() throws BusinessException {
        pdSwitchService.refreshLookupSwitch();
        return true;
    }

    @RequestMapping(value="/getRedisSwitch", method = { RequestMethod.POST})
    public boolean getRedisSwitch() throws BusinessException {
        return pdSwitchService.getRedisSwtich("test");
    }
}
