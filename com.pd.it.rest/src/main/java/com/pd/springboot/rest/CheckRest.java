package com.pd.springboot.rest;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pd.it.common.businessobject.ResultVO;
import com.pd.it.common.exception.BusinessException;
import com.pd.springboot.business.CheckService;

@RestController
@RequestMapping("checkRest")
public class CheckRest {
    @Inject
    private CheckService checkService;

    @RequestMapping("/check")
    public ResultVO check() throws BusinessException {
        checkService.validRedisQuery();
        return checkService.check();
    }

}
