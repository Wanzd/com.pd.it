package com.pd.springboot.rest;

import com.pd.it.common.exception.BusinessException;
import com.pd.it.common.util.DbTool;
import com.pd.redis.businessobject.RedisFO;
import com.pd.redis.businessobject.RedisVO;
import com.pd.springboot.redis.service.RedisService;
import com.pd.springboot.wiki.service.WikiPlusService;
import com.pd.standard.web.BaseRest;
import com.pd.wiki.businessobject.WikiFO;
import com.pd.wiki.businessobject.WikiVO;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;

@RestController
@RequestMapping("sysRedisRest")
public class SysRedisRest {

    @Inject
    private RedisService redisService;

    @RequestMapping(value = "/keys", method = { RequestMethod.POST, RequestMethod.GET })
    @ResponseBody
    public Object keys(@RequestBody(required = false) RedisFO fo) throws BusinessException {
        return redisService.keys(fo);
    }

    @RequestMapping(value = "/get", method = { RequestMethod.POST, RequestMethod.GET })
    @ResponseBody
    public Object get(@RequestBody RedisFO fo) throws BusinessException {
        return redisService.get(fo.getKey());
    }
}
